package com.mm.task04.config;

import com.mm.task04.controller.UserAccountController;
import com.mm.task04.converter.DefaultUserAccountConverter;
import com.mm.task04.converter.UserAccountConverter;
import com.mm.task04.repository.JooqUserAccountRepository;
import com.mm.task04.repository.UserAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAccountConfig {

  private final JooqConfig jooqConfiguration;

  public UserAccountConfig(JooqConfig jooqConfiguration) {
    this.jooqConfiguration = jooqConfiguration;
  }

  @Bean
  public UserAccountConverter userAccountConverter() {
    return new DefaultUserAccountConverter();
  }

  @Bean
  public UserAccountRepository userRepository() {
    return new JooqUserAccountRepository(jooqConfiguration.dslContext(), userAccountConverter());
  }

  @Bean
  public UserAccountController userAccountController() {
    return new UserAccountController(userRepository());
  }
}
