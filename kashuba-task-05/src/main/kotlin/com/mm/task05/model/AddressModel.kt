package com.mm.task05.model

data class AddressModel(
  val borrowerId: Long,
  val address: Map<AddressType, String>?
)

