/**
 * Copyright (C) 2018+ furplag (https://github.com/furplag)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.furplag.sandbox.memento.config;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.callback.FlywayCallback;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;

/**
 * {@link Flyway} database migration for default (primary) database .
 *
 * @author furplag
 *
 */
public interface DefaultFlywayConfigurer {

  /**
   * {@link Flyway} database migration for default (primary) database .
   *
   * @author furplag
   *
   */
  @Profile("memento")
  @Configuration
  @ConditionalOnProperty(prefix = "spring.flyway", name = "enabled", matchIfMissing = false)
  @AutoConfigureAfter({ DefaultDataSourceConfigurer.class })
  public static class DefaultFlywayConfig implements DefaultFlywayConfigurer {

    /** {@link Flyway} for default (primary) database . */
    @Autowired
    @Qualifier("flyway")
    Flyway flyway;

    /**
     * starts the database migration .
     * @see Flyway#migrate()
     */
    @PostConstruct
    void migrate() {
      if (flyway != null) flyway.migrate();
    }
  }

  @Bean("flywayProperties")
  @Primary
  @ConfigurationProperties("spring.flyway")
  @ConditionalOnProperty(prefix = "spring.flyway", name = "enabled", matchIfMissing = false)
  default FlywayProperties flywayProperties() {
    return new FlywayProperties();
  }

  /**
   * {@link Flyway} for default (primary) database .
   *
   * @param properties {@link FlywayProperties}
   * @param dataSourceProperties {@link DataSourceProperties}
   * @param resourceLoader {@link ResourceLoader}
   * @param dataSource {@link DataSource}
   * @param migrationStrategy {@link FlywayMigrationStrategy}
   * @param flywayCallbacks {@link FlywayCallback}
   * @return {@link Flyway} for default (primary) database .
   *
   * @see DefaultDataSourceConfigurer
   */
  @Bean("flyway")
  @Primary
  @ConfigurationProperties("spring.flyway")
  default Flyway flyway(
    // @formatter:off
    @Qualifier("flywayProperties") FlywayProperties properties,
    @Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties,
    ResourceLoader resourceLoader,
    @Qualifier("dataSource") DataSource dataSource,
    @FlywayDataSource ObjectProvider<DataSource> flywayDataSource,
    ObjectProvider<FlywayMigrationStrategy> migrationStrategy,
    ObjectProvider<List<FlywayCallback>> flywayCallbacks) {

    // returns only a dataSource with named "mementoDataSource" in any situation .
    ObjectProvider<DataSource> wrappedDataSource = new ObjectProvider<DataSource>() {
      @Override public DataSource getObject() throws BeansException { return dataSource; }
      @Override public DataSource getObject(Object... args) throws BeansException { return dataSource; }
      @Override public DataSource getIfAvailable() throws BeansException { return dataSource; }
      @Override public DataSource getIfUnique() throws BeansException { return dataSource; }
    };

    return new FlywayAutoConfiguration.FlywayConfiguration(properties, dataSourceProperties, resourceLoader, wrappedDataSource, flywayDataSource, migrationStrategy, flywayCallbacks).flyway();
    // @formatter:on
  }
}
