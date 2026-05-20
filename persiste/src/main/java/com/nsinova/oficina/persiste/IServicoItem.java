package com.nsinova.oficina.persiste;

import com.nsinova.oficina.modelo.ServicoItem;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabs
 */
public interface IServicoItem {
    public ServicoItem manter(ServicoItem servico) throws SQLException;
    
    public List<ServicoItem> obterLista(String placaVeiculo) throws SQLException;
}
