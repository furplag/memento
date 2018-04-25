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

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.seasar.doma.boot.autoconfigure.DomaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jp.furplag.sandbox.memento.doma.DomaConfigurer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * DOMA configuration for internal database named as "memento" .
 *
 * @author furplag
 *
 */
public interface MementoDomaConfigurer extends DomaConfigurer {

  /**
   * DOMA configuration for internal database named as "memento" .
   *
   * @author furplag
   *
   */
  @Profile("memento")
  @Component("mementoDomaConfig")
  @Configuration
  @ConditionalOnProperty(prefix = "memento", name = "enabled", matchIfMissing = false)
  @AutoConfigureAfter({ MementoDataSourceConfigurer.class, MementoFlywayConfigurer.class })
  @EnableTransactionManagement
  @ConfigurationProperties("memento")
  @Slf4j
  public static class MementoDomaConfig implements MementoDomaConfigurer {

    /** {@link #domaProperties()} . */
    @Autowired
    @Qualifier("mementoDomaProperties")
    @Getter
    private DomaProperties domaProperties;

    /** {@link DataSource} for internal database named as "memento" . */
    @Autowired
    @Qualifier("mementoDataSource")
    @Getter
    private DataSource dataSource;

    @PostConstruct
    private void initialized() {
      log.debug("initialized DOMA configuration named as \"mementoDomaConfig\" .\n  {}", getDomaProperties());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Bean("mementoDomaProperties")
  @ConfigurationProperties(prefix = "memento.doma")
  default DomaProperties domaProperties() {
    return new DomaProperties();
  }
}
