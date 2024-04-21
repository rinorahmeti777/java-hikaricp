package org.rinor.db.common;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class CommonSchemaConnection {
    public abstract void disconnect();

    public abstract Connection getReader() throws Exception;

    public abstract Connection getWriter() throws Exception;

    protected Connection getConnection(DataSourceHolder dataSourceHolder, String catalog) throws Exception {
        if (dataSourceHolder == null) {
            throw new Exception();
        }
        Connection connection = null;
        try {
            connection = dataSourceHolder.getConnection();
            if (!connection.isClosed()) {
                connection.setCatalog(catalog);
                return connection;
            }
        } catch (SQLException e) {
            closeConnection(connection);
        }

        synchronized (dataSourceHolder.getLockingObject()) {
            try {
                connection = dataSourceHolder.getConnection();
                if (!connection.isClosed()) {
                    connection.setCatalog(catalog);
                    return connection;
                }
            } catch (SQLException throwable) {
                closeConnection(connection);
                throw new Exception(throwable);
            }
        }
        throw new Exception();
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(STR."Error closing the connection:\{e.getMessage()}");
        }
    }
}
