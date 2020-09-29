package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.ScreenTimeCategoryInfo
import io.flutter.plugin.common.MethodCall

class GetScreenTimeCategoryInfo : Method<ScreenTimeCategoryInfo> {

    override val methodName = EduTimeSdkInstance::getScreenTimeCategoryConstraints.name

    override suspend fun invoke(
        instance: EduTimeSdkInstance,
        call: MethodCall
    ): ScreenTimeCategoryInfo {
        return instance.getScreenTimeCategoryConstraints().getOrThrow()
    }

}