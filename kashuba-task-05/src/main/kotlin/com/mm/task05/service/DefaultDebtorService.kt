package com.mm.task05.service

import com.mm.task05.connector.CreditServiceConnector
import com.mm.task05.model.ContactModel
import com.mm.task05.repository.ContactRepository
import reactor.core.publisher.Flux

class DefaultDebtorService(
  private val contactRepository: ContactRepository,
  private val creditServiceConnector: CreditServiceConnector
) : DebtorService {

  override fun get(daysExpired: Long): Flux<ContactModel> {
    return Flux.from(creditServiceConnector.getIdDebtors(daysExpired)
      .flatMap { m -> contactRepository.get(m) })
  }
}