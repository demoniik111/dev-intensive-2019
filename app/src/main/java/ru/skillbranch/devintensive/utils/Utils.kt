package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        if (fullName.isNullOrEmpty()) {
            return Pair(null, null)
        } else {
            if (fullName != " ") {
                val parts : List<String>? = fullName?.split(" ")

                val firstName = parts?.getOrNull(0)
                var lastName = parts?.getOrNull(1)

                return Pair(firstName, lastName)
            } else {
                return Pair(null, null)
            }
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("not implemented")
    }
}