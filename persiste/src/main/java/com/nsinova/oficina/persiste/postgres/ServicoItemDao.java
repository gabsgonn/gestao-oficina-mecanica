package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.ServicoItem;
import com.nsinova.oficina.persiste.IServicoItem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.nsinova.oficina.persiste.IServico;

/**
 *
 * @author gabs
 */
public class ServicoItemDao implements IServicoItem {
    
    private final Connection conexao;
    private final IServico daoServico;

    public ServicoItemDao(Connection conexao, IServico daoServico) {
        this.conexao = conexao;
        this.daoServico = daoServico;
    }
    
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, servico, tipo, codigo, quantidade, descricao ");
        sql.append("FROM gabrielgon.servico_item ");
        return sql;
    }
    
    private com.nsinova.oficina.modelo.ServicoItem montarItem(ResultSet rs) throws SQLException {
        com.nsinova.oficina.modelo.Servico servico = daoServico.obterPorId(rs.getLong("servico"));
        com.nsinova.oficina.modelo.ServicoItem servicoItem = new com.nsinova.oficina.modelo.ServicoItem(
                servico,
                rs.getString("servico")
        );
        return servicoItem;
    }
    // servico, tipo, codigo, quantidade, descricao

    @Override
    public ServicoItem manter(ServicoItem servico) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ServicoItem> obterLista(String placaVeiculo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
