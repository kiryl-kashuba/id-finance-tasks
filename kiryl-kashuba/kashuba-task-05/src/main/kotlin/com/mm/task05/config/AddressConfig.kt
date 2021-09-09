package com.mm.task05.config

import com.mm.task05.controller.AddressController
import com.mm.task05.repository.AddressRepository
import com.mm.task05.repository.MongoAddressRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AddressConfig(
  private val mongoConfiguration: MongoConfig
) {

  @Bean
  open fun addressController(): AddressController {
    return AddressController(addressRepository())
  }

  @Bean
  open fun addressRepository(): AddressRepository {
    return MongoAddressRepository(mongoConfiguration.mongoDatabase())
  }
}