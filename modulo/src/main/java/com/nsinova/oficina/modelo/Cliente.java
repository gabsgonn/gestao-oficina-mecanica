package com.nsinova.oficina.modelo;

import java.time.LocalDate;

/**
 *
 * @author gabs
 */
public class Cliente extends Pessoa {
    private Endereco endereco;
    
    public Cliente(String nome, String cpf, String email, LocalDate dataNascimento, Endereco endereco) {
        super(nome, cpf, email, dataNascimento);
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
