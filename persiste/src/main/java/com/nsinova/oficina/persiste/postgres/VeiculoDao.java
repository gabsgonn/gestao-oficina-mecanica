package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.persiste.IVeiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabs
 */
public class VeiculoDao implements IVeiculo {
    
    // conexao recebida pelo daofabrica
    private final Connection conexao;
    
    // construtor que recebe a conexao aberta
    public VeiculoDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    //monta o select base sql
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT numero, descricao, data_inicio, data_finalizacao, valor, placa_veiculo");
        sql.append("FROM gabrielgon.servico;");
        return sql;
    }
    
    // converte uma linha do resultSet em objeto Veiculo modelo
    private com.nsinova.oficina.modelo.Veiculo montarItem(ResultSet resultado) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(resultado.getString("propretario"));
        return new com.nsinova.oficina.modelo.Veiculo(
                resultado.getString("placa"),
                pessoa
        );
    }
    
    // percorre o resultSet e monta uma lista de pessoas
    private List<com.nsinova.oficina.modelo.Veiculo> montarLista(ResultSet rs) throws SQLException {
        List<com.nsinova.oficina.modelo.Veiculo> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(montarItem(rs));
        }
        return lista;
    }

    @Override
    public Veiculo manter(Veiculo veiculo) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO public.veiculo (placa, proprietario)");
        sql.append("VALUES (?, ?)");
        sql.append("ON CONFLICT (placa) DO UPDATE SET");
        sql.append("proprietario = EXCLUDED.proprietario");
        sql.append("RETURNING *");
        
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setString(1, veiculo.getPlaca());
            cmd.setString(2, veiculo.getProprietario().getId());
            try (ResultSet rs = cmd.executeQuery()) {
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }

    /**
     *  SELECT * 
        FROM veiculos
        WHERE placa = 'ABC1D23';
     */
    
    @Override
    public List<com.nsinova.oficina.modelo.Veiculo> obterLista(String placa) throws SQLException {
        StringBuilder sql = montarSQL();
        if (placa != null) {
            sql.append("WHERE placa ILIKE ? ");
        }
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            if(placa != null) {
                // % antes e depois permite buscar nome parcial
                cmd.setString(1, "%" + placa + "%");
            }
            try (ResultSet rs = cmd.executeQuery()) {
                return montarLista(rs);
            }
            
            
        }
    }

    @Override
    public Veiculo obterPorPlaca(String placa) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
