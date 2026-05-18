package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IPessoa;
import com.nsinova.oficina.persiste.IVeiculo;
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
            // abre conexao nsinova
            // Conexao conexao = new Conexao("postgresql", "curso", "postgres", "pgsql$nsinova");

            // abre conexao local
            Conexao conexao = new Conexao("postgresql", "oficina", "postgres", "postgresql026");
            
            // cria o dao
            IPessoa daoPessoa = DaoFabrica.criarPessoa(conexao);

            // testa insert
            // Pessoa pessoa = new Pessoa("Maria", "12345678909", "maria@mail.com", LocalDate.of(2005, Month.JUNE, 10));
            // daoPessoa.manter(pessoa);
            // System.out.println("Pessoa inserida!");

            // testa obterLista
//            List<Pessoa> lista = daoPessoa.obterLista(null);
//            for (Pessoa p : lista) {
//                System.out.println(p.getNome() + " - " + p.getCpf());
//            }

            // cria dao veiculo
            IVeiculo daoVeiculo = DaoFabrica.criarVeiculo(conexao);
            
            // get pessoa para insert
            Pessoa unicaPessoa = daoPessoa.obterLista("Gabriel").get(0);
            
            //teste insert
            Veiculo veiculo = new Veiculo("ABC1234", unicaPessoa);
            daoVeiculo.manter(veiculo);
            System.out.println("Veiculo inserido para: " + unicaPessoa.getNome());

            // fecha conexao
            conexao.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
