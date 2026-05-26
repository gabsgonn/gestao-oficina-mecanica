package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Servico;
import com.nsinova.oficina.modelo.ServicoItem;
import com.nsinova.oficina.negocio.util.Manter;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IServico;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author treinamento
 */
public class ServicoNegocio extends NegocioBase {
    private IServico servicoDao;
    private ServicoItemNegocio servicoItemNegocio;

    public ServicoNegocio(Conexao conexao) {
        super(conexao);
    }
    
    @Override
    protected void inicializar() {
        servicoDao = DaoFabrica.criarServico(conexao);
        servicoItemNegocio = new ServicoItemNegocio(conexao);
    }
    
    public ServicoItem adicionarItem(Servico servico, ServicoItem item) throws Exception {
        ServicoItem salvo = servicoItemNegocio.adicionarItem(item);
        servico.adicionarItem(salvo);
        return salvo;
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
    
    public Servico finalizar(Servico servico) throws Exception {
        if (servico.getDataFinalizacao() != null) {
            throw new Exception("Serviço já finalizado!");
        }
        if (servico.getItens().isEmpty()) {
            throw new Exception("Serviço não possui itens!");
        }
        servico.setDataFinalizacao(LocalDate.now());
        servico.setValor(servico.calcularTotal());
        return manter(servico);
    }
    
    public com.nsinova.oficina.modelo.Servico obter(String placaVeiculo) throws SQLException{
        return servicoDao.obterLista(placaVeiculo).get(0);
    }
    
    public List<com.nsinova.oficina.modelo.Servico> obterLista(String placaVeiculo) throws SQLException {
        return servicoDao.obterLista(placaVeiculo);
    }
    
    public List<com.nsinova.oficina.modelo.Servico> obterNumeroEVeiculo() throws SQLException {
        return servicoDao.obterNumeroEVeiculo();
    }
}
