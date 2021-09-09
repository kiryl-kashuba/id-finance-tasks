package com.mm.task05.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mm.platform.mongo.config.MongoClientBuilder
import com.mm.platform.mongo.config.MongoSettings
import com.mongodb.reactivestreams.client.MongoDatabase
import org.cfg4j.provider.ConfigurationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MongoConfig(private val configProvider: ConfigurationProvider) {

  @Bean
  open fun mongoDatabase(): MongoDatabase {
    val mongoSettings = configProvider.bind("mongo.db", MongoSettings::class.java)
    return MongoClientBuilder.of(mongoSettings).withJackson(jacksonObjectMapper()).build()
  }
}