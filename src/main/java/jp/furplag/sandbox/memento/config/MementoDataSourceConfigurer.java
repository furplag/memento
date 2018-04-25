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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * configuration for internal database named as "memento" .
 *
 * @author furplag
 *
 */
public interface MementoDataSourceConfigurer {

  /**
   * configuration for internal database named as "memento" .
   *
   * @author furplag
   *
   */
  @Profile("memento")
  @Configuration
  @ConfigurationProperties("memento")
  @ConditionalOnProperty(prefix = "memento", name = "enabled", matchIfMissing = false)
  public static class MementoDataSourceConfig implements MementoDataSourceConfigurer {}

  /**
   * {@link DataSourceProperties} for {@link #dataSource(DataSourceProperties)} .
   *
   * @return {@link DataSourceProperties} for {@link #dataSource(DataSourceProperties)}
   */
  @Bean("mementoDataSourceProperties")
  @ConfigurationProperties("memento.datasource")
  default DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  /**
   * {@link DataSource} for internal database named as "memento" .
   *
   * @param dataSourceProperties {@link #dataSourceProperties()}
   * @return {@link DataSource} for internal database named as "memento"
   */
  @Bean("mementoDataSource")
  @ConfigurationProperties("memento.datasource")
  default DataSource dataSource(@Qualifier("mementoDataSourceProperties") DataSourceProperties dataSourceProperties) {
    return dataSourceProperties.initializeDataSourceBuilder().build();
  }

  @Bean("mementoTransactionManager")
  default DataSourceTransactionManager transactionManager(@Qualifier("mementoDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
