package cz.edukids.edutime.method

import cz.edukids.sdk.EduMission
import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.EduMissionFinishParams
import io.flutter.plugin.common.MethodCall

class MissionFinish : Method<Unit?> {

    override val methodName = EduMission::finish.name + "Mission"

    override suspend fun invoke(instance: EduTimeSdkInstance, call: MethodCall): Unit? {
        val params = EduMissionFinishParams(
            contractId = call.argument<String>(EduMissionFinishParams::contractId.name)!!,
            isSuccess = call.argument<Boolean>(EduMissionFinishParams::isSuccess.name)!!,
            pointsAcquired = call.argument<Int>(EduMissionFinishParams::pointsAcquired.name)!!,
            dataBundle = call.argument(EduMissionFinishParams::dataBundle.name)
        )
        instance.getMission().finish(params)
        return null
    }

}