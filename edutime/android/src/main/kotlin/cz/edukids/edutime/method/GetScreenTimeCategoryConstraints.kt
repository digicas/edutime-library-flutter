package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.ScreenTimeCategoryConstraints
import io.flutter.plugin.common.MethodCall

class GetScreenTimeCategoryConstraints : Method<ScreenTimeCategoryConstraints> {

    override val methodName = EduTimeSdkInstance::getScreenTimeCategoryConstraints.name

    override suspend fun invoke(
        instance: EduTimeSdkInstance,
        call: MethodCall
    ): ScreenTimeCategoryConstraints {
        return instance.getScreenTimeCategoryConstraints().getOrThrow()
    }

}