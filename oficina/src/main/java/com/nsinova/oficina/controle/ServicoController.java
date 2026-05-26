package com.nsinova.oficina.controle;

import com.nsinova.oficina.api.ConexaoApi;
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
