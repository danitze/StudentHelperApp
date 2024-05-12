package com.studenthelper.base.common.validation

import androidx.core.util.PatternsCompat

class EmailValidator : Validator<String?>() {
    private val pattern = PatternsCompat.EMAIL_ADDRESS
    override fun isValid(value: String?): Boolean {
        return !(value == null || !pattern.matcher(value).matches())
    }

}