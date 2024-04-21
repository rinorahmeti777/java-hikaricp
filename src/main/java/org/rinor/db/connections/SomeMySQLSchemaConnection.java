package org.rinor.db.connections;

import org.rinor.db.common.CommonSchemaConnection;
import org.rinor.db.common.DataSourceHolder;
import org.rinor.db.datasource.MySQLDataSourceHolder;

import java.sql.Connection;

public class SomeMySQLSchemaConnection extends CommonSchemaConnection {
    private static SomeMySQLSchemaConnection instance;
    private final DataSourceHolder writer;
    private final DataSourceHolder reader;

    private SomeMySQLSchemaConnection() {
        writer = new MySQLDataSourceHolder(ConnectionPoolDBConfig.someMySQLSchemaConfig());
        reader = new MySQLDataSourceHolder(ConnectionPoolDBConfig.someMySQLSchemaConfig());
    }

    public static SomeMySQLSchemaConnection getInstance() {
        SomeMySQLSchemaConnection localInstance = instance;
        if (localInstance == null) {
            synchronized (SomePostgresSchemaConnection.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SomeMySQLSchemaConnection();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getWriter() throws Exception {
        return getConnection(writer, writer.getDbConfig().getDbSchemaName());
    }

    @Override
    public Connection getReader() throws Exception {
        return getConnection(reader, reader.getDbConfig().getDbSchemaName());
    }

    @Override
    public void disconnect() {
        writer.disconnect();
        reader.disconnect();
    }
}

