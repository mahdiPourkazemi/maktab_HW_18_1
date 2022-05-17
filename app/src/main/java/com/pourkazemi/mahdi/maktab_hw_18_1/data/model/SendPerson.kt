package com.pourkazemi.mahdi.maktab_hw_18_1.data.model

data class SendPerson(
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String?>
)