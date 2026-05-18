package com.nsinova.oficina.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabs
 */
public class Conexao_old {

    private Connection connection;
    private String provedor;
    
    /**
     * @param provedor
     * @param banco
     * @param user
     * @param senha
     * @throws Exception
     */
    public Conexao_old(String provedor,
            String banco,
            String user,
            String senha) throws Exception {
        this.provedor = provedor;
        inicializar(banco, user, senha);
    }

    private void inicializar(String banco,
            String user,
            String senha) throws Exception {
        Class.forName("org.postgresql.Driver").newInstance();
        connection = DriverManager.getConnection(
                "jdbc:" + provedor + "://127.0.0.1:5432/" + banco + "?"
                + "user=" + user + "&password=" + senha);
    }

    public String getProvedor() {
        return provedor;
    }

    public Connection getConnection() {
        return connection;
    }

    public void reverterTransacao() throws Exception {
        if (connection != null && !connection.isClosed() && !connection.getAutoCommit()) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }

    public void inicializarTransacao() throws Exception {
        connection.setAutoCommit(false);
    }

    public void confirmarTransacao() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    public Boolean isClosed() throws Exception {
        if (connection != null) {
            return connection.isClosed();
        }
        return null;
    }

    public void close() throws Exception {
        connection.close();
    }
    
    public boolean emTransacao() throws SQLException{
        return !connection.getAutoCommit();
    }

}
