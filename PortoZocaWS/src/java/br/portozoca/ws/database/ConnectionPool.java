/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Pool of query connections
 *
 * @author joaovperin
 */
public final class ConnectionPool {

    private static ConnectionPool instance;
    private static PoolProperties properties;

    private final List<ConexaoPool> conexoes;
    private int tries = 0;

    private ConnectionPool() {
        this.conexoes = new ArrayList<>();
    }

    public void addConnection(ConexaoPool conn) throws DBException {
        if (conexoes.size() >= properties.getMaxConnections()) {
            throw new DBException("Pool is full!");
        }
        conexoes.add(conn);
    }

    public ConexaoPool getConnection() throws EmptyPoolException {
        ConexaoPool conn = getPoolConnection();
        tries = 0;
        return conn;
    }

    private ConexaoPool getPoolConnection() throws EmptyPoolException {
        // Recursive protection for not entering in infinite loop
        if (tries++ > properties.getMaxTries()) {
            throw new RuntimeException("Failed to create a new Connection after " + properties.getMaxTries() + " tries.");
        }
        // Try to get a free connection on the pool
        Optional<ConexaoPool> opt = conexoes.stream().filter((c) -> c.isFree()).findFirst();
        if (opt.isPresent()) {
            return opt.get();
        }
        // If it's not full and it don't have any free connections, throws an EmptyPoolException so the caller can create one
        if (conexoes.size() < properties.getMaxConnections()) {
            throw new EmptyPoolException();
        }
        // If it's full, await some time and try again
        try {
            Thread.sleep(properties.getAwaitingTime());
        } catch (InterruptedException ex) {
        }
        return getPoolConnection();
    }

    public static ConnectionPool get() {
        if (instance == null) {
            instantiate();
        }
        return instance;
    }

    private static synchronized void instantiate() {
        if (instance == null) {
            instance = new ConnectionPool();
            properties = PoolProperties.create();
            // Add's a shutdown hook to close all connections
            Runtime.getRuntime().addShutdownHook(new Thread(instance::onFinish));
        }
    }

    private void onFinish() {
        this.conexoes.forEach(ConexaoPool::jdbcClose);
    }

}
