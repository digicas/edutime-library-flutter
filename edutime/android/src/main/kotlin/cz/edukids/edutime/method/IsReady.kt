package cz.edukids.edutime.method

import cz.edukids.edutime.EdutimePlugin
import io.flutter.plugin.common.MethodCall

class IsReady : NonInstanceMethod<Boolean> {

    override val methodName = "isReady"

    override suspend fun invoke(plugin: EdutimePlugin, call: MethodCall): Boolean {
        return plugin.isInitialized
    }

}