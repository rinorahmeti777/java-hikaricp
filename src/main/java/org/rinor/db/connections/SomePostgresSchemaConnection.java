package org.rinor.db.connections;

import org.rinor.db.common.CommonSchemaConnection;
import org.rinor.db.common.DataSourceHolder;
import org.rinor.db.datasource.PostgresDataSourceHolder;

import java.sql.Connection;

public class SomePostgresSchemaConnection extends CommonSchemaConnection {
    private static SomePostgresSchemaConnection instance;
    private final DataSourceHolder writer;
    private final DataSourceHolder reader;

    private SomePostgresSchemaConnection() {
        writer = new PostgresDataSourceHolder(ConnectionPoolDBConfig.somePostgresSchemaConfig());
        reader = new PostgresDataSourceHolder(ConnectionPoolDBConfig.somePostgresSchemaConfig());
    }

    public static SomePostgresSchemaConnection getInstance() {
        SomePostgresSchemaConnection localInstance = instance;
        if (localInstance == null) {
            synchronized (SomePostgresSchemaConnection.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SomePostgresSchemaConnection();
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
