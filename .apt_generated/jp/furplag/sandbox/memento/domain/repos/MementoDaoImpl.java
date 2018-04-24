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

/** */
@org.springframework.stereotype.Repository()
@javax.annotation.Generated(value = { "Doma", "2.19.2" }, date = "2018-04-24T18:42:33.180+0900")
public class MementoDaoImpl extends org.seasar.doma.internal.jdbc.dao.AbstractDao implements jp.furplag.sandbox.memento.domain.repos.MementoDao {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.19.2");
    }

    private static final java.lang.reflect.Method __method0 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(jp.furplag.sandbox.memento.domain.repos.MementoDao.class, "findAll");

    private static final java.lang.reflect.Method __method1 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(jp.furplag.sandbox.memento.domain.repos.MementoDao.class, "oneOf", jp.furplag.memento.domain.entity.Memento.class);

    private static final java.lang.reflect.Method __method2 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(jp.furplag.sandbox.memento.domain.repos.MementoDao.class, "insert", jp.furplag.memento.domain.entity.Memento.class);

    private static final java.lang.reflect.Method __method3 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(jp.furplag.sandbox.memento.domain.repos.MementoDao.class, "update", jp.furplag.memento.domain.entity.Memento.class);

    private static final java.lang.reflect.Method __method4 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(jp.furplag.sandbox.memento.domain.repos.MementoDao.class, "save", jp.furplag.memento.domain.entity.Memento.class);

    /**
     * @param config the config
     */
    @org.springframework.beans.factory.annotation.Autowired()
    public MementoDaoImpl(@org.springframework.beans.factory.annotation.Qualifier("mementoDomaConfig") org.seasar.doma.jdbc.Config config) {
        super(config);
    }

    @Override
    public java.util.List<jp.furplag.memento.domain.entity.Memento> findAll() {
        entering("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "findAll");
        try {
            org.seasar.doma.jdbc.query.SqlFileSelectQuery __query = getQueryImplementors().createSqlFileSelectQuery(__method0);
            __query.setMethod(__method0);
            __query.setConfig(__config);
            __query.setSqlFilePath("META-INF/jp/furplag/sandbox/memento/domain/repos/MementoDao/findAll.sql");
            __query.setEntityType(jp.furplag.memento.domain.entity._Memento.getSingletonInternal());
            __query.setCallerClassName("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl");
            __query.setCallerMethodName("findAll");
            __query.setResultEnsured(false);
            __query.setResultMappingEnsured(false);
            __query.setFetchType(org.seasar.doma.FetchType.LAZY);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.prepare();
            org.seasar.doma.jdbc.command.SelectCommand<java.util.List<jp.furplag.memento.domain.entity.Memento>> __command = getCommandImplementors().createSelectCommand(__method0, __query, new org.seasar.doma.internal.jdbc.command.EntityResultListHandler<jp.furplag.memento.domain.entity.Memento>(jp.furplag.memento.domain.entity._Memento.getSingletonInternal()));
            java.util.List<jp.furplag.memento.domain.entity.Memento> __result = __command.execute();
            __query.complete();
            exiting("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "findAll", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "findAll", __e);
            throw __e;
        }
    }

    @Override
    public jp.furplag.memento.domain.entity.Memento oneOf(jp.furplag.memento.domain.entity.Memento entity) {
        entering("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "oneOf", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.jdbc.query.SqlFileSelectQuery __query = getQueryImplementors().createSqlFileSelectQuery(__method1);
            __query.setMethod(__method1);
            __query.setConfig(__config);
            __query.setSqlFilePath("META-INF/jp/furplag/sandbox/memento/domain/repos/MementoDao/oneOf.sql");
            __query.setEntityType(jp.furplag.memento.domain.entity._Memento.getSingletonInternal());
            __query.addParameter("entity", jp.furplag.memento.domain.entity.Memento.class, entity);
            __query.setCallerClassName("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl");
            __query.setCallerMethodName("oneOf");
            __query.setResultEnsured(false);
            __query.setResultMappingEnsured(false);
            __query.setFetchType(org.seasar.doma.FetchType.LAZY);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.prepare();
            org.seasar.doma.jdbc.command.SelectCommand<jp.furplag.memento.domain.entity.Memento> __command = getCommandImplementors().createSelectCommand(__method1, __query, new org.seasar.doma.internal.jdbc.command.EntitySingleResultHandler<jp.furplag.memento.domain.entity.Memento>(jp.furplag.memento.domain.entity._Memento.getSingletonInternal()));
            jp.furplag.memento.domain.entity.Memento __result = __command.execute();
            __query.complete();
            exiting("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "oneOf", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "oneOf", __e);
            throw __e;
        }
    }

    @Override
    public org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento> insert(jp.furplag.memento.domain.entity.Memento entity) {
        entering("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "insert", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.jdbc.query.AutoInsertQuery<jp.furplag.memento.domain.entity.Memento> __query = getQueryImplementors().createAutoInsertQuery(__method2, jp.furplag.memento.domain.entity._Memento.getSingletonInternal());
            __query.setMethod(__method2);
            __query.setConfig(__config);
            __query.setEntity(entity);
            __query.setCallerClassName("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl");
            __query.setCallerMethodName("insert");
            __query.setQueryTimeout(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.setNullExcluded(true);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.prepare();
            org.seasar.doma.jdbc.command.InsertCommand __command = getCommandImplementors().createInsertCommand(__method2, __query);
            int __count = __command.execute();
            __query.complete();
            org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento> __result = new org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento>(__count, __query.getEntity());
            exiting("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "insert", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "insert", __e);
            throw __e;
        }
    }

    @Override
    public org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento> update(jp.furplag.memento.domain.entity.Memento entity) {
        entering("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "update", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.jdbc.query.AutoUpdateQuery<jp.furplag.memento.domain.entity.Memento> __query = getQueryImplementors().createAutoUpdateQuery(__method3, jp.furplag.memento.domain.entity._Memento.getSingletonInternal());
            __query.setMethod(__method3);
            __query.setConfig(__config);
            __query.setEntity(entity);
            __query.setCallerClassName("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl");
            __query.setCallerMethodName("update");
            __query.setQueryTimeout(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.setNullExcluded(true);
            __query.setVersionIgnored(false);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.setUnchangedPropertyIncluded(false);
            __query.setOptimisticLockExceptionSuppressed(false);
            __query.prepare();
            org.seasar.doma.jdbc.command.UpdateCommand __command = getCommandImplementors().createUpdateCommand(__method3, __query);
            int __count = __command.execute();
            __query.complete();
            org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento> __result = new org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento>(__count, __query.getEntity());
            exiting("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "update", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "update", __e);
            throw __e;
        }
    }

    @Override
    public org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento> save(jp.furplag.memento.domain.entity.Memento entity) {
        entering("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "save", entity);
        try {
            if (entity == null) {
                throw new org.seasar.doma.DomaNullPointerException("entity");
            }
            org.seasar.doma.jdbc.query.SqlFileUpdateQuery __query = getQueryImplementors().createSqlFileUpdateQuery(__method4);
            __query.setMethod(__method4);
            __query.setConfig(__config);
            __query.setSqlFilePath("META-INF/jp/furplag/sandbox/memento/domain/repos/MementoDao/save.sql");
            __query.addParameter("entity", jp.furplag.memento.domain.entity.Memento.class, entity);
            __query.setCallerClassName("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl");
            __query.setCallerMethodName("save");
            __query.setQueryTimeout(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.setEntityAndEntityType("entity", entity, jp.furplag.memento.domain.entity._Memento.getSingletonInternal());
            __query.setNullExcluded(false);
            __query.setVersionIgnored(false);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.setUnchangedPropertyIncluded(false);
            __query.setOptimisticLockExceptionSuppressed(false);
            __query.prepare();
            org.seasar.doma.jdbc.command.UpdateCommand __command = getCommandImplementors().createUpdateCommand(__method4, __query);
            int __count = __command.execute();
            __query.complete();
            org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento> __result = new org.seasar.doma.jdbc.Result<jp.furplag.memento.domain.entity.Memento>(__count, __query.getEntity(jp.furplag.memento.domain.entity.Memento.class));
            exiting("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "save", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("jp.furplag.sandbox.memento.domain.repos.MementoDaoImpl", "save", __e);
            throw __e;
        }
    }

}
