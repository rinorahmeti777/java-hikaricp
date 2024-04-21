package org.rinor.services;

import org.rinor.db.connections.SomePostgresSchemaConnection;
import org.rinor.db.connections.SomeMySQLSchemaConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabasePingService {
    public String getPostgresVersion() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = SomePostgresSchemaConnection.getInstance().getReader();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT version() as version");
            if (rs.next()) {
                return rs.getString("version");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return null;
    }

    public String getMySQLVersion() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = SomeMySQLSchemaConnection.getInstance().getReader();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT version() as version");
            if (rs.next()) {
                return rs.getString("version");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return null;
    }
}
