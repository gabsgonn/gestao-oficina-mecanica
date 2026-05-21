package com.nsinova.oficina.persiste.postgres;

import com.nsinova.oficina.modelo.ItemCatalogo;
import com.nsinova.oficina.modelo.ServicoItem;
import com.nsinova.oficina.modelo.TipoItem;
import com.nsinova.oficina.persiste.IPeca;
import com.nsinova.oficina.persiste.IServicoItem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.nsinova.oficina.persiste.IServico;
import com.nsinova.oficina.persiste.ITipoServico;
import java.sql.PreparedStatement;
import java.util.ArrayList;

//senhafacilapi
/**
 *
 * @author gabs
 */
public class ServicoItemDao implements IServicoItem {
    
    private final Connection conexao;
    private final IServico daoServico;
    private final IPeca daoPeca;
    private final ITipoServico daoTipoServico;

    public ServicoItemDao(Connection conexao, IServico daoServico, IPeca daoPeca, ITipoServico daoTipoServico) {
        this.conexao = conexao;
        this.daoServico = daoServico;
        this.daoPeca = daoPeca;
        this.daoTipoServico = daoTipoServico;
    }
    
    private StringBuilder montarSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, servico, tipo, codigo, quantidade, descricao ");
        sql.append("FROM gabrielgon.servico_item ");
        return sql;
    }
    
    private com.nsinova.oficina.modelo.ServicoItem montarItem(ResultSet rs) throws SQLException {
        com.nsinova.oficina.modelo.Servico servico = daoServico.obterPorId(rs.getLong("servico"));
        
        // tratamento do Enum TipoItem
        String tipo = rs.getString("tipo");
        String codigo = rs.getString("codigo");

        ItemCatalogo item;
        TipoItem tipoItem;

        if ("P".equals(tipo)) {
            item = daoPeca.obterPorCodigo(codigo);
            tipoItem = TipoItem.PECA;
        } else {
            item = daoTipoServico.obterPorCodigo(codigo);
            tipoItem = TipoItem.SERVICO;
        }
        // final tratamento TipoItem
        
        com.nsinova.oficina.modelo.ServicoItem servicoItem = new com.nsinova.oficina.modelo.ServicoItem(
                servico,
                tipoItem,
                item,
                rs.getBigDecimal("quantidade"),
                rs.getString("descricao")
        );
        return servicoItem;
    }

    private List<com.nsinova.oficina.modelo.ServicoItem> montarLista(ResultSet rs) throws SQLException {
        List<com.nsinova.oficina.modelo.ServicoItem> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(montarItem(rs));
        }
        return lista;
    }
    
    
    
    @Override
    public ServicoItem manter(ServicoItem servicoItem) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO gabrielgon.servico_item (servico, tipo, codigo, quantidade, descricao) ");
        sql.append("VALUES (?, ?, ?, ?, ?) ");
        sql.append("ON CONFLICT (id) ");
        sql.append("DO UPDATE ");
        sql.append("SET servico=EXCLUDED.servico, tipo=EXCLUDED.tipo, codigo=EXCLUDED.codigo, quantidade=EXCLUDED.quantidade, descricao=EXCLUDED.descricao ");
        sql.append("RETURNING * ");
        
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            cmd.setLong(1, servicoItem.getServico().getNumero());
            cmd.setString(2, servicoItem.getTipo().toString());
            cmd.setString(3, servicoItem.getItem().getCodigo());
            cmd.setBigDecimal(4, servicoItem.getQuantidade());
            cmd.setString(5, servicoItem.getDescricao());
            try (ResultSet rs = cmd.executeQuery()) {
                return rs.next() ? montarItem(rs) : null;
            }
        }
    }
    
    @Override
    public List<ServicoItem> obterLista(long numeroServico) throws SQLException {
        
        StringBuilder sql = montarSQL();
        
        if (numeroServico > 0) {
            sql.append("WHERE servico = ? ");
        }
        
        try (PreparedStatement cmd = conexao.prepareStatement(sql.toString())) {
            if (numeroServico > 0) {
                cmd.setLong(1, numeroServico);
            }
            try (ResultSet rs = cmd.executeQuery()) {
                return montarLista(rs);
            }
        }
    }
}