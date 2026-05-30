package com.nsinova.oficina.controle;

import com.nsinova.oficina.api.ConexaoApi;
import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Veiculo;
import com.nsinova.oficina.negocio.VeiculoNegocio;

import java.util.List;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author gabs
 */
@Path("veiculos")
public class VeiculoController {
    private ConexaoApi api = new ConexaoApi();
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterLista(
        @QueryParam("veiculo") String veiculo
    ) throws Exception {
        try {
            Conexao conexao = api.conexaoApi();
            
            VeiculoNegocio veiculoNegocio = new VeiculoNegocio(conexao);
            
            List<Veiculo> listaServicos = veiculoNegocio.obterLista(veiculo);
            
            return Response.ok(listaServicos).build();
        } catch (Exception e) {
            throw new Exception("erro" + e);
        }
    }
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarVeiculo(Veiculo veiculo) throws Exception {
        try {
            
            Conexao conexao = api.conexaoApi();
            
            VeiculoNegocio pessoaNegocio = new VeiculoNegocio(conexao);
            
            Veiculo salva = pessoaNegocio.manter(veiculo);
            
            return Response.status(201).entity(salva).build();
            
        } catch (Exception e) {
            throw new Exception("Erro:" + e);
        }
    }
    
    
}
