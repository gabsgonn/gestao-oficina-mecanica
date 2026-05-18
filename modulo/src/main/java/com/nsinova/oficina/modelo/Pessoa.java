package com.nsinova.oficina.modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author gabs
 */
public class Pessoa {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    
    public Pessoa(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        // calcula periodo entre dataNascimento e data atual, e puxa so os anos do calculo
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    
}
