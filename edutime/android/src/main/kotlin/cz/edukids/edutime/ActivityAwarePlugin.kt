package cz.edukids.edutime

import android.app.Activity
import com.skoumal.teanity.tools.log.info
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodChannel

abstract class ActivityAwarePlugin : FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {

    protected var activity: Activity? = null
        set(value) {
            field = value
            onAttached()
        }

    protected var channel: MethodChannel? = null
        private set

    protected open val methodChannelName = this::class.java.simpleName

    protected fun requireActivity() =
        activity ?: throw IllegalAccessException("Cannot access activity that's never been created")

    protected fun requireChannel() =
        channel ?: throw IllegalAccessException("Cannot access channel that's never been created")

    protected open fun onAttached() {}

    // region FlutterPlugin
    final override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        info("Attached to Flutter engine")
        channel = MethodChannel(binding.binaryMessenger, methodChannelName).also {
            it.setMethodCallHandler(this)
        }
    }

    final override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        info("Detached from Flutter engine")
        channel?.setMethodCallHandler(null)
        channel = null
    }
    // endregion

    // region ActivityAware
    final override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        info("Attached to activity")
        activity = binding.activity
    }

    final override fun onDetachedFromActivity() {
        info("Detached from activity")
        activity = null
    }

    final override fun onDetachedFromActivityForConfigChanges() =
        onDetachedFromActivity()

    final override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) =
        onAttachedToActivity(binding)
    // endregion

}