package org.rinor.db.connections;

import org.rinor.common.Parameters;

public class ConnectionPoolDBConfig {
    private final String dbSchemaName;
    private final String dbUsername;
    private final String dbPassword;
    private final int minPoolSize;
    private final int maxPoolSize;
    private String dbUrl;

    public String getDbSchemaName() {
        return dbSchemaName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public ConnectionPoolDBConfig(String dbUrl, String dbSchemaName, String dbUsername, String dbPassword, int minPoolSize, int maxPoolSize) {
        this.dbUrl = dbUrl;
        this.dbSchemaName = dbSchemaName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.minPoolSize = minPoolSize;
        this.maxPoolSize = maxPoolSize;
    }

    public static ConnectionPoolDBConfig somePostgresSchemaConfig() {
        return new ConnectionPoolDBConfig(Parameters.MEDIUM_DB_URL,
                Parameters.MEDIUM_DB_SCHEMA, Parameters.MEDIUM_DB_USERNAME, Parameters.MEDIUM_DB_PASSWORD,
                Parameters.MEDIUM_DB_MIN_POOL_SIZE, Parameters.MEDIUM_DB_MAX_POOL_SIZE);
    }

    public static ConnectionPoolDBConfig someMySQLSchemaConfig() {
        return new ConnectionPoolDBConfig(Parameters.MYSQL_MEDIUM_DB_URL,
                Parameters.MYSQL_MEDIUM_DB_SCHEMA, Parameters.MYSQL_MEDIUM_DB_USERNAME, Parameters.MYSQL_MEDIUM_DB_PASSWORD,
                Parameters.MYSQL_MEDIUM_DB_MIN_POOL_SIZE, Parameters.MYSQL_MEDIUM_DB_MAX_POOL_SIZE);
    }
}