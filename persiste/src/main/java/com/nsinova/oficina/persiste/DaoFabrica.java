package com.nsinova.oficina.persiste;

import com.nsinova.oficina.conexao.Conexao;

/**
 *
 * @author gabs
 */
public class DaoFabrica {
    
    public static IPessoa criarPessoa(Conexao conexao) {
        if("postgresql".equalsIgnoreCase(conexao.getProvedor())){
            return new com.nsinova.oficina.persiste.postgres.PessoaDao(conexao.getConnection());
        }
        return null;
    }
    
}
