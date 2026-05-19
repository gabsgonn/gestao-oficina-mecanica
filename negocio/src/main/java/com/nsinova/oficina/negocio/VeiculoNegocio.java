package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IVeiculo;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.negocio.util.Manter;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author treinamento
 */
public class VeiculoNegocio {
    private IVeiculo veiculoDao;
    private Conexao conexao;

    public VeiculoNegocio(Conexao conexao) {
        this.conexao = conexao;
        inicializar();
    }
    
    private void inicializar() {
        veiculoDao = DaoFabrica.criarVeiculo(conexao);
    }
    
    public Veiculo manter(com.nsinova.oficina.modelo.Veiculo veiculo) throws Exception {
        try {
            return Manter.persist(veiculo,
                    (com.nsinova.oficina.modelo.Veiculo veiculo2) -> manter2(veiculo2), 
                    conexao);
        } catch (Exception ex) {
            throw new Exception("Erro ao manter o Veiculo.", ex);
        }
    }
    
    private Veiculo manter2(com.nsinova.oficina.modelo.Veiculo veiculo) {
        try {
            if (veiculo.getPlaca()==null || veiculo.getProprietario()==null) {
                throw new RuntimeException("Placa ou veiculo não podem ser nulos!");
            } return veiculoDao.manter(veiculo);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public com.nsinova.oficina.modelo.Veiculo obter(String placaVeiculo) throws SQLException {
        return veiculoDao.obterPorPlaca(placaVeiculo);
    }
    
    public List<com.nsinova.oficina.modelo.Veiculo> obterLista(String placaVeiculo) throws SQLException {
        return veiculoDao.obterLista(placaVeiculo);
    }
    
    
}
