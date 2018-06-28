package com.zhukovme.flickrclient.data

import com.zhukovme.flickrclient.ui.common.StringSuggestion

/**
 * Created by Michael Zhukov on 28.06.2018.
 * email: zhukovme@gmail.com
 */
class SuggestionsStore(private val prefHelper: PrefHelper) {

    companion object {
        private const val SUGGESTIONS_PREF = "suggestions"
    }

    fun put(stringSuggestion: StringSuggestion) {
        val suggestions = get()
        suggestions.add(stringSuggestion)
        suggestions.map { it.body }
                .toSet()
                .let { prefHelper.putStringSet(SUGGESTIONS_PREF, it) }

    }

    fun get(filter: (StringSuggestion) -> Boolean = { _ -> true }): MutableSet<StringSuggestion> =
            prefHelper.getStringSet(SUGGESTIONS_PREF, emptySet())
                    .map { StringSuggestion(it) }
                    .filter(filter)
                    .toMutableSet()

    fun clear() = prefHelper.putStringSet(SUGGESTIONS_PREF, emptySet())
}
