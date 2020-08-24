package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import io.flutter.plugin.common.MethodCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

object EduTimeMethodRegistry {

    private val methods = listOf<Method<*>>(
        GetCurrencyStats(),
        GetScreenTimeCategoryConstraints(),
        GetSkillLevel(),
        GetTimeConstraints(),
        MissionComplete(),
        MissionFinish(),
        MissionStart(),
        SuggestCorrectCategory()
    )

    suspend operator fun MethodCall.invoke(instance: EduTimeSdkInstance) = coroutineScope {
        withContext(Dispatchers.Default) {
            select().invoke(instance, this@invoke)
        }
    }

    private fun MethodCall.select() = methods.first { it.methodName == method }

}