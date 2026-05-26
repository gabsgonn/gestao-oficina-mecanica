package com.nsinova.oficina.controle;

import com.nsinova.oficina.api.ConexaoApi;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.negocio.PessoaNegocio;
import com.nsinova.oficina.conexao.Conexao;
import javax.ws.rs.POST;


/**
 *
 * @author gabs
 */
@Path("/pessoas")
public class PessoaController {
    private ConexaoApi api = new ConexaoApi();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inicio(
        @QueryParam("nome") String nomeCliente
    ) throws Exception {
        try {            
            Conexao conexao = api.conexaoApi();
            
            PessoaNegocio pessoaNegocio = new PessoaNegocio(conexao);

            List<Pessoa> listaPessoas = pessoaNegocio.obterLista(nomeCliente);

            return Response.ok(listaPessoas).build();
        } catch (Exception e) {
            throw new Exception("erro" + e);
        } 
    }
    

    @GET
    @Path("/nome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarNomes(
            @QueryParam("nome") String nomeCliente
    ) throws Exception {
        try {
            Conexao conexao = api.conexaoApi();

            PessoaNegocio pessoaNegocio = new PessoaNegocio(conexao);

            List<Pessoa> listaNomePessoas = pessoaNegocio.obterListaSomenteNome(nomeCliente);

            return Response.ok(listaNomePessoas).build();
        } catch (Exception e) {
            throw new Exception("erro" + e);
        }
    }
}
