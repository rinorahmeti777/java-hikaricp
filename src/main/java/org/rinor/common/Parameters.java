package org.rinor.common;

public class Parameters {
    // You should use environment variables in your application, rather than hardcoding the values!
    public static final String MEDIUM_DB_URL = "jdbc:postgresql://localhost:5432/medium";
    public static final String MEDIUM_DB_SCHEMA = "medium";
    public static final String MEDIUM_DB_USERNAME = "rinor";
    public static final String MEDIUM_DB_PASSWORD = "password";
    public static final Integer MEDIUM_DB_MIN_POOL_SIZE = 1;
    public static final Integer MEDIUM_DB_MAX_POOL_SIZE = 10;
    public static final String MYSQL_MEDIUM_DB_URL = "jdbc:mysql://localhost:3308/mysqlmedium";
    public static final String MYSQL_MEDIUM_DB_SCHEMA = "mysqlmedium";
    public static final String MYSQL_MEDIUM_DB_USERNAME = "user";
    public static final String MYSQL_MEDIUM_DB_PASSWORD = "password";
    public static final Integer MYSQL_MEDIUM_DB_MIN_POOL_SIZE = 1;
    public static final Integer MYSQL_MEDIUM_DB_MAX_POOL_SIZE = 10;
}
