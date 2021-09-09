package com.mm.task04.config;

import com.mm.task04.controller.CreditController;
import com.mm.task04.converter.CreditConverter;
import com.mm.task04.converter.DefaultCreditConverter;
import com.mm.task04.repository.CreditRepository;
import com.mm.task04.repository.JooqCreditRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditConfig {

  private final JooqConfig jooqConfiguration;

  public CreditConfig(JooqConfig jooqConfiguration) {
    this.jooqConfiguration = jooqConfiguration;
  }

  @Bean
  public CreditConverter creditConverter() {
    return new DefaultCreditConverter();
  }

  @Bean
  public CreditRepository creditRepository() {
    return new JooqCreditRepository(jooqConfiguration.dslContext(), creditConverter());
  }

  @Bean
  public CreditController creditController() {
    return new CreditController(creditRepository());
  }
}
