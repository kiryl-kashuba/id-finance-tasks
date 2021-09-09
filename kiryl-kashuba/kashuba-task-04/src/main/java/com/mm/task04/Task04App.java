package com.mm.task04;

import com.mm.base.MMAppBuilder;
import com.mm.base.config.DefaultSpringAppConfig;
import com.mm.base.config.WebFluxConfig;
import com.mm.task04.config.BorrowerConfig;
import com.mm.task04.config.CreditConfig;
import com.mm.task04.config.JooqConfig;
import com.mm.task04.config.PersonalDataConfig;
import com.mm.task04.config.UserAccountConfig;
import com.mm.task04.config.WorkConfig;

public class Task04App {

  public static void main(String[] args) {
    new MMAppBuilder()
        .sources(
            DefaultSpringAppConfig.class,
            WebFluxConfig.class,
            PersonalDataConfig.class,
            JooqConfig.class,
            BorrowerConfig.class,
            CreditConfig.class,
            PersonalDataConfig.class,
            UserAccountConfig.class,
            WorkConfig.class)
        .run(8888, "lab", "task-04");
  }
}
