package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Servico;
import com.nsinova.oficina.negocio.util.Manter;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IServico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author treinamento
 */
public class ServicoNegocio extends NegocioBase {
    private IServico servicoDao;
    
    public ServicoNegocio(Conexao conexao) {
        super(conexao);
    }
    
    protected void inicializar() {
        servicoDao = DaoFabrica.criarServico(conexao);
    }
    
    public Servico manter(com.nsinova.oficina.modelo.Servico servico) throws Exception {
        try {
            return Manter.persist(servico, 
                    (com.nsinova.oficina.modelo.Servico servico2) -> manter2(servico2), 
                    conexao);
        } catch (Exception ex) {
            throw new Exception("Erro ao manter o Servico!", ex);
        }
    }
    
    private Servico manter2(com.nsinova.oficina.modelo.Servico servico) {
        try {
            if(servico.getDescricao()==null || 
                    servico.getDataInicio()==null  ||
                    servico.getVeiculo()==null) {
                throw new RuntimeException("Descricao, data inicio e veiculo não podem ser nulos!");
            } return servicoDao.manter(servico);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public com.nsinova.oficina.modelo.Servico obter(String placaVeiculo) throws SQLException{
        return servicoDao.obterLista(placaVeiculo).get(0);
    }
    
    public List<com.nsinova.oficina.modelo.Servico> obterLista(String placaVeiculo) throws SQLException {
        return servicoDao.obterLista(placaVeiculo);
    }
}
