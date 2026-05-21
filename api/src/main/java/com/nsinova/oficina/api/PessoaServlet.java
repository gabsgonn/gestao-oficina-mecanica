package com.nsinova.oficina.api;

import com.google.gson.Gson;
import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.negocio.PessoaNegocio;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PessoaServlet", urlPatterns = {"/pessoa"})
public class PessoaServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Conexao conexao = null;
        try (PrintWriter out = response.getWriter()) {
            conexao = new Conexao("postgresql", "oficina", "postgres", "postgresql026");
            PessoaNegocio negocio = new PessoaNegocio(conexao);
            
            String nome = request.getParameter("nome");
            List<Pessoa> lista = negocio.obterLista(nome);
            System.out.println("Lista size: " + lista.size());
            System.out.println("JSON: " + gson.toJson(lista));
            out.print(gson.toJson(lista));
            
            out.print(gson.toJson(lista));
        } catch (Exception e) {
            response.setStatus(500);
        } finally {
            if (conexao != null) {
                try { conexao.close(); } catch (Exception e) {}
            }
        }
    }
}