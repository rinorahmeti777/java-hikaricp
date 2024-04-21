package org.rinor.db.common;

import com.zaxxer.hikari.HikariDataSource;
import org.rinor.db.connections.ConnectionPoolDBConfig;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSourceHolder {
    void init();

    Connection getConnection() throws SQLException;

    void disconnect();

    ConnectionPoolDBConfig getDbConfig();

    HikariDataSource getHikariDataSource();

    Object getLockingObject();
}
