package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import io.flutter.plugin.common.MethodCall

interface Method<Response : Any?> {

    val methodName: String

    suspend operator fun invoke(instance: EduTimeSdkInstance, call: MethodCall): Response

}