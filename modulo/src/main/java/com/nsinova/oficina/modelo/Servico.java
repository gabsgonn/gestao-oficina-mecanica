package com.nsinova.oficina.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabs
 */
public class Servico {
    private long numero;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinalizacao;
    private BigDecimal valor;
    private Veiculo veiculo;

    // construtor sem 'BigDecimal valor ' e sem 'dataFinalizacao pois vao ser definidos quando fechar a ordem de servico - que éresponsabilidade da camada de negocio
    public Servico(long numero, String descricao, LocalDateTime dataInicio, Veiculo veiculo) {
        this.numero = numero;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.veiculo = veiculo;
    }
    
    // criando lista de itens
    private List<ServicoItem> itens = new ArrayList<>();
    
    public void adicionarItem(ServicoItem item) {
        this.itens.add(item);
    }

    public void removerItem(ServicoItem item) {
        this.itens.remove(item);
    }

    public List<ServicoItem> getItens() {
        return itens;
    }
    
    // quantidade * valor
    public BigDecimal calcularTotal() {
        //inicia total
        BigDecimal total = BigDecimal.ZERO;
        
        //para cada ServicoItem item na lista ServicoItem itens
        for (ServicoItem item : itens) {
            //iterar sobnre  o total
            total = total.add(item.calcularSubtotal());
        }
        //retorna resultado da iteracao
        return total; 
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
     
}
