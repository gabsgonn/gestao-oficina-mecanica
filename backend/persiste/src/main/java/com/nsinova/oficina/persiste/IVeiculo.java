package com.nsinova.oficina.persiste;

import com.nsinova.oficina.modelo.Veiculo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabs
 */
public interface IVeiculo {
    public Veiculo manter(Veiculo veiculo) throws SQLException;
    
    public List<Veiculo> obterLista(String placa) throws SQLException;
    
    public Veiculo obterPorPlaca(String placa) throws SQLException;    
}
