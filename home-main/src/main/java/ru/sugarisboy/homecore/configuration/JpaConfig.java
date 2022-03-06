package ru.sugarisboy.homecore.configuration;

import static ru.sugarisboy.homecore.configuration.ApiConstant.HOME_FEATURE_PACKAGE;
import static ru.sugarisboy.homecore.configuration.ApiConstant.HOME_MAIN_PACKAGE;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan({HOME_MAIN_PACKAGE, HOME_FEATURE_PACKAGE})
@EnableJpaRepositories({HOME_MAIN_PACKAGE, HOME_FEATURE_PACKAGE})
public class JpaConfig {

}
