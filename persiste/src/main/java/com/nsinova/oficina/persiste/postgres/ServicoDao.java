package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.Servico;
import com.nsinova.oficina.persiste.IServico;
import com.nsinova.oficina.persiste.IVeiculo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gabs
 */
public class ServicoDao implements IServico {
    
    //conexao recebida pelo daofabrica
    private final Connection conexao;
    private final IVeiculo daoVeiculo;
    
    public ServicoDao(Connection conexao, IVeiculo daoVeiculo) {
        this.conexao = conexao;
        this.daoVeiculo = daoVeiculo;
    }

    // monta o select base sql
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT numero, descricao, data_inicio, data_finalizacao, valor, placa_veiculo ");
        sql.append("FROM gabrielgon.servico ");
        return sql;
    }

    // monta o select base sql
    private com.nsinova.oficina.modelo.Servico montarItem(ResultSet rs) throws SQLException {
        // inicio um get placa veiculo já em string
        com.nsinova.oficina.modelo.Veiculo placa_veiculo = daoVeiculo.obterPorPlaca(rs.getString("placa_veiculo"));
        com.nsinova.oficina.modelo.Servico servico = new com.nsinova.oficina.modelo.Servico(
                rs.getLong("numero"),
                rs.getString("descricao"), 
                rs.getObject("data_inicio", OffsetDateTime.class).toLocalDate(),
                placa_veiculo
        );
        // se valor não for nulo retorna ele
        if (rs.getBigDecimal("valor") != null) {
            servico.setValor(rs.getBigDecimal("valor"));
        }
        // se data_finalizacao não for nulo retorna ele
        OffsetDateTime dataFin = rs.getObject("data_finalizacao", OffsetDateTime.class);
        if (dataFin != null) {
            servico.setDataFinalizacao(dataFin.toLocalDate());
        }
        return servico;
    }
    
    // percorre o resultSet e monta uma lista de Servicos
    private List<com.nsinova.oficina.modelo.Servico> montarLista(ResultSet rs) throws SQLException {
        List<com.nsinova.oficina.modelo.Servico> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(montarItem(rs));
        }
        return lista;
    }

    //converte uma linha do resultSet em objeto Servico modelo
    @Override
    public Servico manter(Servico servico) throws SQLException {
        String placa = servico.getVeiculo().getPlaca();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO gabrielgon.servico (descricao, data_inicio, data_finalizacao, valor, placa_veiculo) ");
        sql.append("VALUES (?, ?, ?, ?, ?) ");
        sql.append("ON CONFLICT (numero) DO UPDATE SET ");
        sql.append("descricao = EXCLUDED.descricao, ");
        sql.append("data_finalizacao = EXCLUDED.data_finalizacao, ");
        sql.append("valor = EXCLUDED.valor ");
        sql.append("RETURNING * ");
        
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            //seca cada ? na ordem de VALUES
            cmd.setString(1, servico.getDescricao());
            cmd.setDate(2, Date.valueOf(servico.getDataInicio()));

//            cmd.setDate(3, Date.valueOf(servico.getDataFinalizacao()));
//            cmd.setBigDecimal(4, servico.getValor());

            if (servico.getDataFinalizacao() != null) {
                cmd.setDate(3, Date.valueOf(servico.getDataFinalizacao()));
            } else {
                cmd.setNull(3, java.sql.Types.DATE);
            }

            if (servico.getValor() != null) {
                cmd.setBigDecimal(4, servico.getValor());
            } else {
                cmd.setNull(4, java.sql.Types.NUMERIC);
            }

            cmd.setString(5, placa);
            try (ResultSet rs = cmd.executeQuery()) {
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }

    // percorre o resultSet e monta uma lista de Servicos
    @Override
    public List<Servico> obterLista(String placaVeiculo) throws SQLException {
        StringBuilder sql = montarSQL();

        // filtra pela placa se o parametro nao for nulo
        if (placaVeiculo != null && !placaVeiculo.trim().isEmpty()) {
            sql.append("WHERE placa_veiculo ILIKE ? ");
        }

        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            if (placaVeiculo != null && !placaVeiculo.trim().isEmpty()) {
                cmd.setString(1, "%" + placaVeiculo + "%");
            }
            try (ResultSet rs = cmd.executeQuery()) {
                return montarLista(rs);
            }
        }
    }

    @Override
    public Servico obterPorId(long numero) throws SQLException {    
        StringBuilder sql = montarSQL();    
        sql.append("WHERE numero = ? ");
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setLong(1, numero);
            try (ResultSet rs = cmd.executeQuery()) {
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }

    // ========== LISTAR SOMENTE numero e veiculo ===========
    @Override
    public List<Servico> obterNumeroEVeiculo() throws SQLException {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT numero, placa_veiculo FROM gabrielgon.servico ";

        try (PreparedStatement cmd = conexao.prepareStatement(sql);
             ResultSet rs = cmd.executeQuery()) {
            
            while (rs.next()) {
                com.nsinova.oficina.modelo.Veiculo veiculo = daoVeiculo.obterPorPlaca(rs.getString("placa_veiculo"));
                
                Servico servico = new Servico(rs.getLong("numero"), "", null, veiculo);
                lista.add(servico);
            }
        }
        return lista;
    }
}