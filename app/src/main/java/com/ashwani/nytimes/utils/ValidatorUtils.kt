package com.ashwani.nytimes.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern


class ValidatorUtils {

    companion object {
        private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        private const val EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
        private const val MIN_NAME_LENGTH = 3
        private const val MIN_PASSWORD_LENGTH = 8

        fun isNameValid(name: String): Boolean {
            return !TextUtils.isEmpty(name.trim()) && name.trim().length >= MIN_NAME_LENGTH
        }

        fun isValidEmail(email: String): Boolean {
            val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher: Matcher = pattern.matcher(email)
            return !TextUtils.isEmpty(email) && email.length >= MIN_PASSWORD_LENGTH && matcher.matches()
        }

        fun isValidPassword(password: String): Boolean {
            val pattern: Pattern = Pattern.compile(PASSWORD_PATTERN)
            val matcher: Matcher = pattern.matcher(password)
            return !TextUtils.isEmpty(password) && password.length >= MIN_PASSWORD_LENGTH && matcher.matches()
        }
    }
}