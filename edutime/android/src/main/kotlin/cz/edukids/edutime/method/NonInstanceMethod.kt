package cz.edukids.edutime.method

import cz.edukids.edutime.EdutimePlugin
import io.flutter.plugin.common.MethodCall

interface NonInstanceMethod<Response : Any?> {

    val methodName: String

    suspend operator fun invoke(plugin: EdutimePlugin, call: MethodCall): Response

}