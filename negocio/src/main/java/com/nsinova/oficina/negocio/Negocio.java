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
            
            // cria negocio veiculo
            VeiculoNegocio veiculoNegocio = new VeiculoNegocio(conexao);

            // busca pessoa para vincular ao veiculo
            Pessoa proprietario = pessoaNegocio.obterLista("João").get(0);

            // testa cadastro
            Veiculo veiculo = new Veiculo("XYZ9K88", proprietario);
            Veiculo salvo = veiculoNegocio.manter(veiculo);
            System.out.println("Veiculo salvo: " + salvo.getPlaca() + " - " + salvo.getProprietario().getNome());

            // testa obter
            Veiculo encontrado = veiculoNegocio.obter("XYZ9K88");
            System.out.println("Veiculo encontrado: " + encontrado.getPlaca());
            
            // fecha conexao
            conexao.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}