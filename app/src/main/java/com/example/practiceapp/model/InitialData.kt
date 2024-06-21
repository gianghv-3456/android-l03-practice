package com.example.practiceapp.model

object InitialData {
    val accountList = mutableListOf(Account("admin","admin"))
    val contactList = mutableListOf<Contact>(
        Contact("John Doe", "123 Main St", "555-1234", "01/01/1990", "file:///android_asset/cat.png"),
        Contact("Jane Smith", "456 Oak Ave", "555-5678", "02/02/1991", "file:///android_asset/dog.png"),
        Contact("Emily Johnson", "789 Pine Rd", "555-9101", "03/03/1992", "file:///android_asset/owl.png"),
        Contact("Michael Brown", "321 Cedar Blvd", "555-1122", "04/04/1993", "file:///android_asset/lion.png"),
        Contact("Sarah Davis", "654 Maple Ave", "555-3344", "05/05/1994", "file:///android_asset/cat.png"),
        Contact("David Wilson", "987 Birch St", "555-5566", "06/06/1995", "file:///android_asset/dog.png"),
        Contact("Linda Martinez", "135 Elm St", "555-7788", "07/07/1996", "file:///android_asset/owl.png"),
    )
}