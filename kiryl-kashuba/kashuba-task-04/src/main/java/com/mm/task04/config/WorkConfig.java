package com.mm.task04.config;

import com.mm.task04.controller.WorkController;
import com.mm.task04.converter.DefaultWorkConverter;
import com.mm.task04.converter.WorkConverter;
import com.mm.task04.repository.JooqWorkRepository;
import com.mm.task04.repository.WorkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkConfig {

  private final JooqConfig jooqConfiguration;

  public WorkConfig(JooqConfig jooqConfiguration) {
    this.jooqConfiguration = jooqConfiguration;
  }

  @Bean
  public WorkConverter workConverter() {
    return new DefaultWorkConverter();
  }

  @Bean
  public WorkRepository workRepository() {
    return new JooqWorkRepository(jooqConfiguration.dslContext(), workConverter());
  }

  @Bean
  public WorkController workController() {
    return new WorkController(workRepository());
  }
}
