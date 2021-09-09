package com.mm.task05.repository

import com.mm.task05.model.DocFileModel
import com.mm.task05.model.DocType
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Mono

interface FileRepository {

  fun upload(file: FilePart, ownerId: Long, idType: String, docType: DocType): Mono<String>

  fun download(fileId: String): Mono<DocFileModel>

  fun delete(fileId: String): Mono<Void>
}