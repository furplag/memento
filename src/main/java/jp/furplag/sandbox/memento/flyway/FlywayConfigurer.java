package jp.furplag.sandbox.memento.flyway;

import java.util.List;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.callback.FlywayCallback;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ResourceLoader;

/**
 * {@link Flyway} configuration .
 *
 * @author furplag
 *
 */
public interface FlywayConfigurer {

  /**
   * returns only a dataSource with named "mementoDataSource" in any situation .
   *
   * @param dataSource {@link DataSource}
   * @return {@link DataSource} wrapped by {@link ObjectProvider}
   */
  static ObjectProvider<DataSource> inflexibleDataSourceProvider(final DataSource dataSource) {
    return new ObjectProvider<DataSource>() {
      // @formatter:off
      @Override public DataSource getIfAvailable() throws BeansException { return dataSource; }
      @Override public DataSource getIfUnique() throws BeansException { return dataSource; }
      @Override public DataSource getObject() throws BeansException { return dataSource; }
      @Override public DataSource getObject(Object... args) throws BeansException { return dataSource; }
      // @formatter:on
    };
  }

  /**
   * {@link Flyway} initialization .
   *
   * @param properties {@link FlywayProperties}
   * @param dataSourceProperties {@link DataSourceProperties}
   * @param resourceLoader {@link ResourceLoader}
   * @param dataSource {@link DataSource}
   * @param flywayDataSource {@link FlywayMigrationStrategy} wrapped by {@link ObjectProvider}
   * @param migrationStrategy {@link FlywayMigrationStrategy} wrapped by {@link ObjectProvider}
   * @param flywayCallbacks a list of {@link FlywayCallback} wrapped by {@link ObjectProvider}
   * @return {@link Flyway}
   */
  Flyway flyway(
    // @formatter:off
    FlywayProperties properties,
    DataSourceProperties dataSourceProperties,
    ResourceLoader resourceLoader,
    DataSource dataSource,
    @FlywayDataSource ObjectProvider<DataSource> flywayDataSource,
    ObjectProvider<FlywayMigrationStrategy> migrationStrategy,
    ObjectProvider<List<FlywayCallback>> flywayCallbacks);
    // @formatter:on

  /**
   * {@link FlywayProperties} settings with {@link ConfigurationProperties} .
   *
   * @return {@link FlywayProperties}
   */
  default FlywayProperties flywayProperties() {
    return new FlywayProperties();
  }
}
