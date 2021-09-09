package com.mm.task05.connector

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CreditServiceConnector {

  fun getIdDebtors(daysExpired: Long): Flux<Long>

  fun saveDocumentBorrower(ownerId: Long, documentId: String): Mono<String>

  fun deleteDocumentBorrower(fileId: String): Mono<Void>
}