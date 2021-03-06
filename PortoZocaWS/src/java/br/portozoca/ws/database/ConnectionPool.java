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

    /** Instance of the Singleton */
    private static ConnectionPool instance;
    /** Pool Properties */
    private static PoolProperties properties;

    /** List of pool connections */
    private final List<ConexaoPool> conexoes;

    /**
     * Private constructor
     */
    private ConnectionPool() {
        this.conexoes = new ArrayList<>();
    }

    /**
     * Adds a connection to the pool
     *
     * @param conn
     * @throws DBException
     */
    public synchronized void addConnection(ConexaoPool conn) throws DBException {
        if (conexoes.size() >= properties.getMaxConnections()) {
            throw new DBException("Pool is full!");
        }
        conexoes.add(conn);
    }

    /**
     * Returns a connection of the pool
     *
     * @return ConexaoPool
     * @throws EmptyPoolException
     */
    public ConexaoPool getConnection() throws EmptyPoolException, DBException {
        ConexaoPool conn = getPoolConnection(0);
        return conn;
    }

    /**
     * Returns a connection from the pool, with all the logic
     *
     * @return ConexaoPool
     * @throws EmptyPoolException
     */
    private ConexaoPool getPoolConnection(int tries) throws EmptyPoolException, DBException {
        // Recursive protection for not entering in infinite loop
        if (tries > properties.getMaxTries()) {
            throw new DBException("Failed to create a new Connection after " + properties.getMaxTries() + " tries.");
        }
        // Synchronized
        synchronized (conexoes) {
            // Try to get a free connection on the pool
            Optional<ConexaoPool> opt = conexoes.stream().filter((c) -> c.isFree()).findFirst();
            if (opt.isPresent()) {
                return opt.get();
            }
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
        return getPoolConnection(++tries);
    }

    /**
     * Returns the Pool Singleton Instance
     *
     * @return ConexaoPool
     */
    public static ConnectionPool get() {
        if (instance == null) {
            instantiate();
        }
        return instance;
    }

    /**
     * Instantiate the pool using double checked synchronization to optimize
     * performance
     */
    private static synchronized void instantiate() {
        if (instance == null) {
            instance = new ConnectionPool();
            properties = PoolProperties.create();
            // Add's a shutdown hook to close all connections
            Runtime.getRuntime().addShutdownHook(new Thread(instance::onFinish));
        }
    }

    /**
     * Executed by the runtime on the finish of the application
     */
    private void onFinish() {
        this.conexoes.forEach(ConexaoPool::jdbcClose);
    }

}
