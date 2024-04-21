package org.rinor.db.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.rinor.db.common.DataSourceHolder;
import org.rinor.db.connections.ConnectionPoolDBConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDataSourceHolder implements DataSourceHolder {
    final Object lockingObject = new Object();
    final ConnectionPoolDBConfig dbConfig;
    private HikariDataSource hikariDataSource = null;

    public MySQLDataSourceHolder(ConnectionPoolDBConfig dbConfig) {
        this.dbConfig = dbConfig;
        init();
    }

    @Override
    public void init() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername(dbConfig.getDbUsername());
        config.setPassword(dbConfig.getDbPassword());
        config.setJdbcUrl(dbConfig.getDbUrl());

        config.setMaximumPoolSize(dbConfig.getMaxPoolSize());
        config.setLeakDetectionThreshold(50);
        config.setConnectionTimeout(5000);
        config.setMaxLifetime(600000);
        hikariDataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (hikariDataSource == null) {
            synchronized (lockingObject) {
                if (hikariDataSource == null) {
                    init();
                }
            }
        }
        return hikariDataSource.getConnection();
    }

    @Override
    public void disconnect() {
        synchronized (lockingObject) {
            if (hikariDataSource != null && !hikariDataSource.isClosed()) {
                hikariDataSource.close();
            }
        }
    }

    @Override
    public ConnectionPoolDBConfig getDbConfig() {
        return dbConfig;
    }

    @Override
    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    @Override
    public Object getLockingObject() {
        return lockingObject;
    }
}
