package com.mm.task05.repository

import com.mm.task05.model.ContactModel
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ContactRepository {

  fun create(contact: ContactModel): Mono<Unit>

  fun get(id: Long): Mono<ContactModel>

  fun getAmount(amount: Int): Flux<ContactModel>

  fun update(contact: ContactModel): Mono<Unit>

  fun delete(id: Long): Mono<Unit>
}