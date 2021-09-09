package com.mm.task05.repository

import com.mm.task05.connector.CreditServiceConnector
import com.mm.task05.model.DocInfoModel
import reactor.core.publisher.Mono

class MongoBorrowerDocumentRepository(
  private val creditServiceConnector: CreditServiceConnector
) : BorrowerDocumentRepository {

  override fun save(docInfoModel: DocInfoModel): Mono<String> {
    return Mono.defer {
      Mono.just(docInfoModel)
        .flatMap { d ->
          creditServiceConnector.saveDocumentBorrower(d.ownerId, d.id)
        }
    }
  }

  override fun delete(fileId: String): Mono<Void> {
    return Mono.defer {
      creditServiceConnector.deleteDocumentBorrower(fileId)
    }
  }
}