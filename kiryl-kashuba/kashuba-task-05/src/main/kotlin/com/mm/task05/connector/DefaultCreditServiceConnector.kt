package com.mm.task05.connector

import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class DefaultCreditServiceConnector(private val webClient: WebClient) : CreditServiceConnector {

  override fun getIdDebtors(daysExpired: Long): Flux<Long> {
    return Flux.from(
      webClient
        .get()
        .uri("/borrower/expired/days/{amount}", daysExpired)
        .retrieve()
        .bodyToFlux(Long::class.java)
    )
  }

  override fun saveDocumentBorrower(ownerId: Long, documentId: String): Mono<String> {
    return Mono.from(
      webClient
        .post()
        .uri("/document/{borrowerId}/{documentId}", ownerId, documentId)
        .retrieve()
        .bodyToMono(String::class.java)
    )
  }

  override fun deleteDocumentBorrower(fileId: String): Mono<Void> {
    return Mono.from(
      webClient
        .delete()
        .uri("/document/{fileId}", fileId)
        .retrieve()
        .bodyToMono(Void::class.java)
    )
  }
}