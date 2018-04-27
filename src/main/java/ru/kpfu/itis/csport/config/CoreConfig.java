package ru.kpfu.itis.csport.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.kpfu.itis.csport")
public class CoreConfig {

  @Configuration
  @Profile("heroku")
  @PropertySource(value = {"classpath:application.properties", "classpath:application-heroku.properties"})
  static class Heroku{}

  @Configuration
  @Profile("default")
  @PropertySource(value = {"classpath:application.properties", "classpath:application-local.properties"}, ignoreResourceNotFound = true)
  static class Defaults{}

}
