package com.nsinova.oficina.modelo;

import java.math.BigDecimal;

/**
 *
 * @author gabs
 */
public class ServicoItem {
    private Servico servico;
    private TipoItem tipo;
    private ItemCatalogo item;
    private BigDecimal quantidade;
    private String descricao;

    public ServicoItem(Servico servico, TipoItem tipo, ItemCatalogo item, BigDecimal quantidade, String descricao) {
        this.servico = servico;
        this.tipo = tipo;
        this.item = item;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public ItemCatalogo getItem() {
        return item;
    }

    public void setItem(ItemCatalogo item) {
        this.item = item;
    }
    
    public BigDecimal calcularSubtotal() {
        return item.getValor().multiply(this.quantidade);
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
