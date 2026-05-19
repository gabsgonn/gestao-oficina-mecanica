package com.nsinova.oficina.negocio.util;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.negocio.interfaces.IManter;

/**
 *
 * @author treinamento
 */
public class Manter {
    public static <K, T> K persist(T t, IManter<T, K> iManter, 
            Conexao conexao) throws Exception {
        boolean emTransacao = conexao.emTransacao();
        try{
            
            if(!emTransacao){
                conexao.inicializarTransacao();
            }
            
            K k = iManter.persist(t);
            
            if(!emTransacao){
                conexao.confirmarTransacao();
            }
            
            return k;
        }catch(Exception ex){
            if(!emTransacao){
                conexao.reverterTransacao();
            }
            throw ex;
        }
    }
}
