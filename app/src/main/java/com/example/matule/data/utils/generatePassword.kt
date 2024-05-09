package com.example.matule.data.utils

import kotlin.random.Random

fun String.generatePassword(): String {
    val russian = "йцукенгшщзхъфывапролджэячсмитьбюё "
    val english = "ycukengwwzhyfyvaproldzeicsmitybuo_"
    val vowel = "уеыаоэяию"
    val vowelNumbers = "738003940"
    val consonants = "йцкнгшщзхфвпрлджчсмтб"
    val consonantsSymbols = "#?%!@$^&*(){}[]/.,№:;"
    var newPassword = ""
    var wasVowel = false
    var wasConsonant = false
    for (c in this) {
        if (c in vowel && (!wasVowel || Random.nextInt(10) == 5)) {
            newPassword += vowelNumbers[vowel.indexOf(c)]
            wasVowel = true
        }
        else if (c in consonants && (!wasConsonant || Random.nextInt(10) == 5)) {
            newPassword += consonantsSymbols[consonants.indexOf(c)]
            wasConsonant = true
        }
        else if (c in russian) {
            newPassword += english[russian.indexOf(c)]
        }
    }
    if (!wasVowel) {
        newPassword += vowelNumbers[Random.nextInt(vowelNumbers.length)]
    }
    if (!wasConsonant) {
        newPassword += consonantsSymbols[Random.nextInt(consonantsSymbols.length)]
    }
    return newPassword
}