package cz.edukids.edutime.method

import cz.edukids.edutime.EdutimePlugin
import cz.edukids.sdk.EduTimeSdkInstance
import io.flutter.plugin.common.MethodCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

object EduTimeMethodRegistry {

    private val methods = listOf<Method<*>>(
        GetCurrencyStats(),
        GetScreenTimeCategoryInfo(),
        GetSkillLevel(),
        GetTimeConstraints(),
        MissionComplete(),
        MissionFinish(),
        MissionStart(),
        SuggestCorrectCategory()
    )

    private val nonInstanceMethods = listOf<NonInstanceMethod<*>>(
        IsReady(),
    )

    suspend operator fun MethodCall.invoke(instance: EduTimeSdkInstance) = coroutineScope {
        withContext(Dispatchers.Default) {
            select().invoke(instance, this@invoke)
        }
    }

    suspend operator fun MethodCall.invoke(plugin: EdutimePlugin) = coroutineScope {
        withContext(Dispatchers.Default) {
            selectNonInstance()?.invoke(plugin, this@invoke)
        }
    }

    private fun MethodCall.select() = methods.first { it.methodName == method }
    private fun MethodCall.selectNonInstance() =
        nonInstanceMethods.firstOrNull { it.methodName == method }

}