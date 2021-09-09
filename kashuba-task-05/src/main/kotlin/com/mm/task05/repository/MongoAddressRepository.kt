package com.mm.task05.repository

import com.mm.task05.model.AddressModel
import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoDatabase
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class MongoAddressRepository(private val mongoDatabase: MongoDatabase) : AddressRepository {

  override fun get(id: Long): Mono<AddressModel> {
    return Mono
      .from(
        mongoDatabase.getCollection("address", AddressModel::class.java)
          .find(eq("borrowerId", id))
      )
  }

  override fun getAmount(amount: Int): Flux<AddressModel> {
    return Flux
      .from(
        mongoDatabase.getCollection("address", AddressModel::class.java)
          .find()
          .limit(amount)
      )
  }

  override fun create(address: AddressModel): Mono<Unit> {
    return Mono.from(
      mongoDatabase.getCollection("address", AddressModel::class.java)
        .insertOne(address)
    )
      .flatMap { Mono.empty() }
  }

  override fun update(address: AddressModel): Mono<Unit> {
    return Mono.from(
      mongoDatabase.getCollection("address", AddressModel::class.java)
        .replaceOne(eq("borrowerId", address.borrowerId), address)
    )
      .flatMap { Mono.empty() }
  }

  override fun delete(id: Long): Mono<Unit> {
    return Mono
      .from(
        mongoDatabase.getCollection("address", AddressModel::class.java)
          .deleteOne(eq("borrowerId", id))
      )
      .flatMap { Mono.empty() }
  }
}