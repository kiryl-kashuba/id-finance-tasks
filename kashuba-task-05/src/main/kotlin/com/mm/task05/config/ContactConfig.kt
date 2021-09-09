package com.mm.task05.config

import com.mm.task05.controller.ContactController
import com.mm.task05.repository.ContactRepository
import com.mm.task05.repository.MongoContactRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ContactConfig(
  private val mongoConfiguration: MongoConfig
) {

  @Bean
  open fun contactController(): ContactController {
    return ContactController(contactRepository())
  }

  @Bean
  open fun contactRepository(): ContactRepository {
    return MongoContactRepository(mongoConfiguration.mongoDatabase())
  }
}