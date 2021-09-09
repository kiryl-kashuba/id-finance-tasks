package com.mm.task05.repository

import com.mm.task05.model.ContactModel
import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoDatabase
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class MongoContactRepository(private val mongoDatabase: MongoDatabase) : ContactRepository {

  override fun create(contact: ContactModel): Mono<Unit> {
    return Mono
      .from(
        mongoDatabase.getCollection("contact", ContactModel::class.java)
          .insertOne(contact)
      )
      .flatMap { Mono.empty() }
  }

  override fun get(id: Long): Mono<ContactModel> {
    return Mono
      .from(
        mongoDatabase.getCollection("contact", ContactModel::class.java)
          .find(eq("borrowerId", id))
      )
  }

  override fun getAmount(amount: Int): Flux<ContactModel> {
    return Flux
      .from(
        mongoDatabase.getCollection("contact", ContactModel::class.java)
          .find()
          .limit(amount)
      )
  }

  override fun update(contact: ContactModel): Mono<Unit> {
    return Mono.from(
      mongoDatabase.getCollection("contact", ContactModel::class.java)
        .replaceOne(eq("borrowerId", contact.borrowerId), contact)
    )
      .flatMap { Mono.empty() }
  }

  override fun delete(id: Long): Mono<Unit> {
    return Mono
      .from(
        mongoDatabase.getCollection("contact", ContactModel::class.java)
          .deleteOne(eq("borrowerId", id))
      )
      .flatMap { Mono.empty() }
  }
}