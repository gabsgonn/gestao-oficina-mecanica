package com.nsinova.oficina.modelo;

/**
 *
 * @author gabs
 */
public class Veiculo {
    private String placa;
    private Pessoa proprietario;

    public Veiculo(String placa, Pessoa proprietario) {
        this.placa = placa;
        this.proprietario = proprietario;
    }

    public Veiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Pessoa getProprietario() {
        return proprietario;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }    
}
