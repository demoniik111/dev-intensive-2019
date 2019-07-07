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
        var translit = ""
        for (char in payload) {
            val c = char.toString().toLowerCase()
            translit +=
                if (char == ' ')
                    divider
                else if (c in translitDict) {
                    val tchar = translitDict[c]
                    if (char.isUpperCase())
                        tchar?.toUpperCase()
                    else
                        tchar
                } else {
                    char
                }
        }
        return translit
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val f = firstName?.trim()?.firstOrNull()
        val l = lastName?.trim()?.firstOrNull()
        return if (f == null && l == null) null else "${f ?: ""}${l ?: ""}".toUpperCase()
    }
}