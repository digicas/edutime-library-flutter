package cz.edukids.edutime.method

import cz.edukids.sdk.EduTimeSdkInstance
import cz.edukids.sdk.model.ScreenTimeCategorySuggestion
import io.flutter.plugin.common.MethodCall

class SuggestCorrectCategory : Method<Unit?> {

    override val methodName = EduTimeSdkInstance::suggestCorrectCategory.name

    override suspend fun invoke(instance: EduTimeSdkInstance, call: MethodCall): Unit? {
        val params = ScreenTimeCategorySuggestion(
            id = call.argument<String>(ScreenTimeCategorySuggestion::id.name)!!
        )
        return instance.suggestCorrectCategory(params)
    }

}