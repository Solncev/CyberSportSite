package ru.kpfu.itis.csport.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("ru.kpfu.itis.csport")
public class CoreConfig {

  @Bean
  public PropertySourcesPlaceholderConfigurer globalProperties() {
    return propertiesBean(
        new ClassPathResource("application.properties"),
        new ClassPathResource("application-local.properties")
    );
  }

  @Bean
  @Profile("heroku")
  public PropertySourcesPlaceholderConfigurer herokuProperties() {
    return propertiesBean(
        new ClassPathResource("application-heroku.properties")
    );
  }

  private PropertySourcesPlaceholderConfigurer propertiesBean(Resource... resources) {
    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setLocations(resources);
    configurer.setIgnoreResourceNotFound(true);
    //configurer.setIgnoreUnresolvablePlaceholders(true);
    return configurer;
  }
}
