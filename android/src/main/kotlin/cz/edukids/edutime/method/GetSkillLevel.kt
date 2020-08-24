package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.SkillLevel
import io.flutter.plugin.common.MethodCall

class GetSkillLevel : Method<SkillLevel> {

    override val methodName = EduTimeSdkInstance::getSkillLevel.name

    override suspend fun invoke(instance: EduTimeSdkInstance, call: MethodCall): SkillLevel {
        return instance.getSkillLevel().getOrThrow()
    }

}