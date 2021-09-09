package com.mm.task05.repository

import com.mm.task05.model.AddressModel
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AddressRepository {

  fun get(id: Long): Mono<AddressModel>

  fun getAmount(amount: Int): Flux<AddressModel>

  fun create(address: AddressModel): Mono<Unit>

  fun update(address: AddressModel): Mono<Unit>

  fun delete(id: Long): Mono<Unit>
}