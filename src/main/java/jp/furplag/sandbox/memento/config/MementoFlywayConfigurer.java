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
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;

/**
 * {@link Flyway} database migration for internal database named as "memento" .
 *
 * @author furplag
 *
 */
public interface MementoFlywayConfigurer {

  /**
   * {@link Flyway} database migration for internal database named as "memento" .
   *
   * @author furplag
   *
   */
  @Profile("memento")
  @Configuration
  @ConditionalOnProperty(prefix = "memento.flyway", name = "enabled", matchIfMissing = false)
  @AutoConfigureAfter({ MementoDataSourceConfigurer.class })
  @ConfigurationProperties("memento.flyway")
  public static class MementoFlywayConfig implements MementoFlywayConfigurer {

    /** {@link Flyway} for internal database named as "memento" . */
    @Autowired
    @Qualifier("mementoFlyway")
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

  /**
   * {@link FlywayProperties} for {@link #flyway} .
   *
   * @return {@link FlywayProperties} for {@link #flyway}
   */
  @Bean("mementoFlywayProperties")
  @ConfigurationProperties("memento.flyway")
  default FlywayProperties flywayProperties() {
    return new FlywayProperties();
  }

  /**
   * {@link Flyway} for internal database named as "memento" .
   *
   * @param properties {@link FlywayProperties}
   * @param dataSourceProperties {@link DataSourceProperties}
   * @param resourceLoader {@link ResourceLoader}
   * @param dataSource {@link DataSource}
   * @param migrationStrategy {@link FlywayMigrationStrategy}
   * @param flywayCallbacks {@link FlywayCallback}
   * @return {@link Flyway} for internal database named as "memento"
   *
   * @see MementoDataSourceConfigurer
   */
  @Bean("mementoFlyway")
  @ConfigurationProperties("memento.flyway")
  default Flyway flyway(
  // @formatter:off
    @Qualifier("mementoFlywayProperties") FlywayProperties properties,
    @Qualifier("mementoDataSourceProperties") DataSourceProperties dataSourceProperties,
    ResourceLoader resourceLoader,
    @Qualifier("mementoDataSource") DataSource dataSource,
    ObjectProvider<FlywayMigrationStrategy> migrationStrategy,
    ObjectProvider<List<FlywayCallback>> flywayCallbacks) {

    // returns only a dataSource with named "mementoDataSource" in any situation .
    ObjectProvider<DataSource> mementoDataSource = new ObjectProvider<DataSource>() {
      @Override public DataSource getObject() throws BeansException { return dataSource; }
      @Override public DataSource getObject(Object... args) throws BeansException { return dataSource; }
      @Override public DataSource getIfAvailable() throws BeansException { return dataSource; }
      @Override public DataSource getIfUnique() throws BeansException { return dataSource; }
    };

    return new FlywayAutoConfiguration.FlywayConfiguration(properties, dataSourceProperties, resourceLoader, mementoDataSource, mementoDataSource, migrationStrategy, flywayCallbacks).flyway();
    // @formatter:on
  }
}
