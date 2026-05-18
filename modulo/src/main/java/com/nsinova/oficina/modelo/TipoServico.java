package com.nsinova.oficina.modelo;

import java.math.BigDecimal;

/**
 *
 * @author gabs
 */
public class TipoServico extends ItemCatalogo {
    private int tempoEstimado;

    public TipoServico(int tempoEstimado, String codigo, String descricao, BigDecimal valor) {
        super(codigo, descricao, valor);
        this.tempoEstimado = tempoEstimado;
    }

    public int getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(int tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
}
