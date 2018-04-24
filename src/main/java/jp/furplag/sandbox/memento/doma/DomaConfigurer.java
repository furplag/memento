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
package jp.furplag.sandbox.memento.doma;

import org.seasar.doma.boot.autoconfigure.DomaProperties;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.UnknownColumnException;
import org.seasar.doma.jdbc.UnknownColumnHandler;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.entity.EntityType;
import org.seasar.doma.jdbc.query.Query;
import org.springframework.context.annotation.Bean;

public interface DomaConfigurer extends Config {

  /**
   * no raising {@link UnknownColumnException} even if unknown column appeared .
   *
   * @return {@link UnknownColumnHandler} which silently through off any of unknown columns .
   */
  public static UnknownColumnHandler lazilyUnknownColumnHandler() {
    return new UnknownColumnHandler() {@Override public void handle(Query query, EntityType<?> entityType, String unknownColumnName) {} };
  }

  /**
   * {@link DomaProperties} for default database .
   * <p>
   * <strong>you must be overridden this and annotate with {@link Bean @Bean} specify the parameter "name" if you want to registration multiple DOMA .</strong>
   * </p>
   *
   * @return {@link DomaProperties} for default database
   */
  default DomaProperties domaProperties() {
    return new DomaProperties();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  default Dialect getDialect() {
    return getDomaProperties().getDialect().create();
  }

  /** {@link #domaProperties()} . */
  DomaProperties getDomaProperties();

  /**
   * {@inheritDoc}
   */
  @Override
  default UnknownColumnHandler getUnknownColumnHandler() {
    return lazilyUnknownColumnHandler();
  }
}
