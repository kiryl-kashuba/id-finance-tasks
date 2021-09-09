package com.mm.task06.config;

import com.mm.task06.controller.WorkController;
import com.mm.task06.converter.DefaultWorkConverter;
import com.mm.task06.converter.WorkConverter;
import com.mm.task06.repository.JooqWorkRepository;
import com.mm.task06.repository.WorkRepository;
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
