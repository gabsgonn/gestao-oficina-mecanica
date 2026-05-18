package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.persiste.IPessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao implements IPessoa {

    // conexao recebida pelo daofabrica
    private final Connection conexao;

    // construtor que recebe a conexao aberta
    public PessoaDao(Connection conexao) {
        this.conexao = conexao;
    }

    //monta o select base sql
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("id, nome, cpf, email, nascimento ");
        sql.append("FROM gabrielgon.pessoa; ");
        return sql;
    }
    
    // converte uma linha do resultSet em objeto Pessoa modelo
    private com.nsinova.oficina.modelo.Pessoa montarItem(ResultSet rs) throws SQLException {
        return new com.nsinova.oficina.modelo.Pessoa(
            rs.getString("nome"),
            rs.getString("cpf"),
            rs.getString("email"),
            rs.getObject("nascimento", LocalDate.class)
        );
    }

    // percorre o resultSet e monta uma lista de pessoas
    private List<com.nsinova.oficina.modelo.Pessoa> montarLista(ResultSet rs) throws SQLException {
        List<com.nsinova.oficina.modelo.Pessoa> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(montarItem(rs));
        }
        return lista;
    }

    @Override
    public Pessoa manter(com.nsinova.oficina.modelo.Pessoa pessoa) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO gabrielgon.pessoa ");
        sql.append("(nome, cpf, email, nascimento) ");
        sql.append("VALUES (?, ?, ?, ?) ");
        // se o cpf ja existir, atualiza os outros campos
        sql.append("ON CONFLICT (cpf) DO UPDATE SET ");
        sql.append("nome = EXCLUDED.nome, ");
        sql.append("email = EXCLUDED.email, ");
        sql.append("nascimento = EXCLUDED.nascimento ");
        // devolve a linha salva para montar o objeto de retorno
        sql.append("RETURNING *");

        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            // seta cada ? na ordem do VALUES
            cmd.setString(1, pessoa.getNome());
            cmd.setString(2, pessoa.getCpf());
            cmd.setString(3, pessoa.getEmail());
            cmd.setObject(4, pessoa.getDataNascimento());
            try (ResultSet rs = cmd.executeQuery()) {
                // retorna a pessoa salva ou null se nao salvou
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }

    @Override
    public List<com.nsinova.oficina.modelo.Pessoa> obterLista(String nome) throws SQLException {
        StringBuilder sql = montarSQL();
        // se nome for null traz todo mundo, senao filtra
        if (nome != null) {
            sql.append("WHERE nome ILIKE ? ");
        }
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            if (nome != null) {
                // % antes e depois permite buscar nome parcial
                cmd.setString(1, "%" + nome + "%");
            }
            try (ResultSet rs = cmd.executeQuery()) {
                return montarLista(rs);
            }
        }
    }

    @Override
    public com.nsinova.oficina.modelo.Pessoa obterPorId(String id) throws SQLException {
        StringBuilder sql = montarSQL();
        // ::uuid faz o cast do String para UUID que o postgres espera
        sql.append("WHERE id = ?::uuid ");
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setString(1, id);
            try (ResultSet rs = cmd.executeQuery()) {
                // retorna a pessoa encontrada ou null se nao existir
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }
}