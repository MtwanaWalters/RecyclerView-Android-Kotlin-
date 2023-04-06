package com.sscience.example.recyclerview.data

import androidx.lifecycle.MutableLiveData

object EmailDataStore {

    private val allEmails = listOf(
        Email(
            1L,
            "Software Science",
            "Feedback",
            "Thank you all for being part of the team. Your support is highly appreciated" +
                    "If you have not already then please make sure to subscribe.",
            false
        ),
        Email(
            2L,
            "Sky Rocket Publishers",
            "<No subject>",
            "A reminder for all our users. Billing information has been updated in our" +
                    "website, please take time to review the changes. The team.",
            true
        ),
        Email(
            3L,
            "FreeCodeCamp.org",
            "Code Fest Kenya",
            "Free code camp will be hosting an event in Nairobi, Kenya. " +
                    "Make sure to attend.",
            false
        ),
        Email(
            4L,
            "noreply@feedback.com",
            "<No subject>",
            "Use the confirmation code sent to your device to verify yourself in our portal.",
            false,
            MailBox.DELETED
        ),
        Email(
            5L,
            "David Leshan",
            "Friday night party at Emmas place",
            "Hey, hope this mail finds you well. The team and I will be holding a party at" +
                    "Emmas place. We have all your favourite food. Make sure to pass by then. Ill" +
                    "save you a seat. Cheers.",
            false
        ),
        Email(
            6L,
            "Intel Corporation India",
            "Product promotion",
            "New intel powered computers are available in our store",
            false
        )
    )

    private val emailLiveData: MutableLiveData<List<Email>> = MutableLiveData()
    init {
        emailLiveData.value = allEmails
    }

    val inbox: List<Email>? = emailLiveData.value?.filter { email ->
        email.mailbox == MailBox.INBOX
    }

}