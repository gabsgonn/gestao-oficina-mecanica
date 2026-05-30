package com.nsinova.oficina.persiste;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.persiste.postgres.ServicoItemDao;

/**
 *
 * @author gabs
 */
public class DaoFabrica {
    
    public static IPessoa criarPessoa(Conexao conexao) {
        if("postgresql".equalsIgnoreCase(conexao.getProvedor())){
            return new com.nsinova.oficina.persiste.postgres.PessoaDao(conexao.getConnection());
        }
        return null;
    }
    
    public static IVeiculo criarVeiculo(Conexao conexao) {
        if("postgresql".equalsIgnoreCase(conexao.getProvedor())){
            IPessoa daoPessoa = criarPessoa(conexao);
            return new com.nsinova.oficina.persiste.postgres.VeiculoDao(conexao.getConnection(), daoPessoa);
        }
        return null;
    }
    
    public static IServico criarServico(Conexao conexao) {
        if("postgresql".equalsIgnoreCase(conexao.getProvedor())){
            IVeiculo daoVeiculo = criarVeiculo(conexao);
            return new com.nsinova.oficina.persiste.postgres.ServicoDao(conexao.getConnection(), daoVeiculo);
        }
        return null;
    }

    public static ITipoServico criarTipoServico(Conexao conexao) {
        if ("postgresql".equalsIgnoreCase(conexao.getProvedor())) {
            return new com.nsinova.oficina.persiste.postgres.TipoServicoDao(conexao.getConnection());
        }
        return null;
    }

    public static IPeca criarPeca(Conexao conexao) {
        if ("postgresql".equalsIgnoreCase(conexao.getProvedor())) {
            return new com.nsinova.oficina.persiste.postgres.PecaDao(conexao.getConnection());
        }
        return null;
    }
 
    public static IServicoItem criarServicoItem(Conexao conexao) {
        if ("postgresql".equalsIgnoreCase(conexao.getProvedor())) {
            IServico daoServico = criarServico(conexao);
            IPeca daoPeca = criarPeca(conexao);
            ITipoServico daoTipoServico = criarTipoServico(conexao);
            return new ServicoItemDao(conexao.getConnection(), daoServico, daoPeca, daoTipoServico);
        }
        return null;
    }
}