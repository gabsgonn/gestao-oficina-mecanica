package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.ItemCatalogo;
import com.nsinova.oficina.modelo.ServicoItem;
import com.nsinova.oficina.negocio.util.Manter;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IServicoItem;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabs
 */
public class ServicoItemNegocio extends NegocioBase {
    private IServicoItem servicoItemDao;

    public ServicoItemNegocio(Conexao conexao) {
        super(conexao);
    }
    
    @Override
    protected void inicializar() {
        servicoItemDao = DaoFabrica.criarServicoItem(conexao);
    }

    public ServicoItem adicionarItem(com.nsinova.oficina.modelo.ServicoItem servicoItem) throws Exception {
        try {
            return Manter.persist(servicoItem, 
                    (com.nsinova.oficina.modelo.ServicoItem servico2) -> manter2(servico2), 
                    conexao);
        } catch (Exception ex) {
            throw new Exception("Erro ao manter o Servico!", ex);
        }
    }
    
    private ServicoItem manter2(com.nsinova.oficina.modelo.ServicoItem servicoItem) {
        try {
            if(servicoItem.getServico()==null || 
                    servicoItem.getTipo()==null || 
                    servicoItem.getItem()==null || 
                    servicoItem.getQuantidade()==null) {
                throw new RuntimeException("Serviço, tipo, item ou quantidade não podem ser nulos!");
            } return servicoItemDao.manter(servicoItem);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public ServicoItem obter(long numeroServico) throws SQLException {
        return servicoItemDao.obterLista(numeroServico).get(0);
    }

    public List<ServicoItem> obterLista(long numeroServico) throws SQLException {
        return servicoItemDao.obterLista(numeroServico);
    }
}
