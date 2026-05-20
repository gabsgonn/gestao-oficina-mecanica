package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.Peca;
import com.nsinova.oficina.persiste.IPeca;
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
public class PecaDao implements IPeca {

    private final Connection conexao;

    public PecaDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao, valor, estoque ");
        sql.append("FROM gabrielgon.peca ");
        return sql;
    }

    // monta o select base sql
    private com.nsinova.oficina.modelo.Peca montarItem(ResultSet rs) throws SQLException {
        com.nsinova.oficina.modelo.Peca peca = new com.nsinova.oficina.modelo.Peca(
                rs.getInt("estoque"),
                rs.getString("codigo"),
                rs.getString("descricao"),
                rs.getBigDecimal("valor")
        );
        return peca;
    }

    private List<com.nsinova.oficina.modelo.Peca> montarLista(ResultSet rs) throws SQLException {
        List<com.nsinova.oficina.modelo.Peca> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(montarItem(rs));
        }
        return lista;
    }
    
//    Gerar SQL - DBeaver:
//    INSERT INTO gabrielgon.peca
//    (codigo, descricao, valor, estoque)
//    SELECT src.codigo, src.descricao, src.valor, src.estoque
//    FROM SOURCE_TABLE AS src
//    ON CONFLICT (codigo)
//    /* or you may use [DO NOTHING;] */
//    DO UPDATE 
//    SET descricao=EXCLUDED.descricao, valor=EXCLUDED.valor, estoque=EXCLUDED.estoque;
    
    @Override
    public Peca manter(Peca peca) throws SQLException {
        
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO gabrielgon.peca (codigo, descricao, valor, estoque) ");
        sql.append("VALUES (?, ?, ?, ?) ");
        sql.append("ON CONFLICT (codigo) ");
        sql.append("DO UPDATE ");
        sql.append("SET descricao=EXCLUDED.descricao, valor=EXCLUDED.valor, estoque=EXCLUDED.estoque ");
        sql.append("RETURNING * ");

        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setString(1, peca.getCodigo());
            cmd.setString(2, peca.getDescricao());
            cmd.setBigDecimal(3, peca.getValor());
            cmd.setInt(4, peca.getEstoque());
            try (ResultSet rs = cmd.executeQuery()) {
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }

    @Override
    public List<Peca> obterLista(String descricao) throws SQLException {
        List<Peca> lista = new ArrayList<>();
        StringBuilder sql = montarSQL();

        // filtra pela placa se o parametro nao for nulo
        if (descricao != null && !descricao.trim().isEmpty()) {
            sql.append("WHERE placa_veiculo ILIKE ? ");
        }

        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            if (descricao != null && !descricao.trim().isEmpty()) {
                cmd.setString(1, "%" + descricao + "%");
            }
            try (ResultSet rs = cmd.executeQuery()) {
                return montarLista(rs);
            }
        }
    }

    @Override
    public Peca obterPorCodigo(String codigo) throws SQLException {
        StringBuilder sql = montarSQL();

        sql.append("WHERE codigo = ? ");

        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setString(1, codigo);
            try (ResultSet rs = cmd.executeQuery()) {
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }
    
}
