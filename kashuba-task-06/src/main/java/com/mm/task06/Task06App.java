package com.mm.task06;

import com.mm.base.MMAppBuilder;
import com.mm.base.config.DefaultSpringAppConfig;
import com.mm.base.config.WebFluxConfig;
import com.mm.task06.config.BorrowerConfig;
import com.mm.task06.config.CreditConfig;
import com.mm.task06.config.GraphQlConfig;
import com.mm.task06.config.JacksonConfig;
import com.mm.task06.config.JooqConfig;
import com.mm.task06.config.PersonalDataConfig;
import com.mm.task06.config.UserAccountConfig;
import com.mm.task06.config.WorkConfig;

public class Task06App {

  public static void main(String[] args) {
    new MMAppBuilder()
        .sources(
            DefaultSpringAppConfig.class,
            WebFluxConfig.class,
            PersonalDataConfig.class,
            JooqConfig.class,
            GraphQlConfig.class,
            JacksonConfig.class,
            BorrowerConfig.class,
            CreditConfig.class,
            PersonalDataConfig.class,
            UserAccountConfig.class,
            WorkConfig.class)
        .run(8888, "lab", "task-06");
  }
}
