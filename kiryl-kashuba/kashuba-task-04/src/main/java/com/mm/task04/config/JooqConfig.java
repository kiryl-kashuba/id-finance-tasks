package com.mm.task04.config;

import com.mm.base.service.crypt.CipherEngineAES;
import com.mm.platform.jooq.audit.JooqConverterAwareAuditRecordListener;
import com.mm.platform.jooq.config.DefaultJooqBuilder;
import com.mm.platform.jooq.config.JooqSettings;
import com.mm.platform.jooq.datatype.CryptStringConverter;
import org.cfg4j.provider.ConfigurationProvider;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

  private final ConfigurationProvider configurationProvider;

  public JooqConfig(ConfigurationProvider configurationProvider) {
    this.configurationProvider = configurationProvider;
  }

  @Bean
  @SuppressWarnings({"java:S2696", "squid:S2696"})
  public DSLContext dslContext() {
    final JooqSettings settings = configurationProvider.bind("onboarding.jooq", JooqSettings.class);

    CryptStringConverter.cipherEngine = new CipherEngineAES(
        configurationProvider.getProperty("secret.key", String[].class));

    return DefaultJooqBuilder.of(settings)
        .withDefaultProviders()
        .withRecordListeners(new JooqConverterAwareAuditRecordListener())
        .build();
  }
}
