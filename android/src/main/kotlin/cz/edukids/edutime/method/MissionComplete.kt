package cz.edukids.edutime.method

import cz.edukids.sdk.EduMission
import cz.edukids.sdk.EduTimeSdkInstance
import io.flutter.plugin.common.MethodCall

class MissionComplete : Method<Unit?> {

    override val methodName = EduMission::complete.name + "Mission"

    override suspend fun invoke(instance: EduTimeSdkInstance, call: MethodCall): Unit? {
        instance.getMission().complete()
        return null
    }

}