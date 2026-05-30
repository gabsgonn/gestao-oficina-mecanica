package com.nsinova.oficina.modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author gabs
 */
public class Pessoa {
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private int idade;
    
    public Pessoa(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
    
    public int getIdade() {
        // calcula periodo entre dataNascimento e data atual, e puxa so os anos do calculo
        if (dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    };

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.idade = getIdade();
    }
}
