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

package jp.furplag.sandbox.memento.domain.repos;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import jp.furplag.memento.domain.entity.Memento;
import jp.furplag.sandbox.memento.doma.Memently;

@Memently
@Dao
@Transactional
public interface MementoDao {

  @Select
  List<Memento> findAll();

  @Select
  Memento oneOf(Memento e);

  @Insert(excludeNull = true)
  Result<Memento> insert(Memento e);

  @Update(ignoreVersion = false)
  Result<Memento> update(Memento e);

  @Update(sqlFile = true)
  Result<Memento> save(Memento e);

  default Memento getIfPersistencive(Memento e) {
    return Optional.ofNullable(oneOf(e)).orElse(e);
  }
}
