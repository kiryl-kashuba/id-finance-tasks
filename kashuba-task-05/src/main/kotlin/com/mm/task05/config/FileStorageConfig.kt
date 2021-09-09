package com.mm.task05.config

import com.mm.task05.connector.DefaultFileStorageConnector
import com.mm.task05.connector.FileStorageConnector
import com.mm.task05.controller.FileStorageController
import com.mm.task05.repository.BorrowerDocumentRepository
import com.mm.task05.repository.FileRepository
import com.mm.task05.repository.MongoBorrowerDocumentRepository
import com.mm.task05.repository.MongoFileRepository
import org.cfg4j.provider.ConfigurationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class FileStorageConfig(
  private val debtorServiceConfiguration: DebtorServiceConfig,
  private val configurationProvider: ConfigurationProvider
) {

  @Bean
  open fun fileStorageWebClient(): WebClient {
    return WebClient.builder()
      .baseUrl(configurationProvider.getProperty("file_storage.base_url", String::class.java))
      .build()
  }

  @Bean
  open fun fileStorageController(): FileStorageController {
    return FileStorageController(fileRepository())
  }

  @Bean
  open fun fileRepository(): FileRepository {
    return MongoFileRepository(fileStorageConnector(), borrowerDocumentRepository())
  }

  @Bean
  open fun fileStorageConnector(): FileStorageConnector {
    return DefaultFileStorageConnector(fileStorageWebClient())
  }

  @Bean
  open fun borrowerDocumentRepository(): BorrowerDocumentRepository {
    return MongoBorrowerDocumentRepository(debtorServiceConfiguration.creditServiceConnector())
  }
}