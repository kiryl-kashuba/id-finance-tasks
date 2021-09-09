package com.mm.task05.model

import java.util.*

data class DocInfoModel(
  val id: String,
  val ownerId: Long,
  val ownerIdType: String?,
  val docType: String?,
  val fileName: String,
  val uploadDate: Date?
)