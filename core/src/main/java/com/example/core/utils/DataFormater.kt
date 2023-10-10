package com.example.core.utils

import androidx.core.text.buildSpannedString

object DataFormater {
    fun formatArtist(names: List<String?>?): CharSequence {
        if (names.isNullOrEmpty()) {
            return ""
        }

        return when (names.size) {
            1 -> "${names[0]}"
            2 -> buildSpannedString {
                append("${names[0]}")
                append(" and ")
                append("${names[1]}")
            }

            else -> buildSpannedString {
                for (i in names.indices) {
                    val name = names[i]
                    if (name != null) {
                        if (i > 0) {
                            if (i == names.size - 1) {
                                append(" and ")
                            } else {
                                append(", ")
                            }
                        }
                        append("$name")
                    }
                }
            }
        }
    }

    fun formatDesc(desc: String): String {
        return "\u3000\u3000$desc"
    }

}