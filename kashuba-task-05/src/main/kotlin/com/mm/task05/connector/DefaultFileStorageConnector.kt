package com.mm.task05.connector

import com.mm.task05.model.DocFileModel
import com.mm.task05.model.DocInfoModel
import com.mm.task05.model.DocType
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class DefaultFileStorageConnector(
  private val webClient: WebClient
) : FileStorageConnector {

  override fun upload(file: FilePart, ownerId: Long, idType: String, docType: DocType): Mono<DocInfoModel> {
    return Mono.from(
      webClient
        .post()
        .uri("/upload-document/{ownerId}/{ownerIdType}/{docType}", ownerId, idType, docType)
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData("file", file))
        .retrieve()
        .bodyToMono(DocInfoModel::class.java)
    )
  }

  override fun download(fileId: String): Mono<DocFileModel> {
    return Mono.from(
      webClient
        .get()
        .uri("/download-document/{fileId}", fileId)
        .retrieve()
        .bodyToMono(DocFileModel::class.java)
    )
  }

  override fun delete(fileId: String): Mono<Void> {
    return Mono.from(
      webClient
        .get()
        .uri("/delete-document/{fileId}", fileId)
        .retrieve()
        .bodyToMono(Void::class.java)
    )
  }
}