package com.mm.task05.model

data class DocFileModel(
  val id: String,
  val name: String,
  val content: ByteArray
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as DocFileModel

    if (id != other.id) return false
    if (name != other.name) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + name.hashCode()
    return result
  }
}
