package cz.edukids.edutime.method

import cz.edukids.sdk.EduMission
import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.EduMissionContract
import cz.edukids.sdk.model.EduMissionStartParams
import io.flutter.plugin.common.MethodCall

class MissionStart : Method<EduMissionContract> {

    override val methodName = EduMission::start.name + "Mission"

    override suspend fun invoke(
        instance: EduTimeSdkInstance,
        call: MethodCall
    ): EduMissionContract {
        val params = EduMissionStartParams(
            isRetry = call.argument<Boolean>(EduMissionStartParams::isRetry.name)!!,
            skills = call.argument<List<String>>(EduMissionStartParams::skills.name)!!,
            eduTaskType = call.argument<String>(EduMissionStartParams::eduTaskType.name)!!,
            disciplines = call.argument(EduMissionStartParams::disciplines.name),
            dataBundle = call.argument(EduMissionStartParams::dataBundle.name)
        )
        return instance.getMission().start(params).getOrThrow()
    }

}