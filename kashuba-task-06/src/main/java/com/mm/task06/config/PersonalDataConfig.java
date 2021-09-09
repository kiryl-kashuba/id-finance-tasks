package com.mm.task06.config;

import com.mm.task06.controller.PersonalDataController;
import com.mm.task06.converter.DefaultPersonalDataConverter;
import com.mm.task06.converter.PersonalDataConverter;
import com.mm.task06.repository.JooqPersonalDataRepository;
import com.mm.task06.repository.PersonalDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonalDataConfig {

  private final JooqConfig jooqConfiguration;

  public PersonalDataConfig(JooqConfig jooqConfiguration) {
    this.jooqConfiguration = jooqConfiguration;
  }

  @Bean
  public PersonalDataConverter personalDataConverter() {
    return new DefaultPersonalDataConverter();
  }

  @Bean
  public PersonalDataRepository personalDataRepository() {
    return new JooqPersonalDataRepository(jooqConfiguration.dslContext(), personalDataConverter());
  }

  @Bean
  public PersonalDataController personalDataController() {
    return new PersonalDataController(personalDataRepository());
  }
}





