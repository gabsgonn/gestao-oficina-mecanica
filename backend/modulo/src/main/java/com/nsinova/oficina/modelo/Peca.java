package com.nsinova.oficina.modelo;

import java.math.BigDecimal;

/**
 *
 * @author gabs
 */
public class Peca extends ItemCatalogo {
    private int estoque;
    
    public Peca(int estoque, String codigo, String descricao, BigDecimal valor) {
        super(codigo, descricao, valor);
        this.estoque = estoque;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
