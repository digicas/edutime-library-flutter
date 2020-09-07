package cz.edukids.edutime

import android.util.Log
import androidx.lifecycle.lifecycleScope
import cz.edukids.edutime.dictionary.toResponse
import cz.edukids.edutime.method.EduTimeMethodRegistry.invoke
import cz.edukids.sdk.EduTimeSdk
import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.EduError
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import kotlinx.coroutines.launch

class EduTimePlugin : ActivityAwarePlugin() {

    private lateinit var instance: EduTimeSdkInstance
    override val methodChannelName = "EduTime"

    override fun onAttached() {
        EduTimeSdk().runCatching { getNewInstance(requireActivity().intent) }
            .onSuccess { instance = it }
            .onFailure {
                Log.e(
                    this::class.java.simpleName,
                    "Cannot instantiate EduTimeSdk. Check intent for params"
                )
            }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (!this::instance.isInitialized) {
            return result.error(
                "ERR-EDU-100",
                "SDK is not initialized yet! Connect to activity.",
                null
            )
        }

        requireActivity().lifecycleScope.launch {
            call.runCatching { invoke(instance) }
                .onFailure {
                    when (it) {
                        is NoSuchElementException -> result.notImplemented()
                        else -> result(it)
                    }
                }
                .onSuccess { result(it) }
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
            val plugin = EduTimePlugin()
            val channel = MethodChannel(registrar.messenger(), plugin.methodChannelName)
            channel.setMethodCallHandler(plugin)
        }

    }

}
