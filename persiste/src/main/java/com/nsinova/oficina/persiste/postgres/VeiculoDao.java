package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.persiste.IPessoa;
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
    private final IPessoa daoPessoa;
    
    // construtor que recebe a conexao aberta
    public VeiculoDao(Connection conexao, IPessoa daoPessoa) {
        this.conexao = conexao;
        this.daoPessoa = daoPessoa;
    }
    
    //monta o select base sql
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT placa, proprietario ");
        sql.append("FROM gabrielgon.veiculo ");
        return sql;
    }
    
    // converte uma linha do resultSet em objeto Veiculo modelo
    private com.nsinova.oficina.modelo.Veiculo montarItem(ResultSet rs) throws SQLException {
        com.nsinova.oficina.modelo.Pessoa pessoa = daoPessoa.obterPorId(rs.getString("proprietario"));
        return new com.nsinova.oficina.modelo.Veiculo(
                rs.getString("placa"),
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
        //get proprietario
        String proprietario = veiculo.getProprietario().getId();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO gabrielgon.veiculo (placa, proprietario) ");
        sql.append("VALUES (?, ?::uuid) ");
        // se o cpf ja existir, atualiza os outros campos
        sql.append("ON CONFLICT (placa) DO UPDATE SET ");
        sql.append("proprietario = EXCLUDED.proprietario ");
        // devolve a linha salva para montar o objeto de retorno
        sql.append("RETURNING * ");
        
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            // seta cada ? na ordem do VALUES
            cmd.setString(1, veiculo.getPlaca());
            cmd.setString(2, proprietario);
            try (ResultSet rs = cmd.executeQuery()) {
                // retorna a pessoa salva ou null se nao salvou
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }
    
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
    public com.nsinova.oficina.modelo.Veiculo obterPorPlaca(String placa) throws SQLException {
        StringBuilder sql = montarSQL();
        sql.append("WHERE placa = ? ");
        try(PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setString(1, placa);
            try (ResultSet rs = cmd.executeQuery()) {
                // retorna o veiculo encontrado ou null se não existir
                return rs.next() ? montarItem(rs) : null;
            }
        }

    }
    
}
