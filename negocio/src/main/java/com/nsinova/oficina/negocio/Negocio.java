package com.nsinova.oficina.negocio;

import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Servico;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IServico;
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
            Conexao conexao = new Conexao("postgresql", "curso", "postgres", "pgsql$nsinova");

            // abre conexao local
            // Conexao conexao = new Conexao("postgresql", "oficina", "postgres", "postgresql026");
            
            // cria dao servico
            IServico daoServico = DaoFabrica.criarServico(conexao);
            IVeiculo daoVeiculo = DaoFabrica.criarVeiculo(conexao);

            
            // cria negocio
            PessoaNegocio pessoaNegocio = new PessoaNegocio(conexao);

            // testa cadastro
            Pessoa pessoa = new Pessoa("João", "98765432100", "joao@mail.com", LocalDate.of(1990, Month.MARCH, 15));
            Pessoa salva = pessoaNegocio.manter(pessoa);
            System.out.println("Pessoa salva: " + salva.getNome());

            // testa obterLista
            List<Pessoa> lista = pessoaNegocio.obterLista(null);
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