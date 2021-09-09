package com.mm.task05.model

data class ContactModel(
  val borrowerId: Long,
  val contacts: Map<ContactType, String>?
)