package com.sscience.example.recyclerview.data

import androidx.lifecycle.MutableLiveData
import com.sscience.example.recyclerview.R

object EmailDataStore {

    val senders = listOf(
        Sender(1L,"David Leshan",R.drawable.david_leshan),
        Sender(2L,"Software Science"),
        Sender(3L,"Intel Corporation India",R.drawable.intel),
        Sender(4L,"Sky Rocket Publishers",R.drawable.publishers),
        Sender(5L,"FreeCodeCamp.org",R.drawable.code_camp),
        Sender(6L,"noreply@feedback.com")
    )

    private val allEmails = listOf(
        Email(
            1L,
            sender = senders[1],
            "Feedback",
            "Thank you all for being part of the team. Your support is highly appreciated" +
                    "If you have not already then please make sure to subscribe."
        ),
        Email(
            2L,
            senders[3],
            "<No subject>",
            "A reminder for all our users. Billing information has been updated in our" +
                    "website, please take time to review the changes. The team.",
            true
        ),
        Email(
            3L,
            senders[4],
            "Code Fest Kenya",
            "Free code camp will be hosting an event in Nairobi, Kenya. " +
                    "Make sure to attend."
        ),
        Email(
            4L,
            senders[5],
            "<No subject>",
            "Use the confirmation code sent to your device to verify yourself in our portal.",
            mailbox = MailBox.DELETED,
        ),
        Email(
            5L,
            senders[0],
            "Friday night party at Emmas place",
            "Hey, hope this mail finds you well. The team and I will be holding a party at" +
                    "Emmas place. We have all your favourite food. Make sure to pass by then. Ill" +
                    "save you a seat. Cheers."
        ),
        Email(
            6L,
            senders[2],
            "Product promotion",
            "New intel powered computers are available in our store"
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