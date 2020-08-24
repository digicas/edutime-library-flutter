package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.TimeConstraints
import io.flutter.plugin.common.MethodCall

class GetTimeConstraints : Method<TimeConstraints> {

    override val methodName = EduTimeSdkInstance::getTimeConstraints.name

    override suspend fun invoke(instance: EduTimeSdkInstance, call: MethodCall): TimeConstraints {
        return instance.getTimeConstraints().getOrThrow()
    }

}