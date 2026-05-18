package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IPessoa;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 *
 * @author treinamento
 */
public class Negocio {

    public static void main(String[] args) {
        try {
            // abre conexao
            Conexao conexao = new Conexao("postgresql", "curso", "postgres", "pgsql$nsinova");

            // cria o dao
            IPessoa daoPessoa = DaoFabrica.criarPessoa(conexao);

            // testa insert
            Pessoa pessoa = new Pessoa("Gabriel", "12345678901", "gab@mail.com", LocalDate.of(2004, Month.NOVEMBER, 4));
            daoPessoa.manter(pessoa);
            System.out.println("Pessoa inserida!");

            // testa obterLista
            List<Pessoa> lista = daoPessoa.obterLista(null);
            for (Pessoa p : lista) {
                System.out.println(p.getNome() + " - " + p.getCpf());
            }

            // fecha conexao
            conexao.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
