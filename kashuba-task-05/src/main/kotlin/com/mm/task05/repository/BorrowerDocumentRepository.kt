package com.mm.task05.repository

import com.mm.task05.model.DocInfoModel
import reactor.core.publisher.Mono

interface BorrowerDocumentRepository {

  fun save(docInfoModel: DocInfoModel): Mono<String>

  fun delete(fileId: String): Mono<Void>
}