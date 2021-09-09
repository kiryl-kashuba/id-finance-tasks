package com.mm.task05

import com.mm.base.MMAppBuilder
import com.mm.base.config.DefaultSpringAppConfig
import com.mm.base.config.WebFluxConfig
import com.mm.task05.config.AddressConfig
import com.mm.task05.config.ContactConfig
import com.mm.task05.config.DebtorServiceConfig
import com.mm.task05.config.FileStorageConfig
import com.mm.task05.config.MongoConfig

object Task05App {

  @JvmStatic
  fun main(args: Array<String>) {
    MMAppBuilder()
      .sources(
        DefaultSpringAppConfig::class.java,
        WebFluxConfig::class.java,
        MongoConfig::class.java,
        AddressConfig::class.java,
        ContactConfig::class.java,
        DebtorServiceConfig::class.java,
        FileStorageConfig::class.java
      )
      .run(8880, "lab", "task-05")
  }
}