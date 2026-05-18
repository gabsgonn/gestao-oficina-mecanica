package com.nsinova.oficina.persiste;

import com.nsinova.oficina.modelo.Servico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabs
 */
public interface IServico {
    public Servico manter(Servico servico) throws SQLException;
    
    public List<Servico> obterLista(String placaVeiculo) throws SQLException;
    
    public Servico obterPorId(long numero) throws SQLException;
}
