/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

import java.util.Properties;

/**
 *
 * @author joaovperin
 */
public final class PoolProperties {

    private static final String PT_MAX_CONN = "maxConnections";
    private static final String PT_AWAIT_TIME = "awaitingTime";
    private static final String PT_MAX_TRIES = "maxCreateConnectionTries";

    private final Properties properties;

    private PoolProperties(Properties pt) {
        this.properties = pt;
    }

    public int getMaxTries() {
        return Integer.valueOf(properties.getProperty(PT_MAX_TRIES, "15"));
    }

    public int getMaxConnections() {
        return Integer.valueOf(properties.getProperty(PT_MAX_CONN, "10"));
    }

    public int getAwaitingTime() {
        return Integer.valueOf(properties.getProperty(PT_AWAIT_TIME, "80"));
    }

    public static PoolProperties create() {
        return create(new Properties());
    }

    public static PoolProperties create(Properties pt) {
        return new PoolProperties(pt);
    }

}
