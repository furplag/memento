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

import jp.furplag.sandbox.memento.flyway.FlywayConfigurer;

/**
 * {@link Flyway} database migration for default (primary) database .
 *
 * @author furplag
 *
 */
public interface DefaultFlywayConfigurer extends FlywayConfigurer {

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
    private Flyway flyway;

    /**
     * starts the database migration .
     * @see Flyway#migrate()
     */
    @PostConstruct
    private void migrate() {
      if (flyway != null) flyway.migrate();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
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
    // @formatter:on
    return new FlywayAutoConfiguration.FlywayConfiguration(properties, dataSourceProperties, resourceLoader, FlywayConfigurer.inflexibleDataSourceProvider(dataSource), flywayDataSource, migrationStrategy, flywayCallbacks).flyway();
  }

  @Override
  @Bean("flywayProperties")
  @Primary
  @ConfigurationProperties("spring.flyway")
  @ConditionalOnProperty(prefix = "spring.flyway", name = "enabled", matchIfMissing = false)
  default FlywayProperties flywayProperties() {
    return new FlywayProperties();
  }
}
