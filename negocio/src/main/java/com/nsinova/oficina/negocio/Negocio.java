package com.nsinova.oficina.negocio;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.modelo.Servico;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IPessoa;
import com.nsinova.oficina.persiste.IServico;
import com.nsinova.oficina.persiste.IVeiculo;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            
            // cria dao servico
            IServico daoServico = DaoFabrica.criarServico(conexao);
            IVeiculo daoVeiculo = DaoFabrica.criarVeiculo(conexao);

            // busca o veiculo cadastrado para vincular ao servico
            Veiculo veiculo = daoVeiculo.obterPorPlaca("ABC1234");

            // testa insert
            Servico servico = new Servico(
                0,
                "Troca de óleo",
                LocalDateTime.now(),
                veiculo
            );
            daoServico.manter(servico);
            System.out.println("Servico inserido!");

            // testa obterLista por placa
            List<Servico> lista = daoServico.obterLista("ABC1234");
            for (Servico s : lista) {
                System.out.println(s.getNumero() + " - " + s.getDescricao());
            }
            

            // fecha conexao
            conexao.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}