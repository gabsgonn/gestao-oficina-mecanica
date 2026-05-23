package com.nsinova.oficina.negocio;

import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Servico;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.persiste.DaoFabrica;
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

//    public static void main(String[] args) {
//        try {
//            // abre conexao nsinova
//            Conexao conexao = new Conexao("postgresql", "curso", "postgres", "pgsql$nsinova");
//
//            // abre conexao local
//            // Conexao conexao = new Conexao("postgresql", "oficina", "postgres", "postgresql026");
//            
//            // cria dao servico
//            IServico daoServico = DaoFabrica.criarServico(conexao);
//            IVeiculo daoVeiculo = DaoFabrica.criarVeiculo(conexao);
//
//            
//            // cria negocio
//            PessoaNegocio pessoaNegocio = new PessoaNegocio(conexao);
//            
//            // cria negocio veiculo
//            VeiculoNegocio veiculoNegocio = new VeiculoNegocio(conexao);
//
//            // cria negocio servico
//            ServicoNegocio servicoNegocio = new ServicoNegocio(conexao);
//
//            // busca veiculo para vincular ao servico
//            Veiculo veiculoServico = veiculoNegocio.obter("XYZ9K88");
//
//            // testa cadastro
//            Servico servico = new Servico(
//                0,
//                "Alinhamento e balanceamento",
//                LocalDate.now(),
//                veiculoServico
//            );
//            Servico salvo = servicoNegocio.manter(servico);
//            System.out.println("Servico salvo: " + salvo.getNumero() + " - " + salvo.getDescricao());
//
//            // testa obterLista por placa
//            List<Servico> lista = servicoNegocio.obterLista("XYZ9K88");
//            for (Servico s : lista) {
//                System.out.println(s.getNumero() + " - " + s.getDescricao());
//            }
//            
//            // fecha conexao
//            conexao.close();
//
//        } catch (Exception e) {
//            System.out.println("Erro: " + e.getMessage());
//        }
//    }
}