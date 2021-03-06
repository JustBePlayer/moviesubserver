package com.ado.moviesub.app;

import com.ado.moviesub.app.entity.movie.locator.SubtitleLocator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
public class AppConfig {

  @Autowired
  private ApplicationContext context;

  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
    b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    b.modules(new JavaTimeModule());
    b.serializationInclusion(JsonInclude.Include.NON_NULL);
    return b;
  }

  @Bean("fileLocatorAlias")
  public SubtitleLocator getSubtitlesFileLocator(@Value("${subtitle.provider}") String qualifier) {
    return (SubtitleLocator) context.getBean(qualifier);
  }

}
