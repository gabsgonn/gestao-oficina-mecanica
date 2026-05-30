package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;

/**
 *
 * @author gabs
 * 
 * classe base para as classes de negócio.
 * centraliza a inicialização da conexão
 */
public abstract class NegocioBase {
    //inicia conexao
    protected Conexao conexao;

    //inicia negocioBase 
    public NegocioBase(Conexao conexao) {
        this.conexao = conexao;
        inicializar();
    }

    protected abstract void inicializar();
}
