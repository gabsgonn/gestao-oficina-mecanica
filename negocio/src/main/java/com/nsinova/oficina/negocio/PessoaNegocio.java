package com.nsinova.oficina.negocio;

import com.nsinova.oficina.negocio.util.Manter;
import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.persiste.DaoFabrica;
import com.nsinova.oficina.persiste.IPessoa;
import java.sql.SQLException;
import java.util.List;

/**
 * @author treinamento
 */
public class PessoaNegocio extends NegocioBase {
    private IPessoa pessoaDao;
    
    public PessoaNegocio(Conexao conexao) {
        super(conexao);
    }
    
    protected void inicializar() {
        pessoaDao = DaoFabrica.criarPessoa(conexao);
    }
    
    public Pessoa manter(com.nsinova.oficina.modelo.Pessoa pessoa) throws Exception {
        try {
            return Manter.persist(pessoa, (com.nsinova.oficina.modelo.Pessoa pessoa2) -> manter2(pessoa2), 
                    conexao);
        } catch (Exception ex) {
            throw new Exception("Erro ao manter a Pessoa!", ex);
        }
    }
    
    private Pessoa manter2(com.nsinova.oficina.modelo.Pessoa pessoa) {
        try {
            if(pessoa.getNome()==null || 
                    pessoa.getCpf()==null ||
                    pessoa.getDataNascimento()==null) {
                throw new RuntimeException("Nome, cpf, email e data de nascimento não podem ser nulos!");
            } return pessoaDao.manter(pessoa);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public com.nsinova.oficina.modelo.Pessoa obter(String nomePessoa) throws SQLException{
        return pessoaDao.obterLista(nomePessoa).get(0);
    }
    
    public List<com.nsinova.oficina.modelo.Pessoa> obterLista(String nomeCliente) throws SQLException {
        return pessoaDao.obterLista(nomeCliente);
    }
}