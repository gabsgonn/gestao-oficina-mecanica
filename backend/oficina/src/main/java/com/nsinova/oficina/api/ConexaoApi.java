package com.nsinova.oficina.api;

import com.nsinova.oficina.conexao.Conexao;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author gabs
 */
public class ConexaoApi {
    
    public Conexao conexaoApi() {
        
        Dotenv dotenv = Dotenv.configure().filename(".env").load();
        
        String dbUsuarioNs = dotenv.get("DB_USUARIO_NSINOVA");
        String dbSenhaNs = dotenv.get("DB_SENHA_NSINOVA");
        
        String dbUsuarioHome = dotenv.get("DB_USUARIO_HOME");
        String dbSenhaHome = dotenv.get("DB_SENHA_HOME");
        
        Conexao conexao = null;
        
        try {
            conexao = new Conexao("postgresql", "curso", dbUsuarioNs, dbSenhaNs);
            return conexao;
        } catch (Exception e) {            
            try {
                conexao = new Conexao("postgresql", "oficina", dbUsuarioHome, dbSenhaHome);
                return conexao;
            } catch (Exception ex) {
                return null; 
            }
        }
    }
}

