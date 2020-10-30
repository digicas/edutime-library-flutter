package cz.edukids.edutime

import com.skoumal.teanity.tools.log.error
import com.skoumal.teanity.tools.log.info
import com.skoumal.teanity.tools.log.verbose
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

    val isInitialized get() = this::instance.isInitialized

    override fun onAttached() {
        info("EduTimeSdk is being initialized")
        EduTimeSdk().runCatching { getNewInstance(requireActivity().intent) }
            .onSuccess {
                instance = it
                info("Created EduTime instance")
            }
            .onFailure {
                error("Cannot instantiate EduTimeSdk. Check intent for params")
            }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        launch {
            // probes and fetches request from non instance methods
            call.invoke(this@EdutimePlugin)?.let {
                result(it)
                return@launch
            }

            // all other methods require the instance to be initialized so we fail early and return error
            if (!isInitialized) {
                error("Call to method ${call.method} cannot be processed. SDK is not initialized")
                return@launch result.error(
                    "ERR-EDU-100",
                    "SDK is not initialized yet! Connect to activity.",
                    null
                )
            }

            info("Starting call to ${call.method}")

            // probes and fetched all available methods and returns result or fails with notImplemented()
            call.runCatching { invoke(instance) }
                .onFailure {
                    error("Call to ${call.method} failed", it)
                    when (it) {
                        is NoSuchElementException -> result.notImplemented()
                        else -> result(it)
                    }
                }
                .onSuccess {
                    info("Call to ${call.method} successful")
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
        else -> {
            verbose("Responding with ${response.toString()}")
            success(response?.toResponse())
        }
    }

    companion object {

        @JvmStatic
        fun registerWith(registrar: PluginRegistry.Registrar) {
            val plugin = EdutimePlugin()
            val channel = MethodChannel(registrar.messenger(), plugin.methodChannelName)
            channel.setMethodCallHandler(plugin)
            plugin.activity = registrar.activity()
            info("Registered with Flutter plugin registry")
        }

    }

}
