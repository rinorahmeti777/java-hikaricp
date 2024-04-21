package org.rinor;

import org.rinor.services.DatabasePingService;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabasePingService databasePingService = new DatabasePingService();
        System.out.println(STR."Postgres version:\{databasePingService.getPostgresVersion()}");
        System.out.println(STR."MySQL version:\{databasePingService.getMySQLVersion()}");

    }
}