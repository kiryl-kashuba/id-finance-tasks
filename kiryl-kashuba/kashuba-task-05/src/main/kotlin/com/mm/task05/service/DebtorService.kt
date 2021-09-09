package com.mm.task05.service

import com.mm.task05.model.ContactModel
import reactor.core.publisher.Flux

interface DebtorService {

  fun get(daysExpired: Long): Flux<ContactModel>
}