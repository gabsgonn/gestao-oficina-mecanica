package com.nsinova.oficina.controle;

import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.negocio.ServicoNegocio;
import com.nsinova.oficina.modelo.Servico;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author gabs
 */
@Path("/servicos")
public class ServicoController {
    private Conexao conexao = null;
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inicio(
        @QueryParam("servico") String servico
    ) throws Exception {
        try {
            conexao = new Conexao("postgresql", "curso", "postgres", "pgsql$nsinova");
        
            ServicoNegocio servicoNegocio = new ServicoNegocio(conexao);
            
            List<Servico> listaServicos = servicoNegocio.obterLista(servico);
            
            return Response.ok(listaServicos).build();
        } catch (Exception e) {
            throw new Exception("erro" + e);
        }
    }
    
    @GET
    @Path("/nome-e-placa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlacaProprietario(
        @QueryParam("servico") String servico
    ) throws Exception {
        try {
            conexao = new Conexao("postgresql", "curso", "postgres", "pgsql$nsinova");
        
            ServicoNegocio servicoNegocio = new ServicoNegocio(conexao);
            
            List<Servico> listaServicos = servicoNegocio.obterNumeroEVeiculo();
            
            return Response.ok(listaServicos).build();
        } catch (Exception e) {
            throw new Exception("erro" + e);
        }
    }
}
