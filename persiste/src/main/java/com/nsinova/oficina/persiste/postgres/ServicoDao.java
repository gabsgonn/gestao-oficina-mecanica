package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.Servico;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.persiste.IServico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author gabs
 */
public class ServicoDao implements IServico {
    
    //conexao recebida pelo daofabrica
    private final Connection conexao;
    
    // construtor que receve a conexao aberta
    public ServicoDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    // monta o select base sql
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT numero, descricao, data_inicio, data_finalizacao, valor, placa_veiculo");
        sql.append("FROM gabrielgon.servico;");
        return sql;
    }
    
    // monta o select base sql
    private com.nsinova.oficina.modelo.Servico montarItem(ResultSet resultado) throws SQLException {
        return new com.nsinova.oficina.modelo.Servico(0, "descricao", LocalDateTime.MIN, new Veiculo());
    }

    //converte uma linha do resultSet em objeto Servico modelo
    @Override
    public Servico manter(Servico servico) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // percorre o resultSet e monta uma lista de Servicos
    @Override
    public List<Servico> obterLista(String placaVeiculo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Servico obterPorId(long numero) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}