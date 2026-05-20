package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.TipoServico;
import com.nsinova.oficina.persiste.ITipoServico;
import java.math.BigDecimal;
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
public class TipoServicoDao implements ITipoServico {
    private final Connection conexao;

    public TipoServicoDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    // monta o select base sql
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao, valor, tempo_estimado ");
        sql.append("FROM gabrielgon.tipo_servico ");
        return sql;
    }

    // monta o select base sql
    private com.nsinova.oficina.modelo.TipoServico montarItem(ResultSet rs) throws SQLException {
        com.nsinova.oficina.modelo.TipoServico tipoServico = new com.nsinova.oficina.modelo.TipoServico(
                rs.getInt("tempo_estimado_minutos "),
                rs.getString("codigo "),
                rs.getString("descricao "),
                rs.getBigDecimal("valor ")
        );
        return tipoServico;
    }
    
    // percorre o resultSet e monta uma lista de Servicos
    private List<com.nsinova.oficina.modelo.TipoServico> montarLista(ResultSet rs) throws SQLException {
        List<com.nsinova.oficina.modelo.TipoServico> lista = new ArrayList<>();
        
        while (rs.next()) {
            lista.add(montarItem(rs));
        }
        return lista;
    }
    
    // Gerar SQL - DBeaver:
    //INSERT INTO gabrielgon.tipo_servico (codigo, descricao, valor, tempo_estimado_minutos)
    //SELECT src.codigo, src.descricao, src.valor, src.tempo_estimado_minutos
    //FROM SOURCE_TABLE AS src
    //ON CONFLICT (codigo)
    ///* or you may use [DO NOTHING;] */
    //DO UPDATE 
    //SET descricao=EXCLUDED.descricao, valor=EXCLUDED.valor, tempo_estimado_minutos=EXCLUDED.tempo_estimado_minutos;
    
    @Override
    public TipoServico manter(TipoServico tipoServico) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO gabrielgon.tipo_servico (codigo, descricao, valor, tempo_estimado_minutos) ");
        sql.append("VALUES (?, ?, ?, ?) ");
        sql.append("ON CONFLICT (codigo) ");
        sql.append("DO UPDATE ");
        sql.append("SET descricao=EXCLUDED.descricao, valor=EXCLUDED.valor, tempo_estimado_minutos=EXCLUDED.tempo_estimado_minutos ");
        sql.append("RETURNING * ");

        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setString(1, tipoServico.getCodigo());
            cmd.setString(2, tipoServico.getDescricao());
            cmd.setBigDecimal(3, tipoServico.getValor());
            cmd.setInt(4, tipoServico.getTempoEstimado());
            try (ResultSet rs = cmd.executeQuery()) {
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }
    
//                rs.getInt(tipoServico.getTempoEstimado()),
//                rs.getString(tipoServico.getDescricao()),
//                rs.getBigDecimal(tipoServico.getValor()),
//                rs.get()
    
    @Override
    public List<TipoServico> obterLista(String descricao) throws SQLException {
        List<TipoServico> lista = new ArrayList<>();
        
        StringBuilder sql = montarSQL();
        
        if (descricao != null && !descricao.trim().isEmpty()) {
            sql.append("WHERE descricao ILIKE ? ");
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
    public TipoServico obterPorCodigo(String codigo) throws SQLException {
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