package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.CurrencyStats
import io.flutter.plugin.common.MethodCall

class GetCurrencyStats : Method<CurrencyStats> {

    override val methodName = EduTimeSdkInstance::getCurrencyStats.name

    override suspend fun invoke(instance: EduTimeSdkInstance, call: MethodCall): CurrencyStats {
        return instance.getCurrencyStats().getOrThrow()
    }

}