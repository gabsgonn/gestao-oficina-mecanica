package com.nsinova.oficina.persiste;

import com.nsinova.oficina.modelo.Peca;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabs
 */
public interface IPeca {
    public Peca manter(Peca peca) throws SQLException;
    
    public List<Peca> obterLista(String descricao) throws SQLException;
    
    public Peca obterPorCodigo(String codigo) throws SQLException;
}