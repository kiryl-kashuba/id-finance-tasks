package com.mm.task06.config;

import com.mm.task06.controller.BorrowerController;
import com.mm.task06.repository.BorrowerRepository;
import com.mm.task06.repository.JooqBorrowerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BorrowerConfig {

  private final JooqConfig jooqConfiguration;
  private final PersonalDataConfig personalDataConfiguration;
  private final WorkConfig workConfiguration;

  public BorrowerConfig(JooqConfig jooqConfiguration,
      PersonalDataConfig personalDataConfiguration,
      WorkConfig workConfiguration) {
    this.jooqConfiguration = jooqConfiguration;
    this.personalDataConfiguration = personalDataConfiguration;
    this.workConfiguration = workConfiguration;
  }

  @Bean
  public BorrowerRepository borrowerRepository() {
    return new JooqBorrowerRepository(jooqConfiguration.dslContext(),
        personalDataConfiguration.personalDataRepository(),
        workConfiguration.workRepository());
  }

  @Bean
  public BorrowerController borrowerController() {
    return new BorrowerController(borrowerRepository());
  }
}
