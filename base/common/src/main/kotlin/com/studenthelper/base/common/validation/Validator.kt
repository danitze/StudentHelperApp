package com.studenthelper.base.common.validation

abstract class Validator<in T> {
    abstract fun isValid(value: T): Boolean
}