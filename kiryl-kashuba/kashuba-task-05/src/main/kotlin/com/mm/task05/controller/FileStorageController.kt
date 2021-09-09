package com.mm.task05.controller

import com.mm.task05.model.DocFileModel
import com.mm.task05.model.DocType
import com.mm.task05.repository.FileRepository
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/document")
class FileStorageController(
  private val fileRepository: FileRepository
) {

  @PostMapping("/upload/{ownerId}/{ownerIdType}/{docType}")
  fun upload(
    @PathVariable("ownerId") ownerId: Long,
    @PathVariable("ownerIdType") idType: String,
    @PathVariable("docType") docType: DocType,
    @RequestPart("file") file: FilePart
  ): Mono<String> {
    return fileRepository.upload(file, ownerId, idType, docType)
  }

  @GetMapping("/download/{fileId}")
  fun download(@PathVariable("fileId") fileId: String): Mono<DocFileModel> {
    return Mono.from(
      fileRepository.download(fileId)
    )
  }

  @GetMapping("/delete/{fileId}")
  fun delete(@PathVariable("fileId") fileId: String): Mono<Void> {
    return Mono.from(
      fileRepository.delete(fileId)
    )
  }
}