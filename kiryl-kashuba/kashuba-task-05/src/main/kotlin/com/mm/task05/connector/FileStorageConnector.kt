package com.mm.task05.connector

import com.mm.task05.model.DocFileModel
import com.mm.task05.model.DocInfoModel
import com.mm.task05.model.DocType
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Mono

interface FileStorageConnector {

  fun upload(file: FilePart, ownerId: Long, idType: String, docType: DocType): Mono<DocInfoModel>

  fun download(fileId: String): Mono<DocFileModel>

  fun delete(fileId: String): Mono<Void>
}