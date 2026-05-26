package com.nsinova.oficina.persiste;

import com.nsinova.oficina.modelo.Pessoa;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author gabs
 */
public interface IPessoa {
    public Pessoa manter(Pessoa pessoa) throws SQLException;
    
    public List<Pessoa> obterLista(String nome) throws SQLException;
    
    public Pessoa obterPorId(String id) throws SQLException;

    public List<Pessoa> obterListaSomenteNome(String nomeCliente) throws SQLException;
}
