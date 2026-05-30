package com.nsinova.oficina.controle;

import com.nsinova.oficina.api.ConexaoApi;
import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.negocio.ServicoNegocio;
import com.nsinova.oficina.modelo.Servico;

import java.util.List;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author gabs
 */
@Path("servicos")
public class ServicoController {
    private ConexaoApi api = new ConexaoApi();

    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inicio(
        @QueryParam("servico") String servico
    ) throws Exception {
        try {
            Conexao conexao = api.conexaoApi();
            
            ServicoNegocio servicoNegocio = new ServicoNegocio(conexao);
            
            List<Servico> listaServicos = servicoNegocio.obterLista(servico);
            
            return Response.ok(listaServicos).build();
        } catch (Exception e) {
            throw new Exception("erro" + e);
        }
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarServico(Servico servico) throws Exception {
        try {
            
            Conexao conexao = api.conexaoApi();
            
            ServicoNegocio servicoNegocio = new ServicoNegocio(conexao);
            
            Servico salva = servicoNegocio.manter(servico);
            
            return Response.status(201).entity(salva).build();
            
        } catch (Exception e) {
            throw new Exception("Erro:" + e);
        }
    }
    
    @PUT
    @Path("{numero}/finalizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response finalizarServico(@PathParam("numero") long numero) throws Exception {
        try {
            Conexao conexao = api.conexaoApi();
            ServicoNegocio servicoNegocio = new ServicoNegocio(conexao);

            Servico servico = servicoNegocio.obterPorId(numero);

            if (servico == null) {
                return Response.status(404).entity("Serviço " + numero + " não encontrado").build();
            }

            Servico salva = servicoNegocio.finalizar(servico);

            return Response.ok(salva).build();

        } catch (Exception e) {
            throw new Exception("Erro:" + e.getMessage(), e);
        }
    }
    
//    ========== endpoint teste ===========
    @GET
    @Path("/numero-veiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlacaProprietario(
        @QueryParam("servico") String servico
    ) throws Exception {
        try {
            Conexao conexao = api.conexaoApi();
            
            ServicoNegocio servicoNegocio = new ServicoNegocio(conexao);
            
            List<Servico> listaServicos = servicoNegocio.obterNumeroEVeiculo();
            
            return Response.ok(listaServicos).build();
        } catch (Exception e) {
            throw new Exception("erro" + e);
        }
    }
}
