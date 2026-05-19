package com.nsinova.oficina.negocio.interfaces;

/**
 *
 * @author treinamento
 * @param <T>
 * @param <K>
 */
public interface IManter<T, K> {
    
    /**
     *  Interface funcional 
     *  que insere/altera/deleta informações 
     *  do Banco de dados.
     *  Recebe objeto do tipo T e retorna
     *  objeto do tipo K.
     * @param t
     * @return
     */
    public K persist(T t); 
    
}
