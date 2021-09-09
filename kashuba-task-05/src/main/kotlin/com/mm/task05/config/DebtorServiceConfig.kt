package com.mm.task05.config

import com.mm.task05.connector.CreditServiceConnector
import com.mm.task05.connector.DefaultCreditServiceConnector
import com.mm.task05.controller.DebtorServiceController
import com.mm.task05.service.DebtorService
import com.mm.task05.service.DefaultDebtorService
import org.cfg4j.provider.ConfigurationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class DebtorServiceConfig(
  private val contactConfiguration: ContactConfig,
  private val configurationProvider: ConfigurationProvider
) {

  @Bean
  open fun debtorServiceController(): DebtorServiceController {
    return DebtorServiceController(debtorService())
  }

  @Bean
  open fun debtorService(): DebtorService {
    return DefaultDebtorService(contactConfiguration.contactRepository(), creditServiceConnector())
  }

  @Bean
  open fun creditServiceWebClient(): WebClient {
    return WebClient.builder()
      .baseUrl(configurationProvider.getProperty("credit_service.base_url", String::class.java))
      .build()
  }

  @Bean
  open fun creditServiceConnector(): CreditServiceConnector {
    return DefaultCreditServiceConnector(creditServiceWebClient())
  }
}