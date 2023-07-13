package com.ashwani.nytimes.utils

import org.junit.Assert
import org.junit.Test


class ValidatorUtilsTest {


    @Test
    fun testName() {
        Assert.assertFalse(ValidatorUtils.isNameValid(""))
        Assert.assertFalse(ValidatorUtils.isNameValid("As"))
        Assert.assertTrue(ValidatorUtils.isNameValid("Ashwani"))
    }


    @Test
    fun testEmail() {

        Assert.assertFalse(ValidatorUtils.isValidEmail(""))
        Assert.assertFalse(ValidatorUtils.isValidEmail("ashwani"))
        Assert.assertFalse(ValidatorUtils.isValidEmail("ashwani@"))
        Assert.assertTrue(ValidatorUtils.isValidEmail("ashwani@gmail.com"))
    }


    @Test
    fun testPassword() {
        Assert.assertFalse(ValidatorUtils.isValidPassword(""))
        Assert.assertFalse(ValidatorUtils.isValidPassword("ashwani"))
        Assert.assertFalse(ValidatorUtils.isValidPassword("ashwani@"))
        Assert.assertFalse(ValidatorUtils.isValidPassword("ashwani@1"))
        Assert.assertTrue(ValidatorUtils.isValidPassword("Ashwani@1"))
    }
}