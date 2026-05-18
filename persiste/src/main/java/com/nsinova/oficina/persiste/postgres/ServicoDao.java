package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.persiste.IServico;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author gabs
 */
public class ServicoDao implements IServico {
    
    private final Connection conexao;
    
    public ServicoDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT numero, descricao, data_inicio, data_finalizacao, valor, placa_veiculo");
        sql.append("FROM gabrielgon.servico;");
        return sql;
    }
    
    private com.nsinova.oficina.modelo.Servico montarItem(ResultSet resultado) throws SQLException {
        return new com.nsinova.oficina.modelo.Servico(0, descricao, LocalDateTime.MIN, veiculo)
    }
    
    
}


/**
 * SELECT numero, descricao, data_inicio, data_finalizacao, valor, placa_veiculo
 * FROM gabrielgon.servico;
 */