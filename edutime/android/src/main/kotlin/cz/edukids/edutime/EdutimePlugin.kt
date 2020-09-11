package cz.edukids.edutime

import android.util.Log
import cz.edukids.edutime.dictionary.toResponse
import cz.edukids.edutime.method.EduTimeMethodRegistry.invoke
import cz.edukids.sdk.EduTimeSdk
import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.EduError
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class EdutimePlugin : ActivityAwarePlugin(), CoroutineScope by MainScope() {

    private lateinit var instance: EduTimeSdkInstance
    override val methodChannelName = "edutime"

    override fun onAttached() {
        Log.i(tag(), "EduTimeSdk is being initialized")
        EduTimeSdk().runCatching { getNewInstance(requireActivity().intent) }
                .onSuccess {
                    instance = it
                    Log.i(tag(), "Created EduTime instance")
                }
                .onFailure {
                    Log.e(tag(), "Cannot instantiate EduTimeSdk. Check intent for params")
                }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (!this::instance.isInitialized) {
            Log.e(
                    tag(),
                    "Call to method ${call.method} cannot be processed. SDK is not initialized"
            )
            return result.error(
                    "ERR-EDU-100",
                    "SDK is not initialized yet! Connect to activity.",
                    null
            )
        }

        Log.i(tag(), "Starting call to ${call.method}")

        launch {
            call.runCatching { invoke(instance) }
                    .onFailure {
                        Log.e(tag(), "Call to ${call.method} failed", it)
                        when (it) {
                            is NoSuchElementException -> result.notImplemented()
                            else -> result(it)
                        }
                    }
                    .onSuccess {
                        Log.i(tag(), "Call to ${call.method} successful")
                        result(it)
                    }
        }
    }

    private operator fun Result.invoke(response: Any?) = when (response) {
        // encountered library error that's been caught and is known
        is EduError -> error("ERR-EDU-${response.code}", response.message, null)
        // encountered any other uncaught error
        is Throwable -> error("ERR-EDU-0", response.message, null)
        // respond as usual
        else -> success(response?.toResponse())
    }

    companion object {

        @JvmStatic
        fun registerWith(registrar: PluginRegistry.Registrar) {
            val plugin = EdutimePlugin()
            val channel = MethodChannel(registrar.messenger(), plugin.methodChannelName)
            channel.setMethodCallHandler(plugin)
            plugin.activity = registrar.activity()
            Log.i(tag(), "Registered with Flutter plugin registry")
        }

    }

}

inline fun <reified T : Any> T.tag() = this@tag::class.java.simpleName