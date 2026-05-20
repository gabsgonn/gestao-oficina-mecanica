package com.nsinova.oficina.persiste;

import com.nsinova.oficina.modelo.TipoServico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabs
 */
public interface ITipoServico {
    public TipoServico manter(TipoServico tipoServico) throws SQLException;
    
    public List<TipoServico> obterLista(String descricao) throws SQLException;
    
    public TipoServico obterPorCodigo(String codigo) throws SQLException;
}
