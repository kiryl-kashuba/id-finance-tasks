package com.mm.task05.repository

import com.mm.task05.connector.FileStorageConnector
import com.mm.task05.model.DocFileModel
import com.mm.task05.model.DocType
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Mono

class MongoFileRepository(
  private val fileStorageConnector: FileStorageConnector,
  private val borrowerDocumentService: BorrowerDocumentRepository
) : FileRepository {

  override fun upload(file: FilePart, ownerId: Long, idType: String, docType: DocType): Mono<String> {
    return Mono.from(
      fileStorageConnector.upload(file, ownerId, idType, docType)
        .map { r -> borrowerDocumentService.save(r) }
        .flatMap { resp -> resp }
    )
  }

  override fun download(fileId: String): Mono<DocFileModel> {
    return Mono.from(
      fileStorageConnector.download(fileId)
    )
  }

  override fun delete(fileId: String): Mono<Void> {
    return Mono.from(
      fileStorageConnector.delete(fileId)
        .then(borrowerDocumentService.delete(fileId))
    )
  }
}