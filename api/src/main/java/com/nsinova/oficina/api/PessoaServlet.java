package com.nsinova.oficina.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.nsinova.oficina.conexao.Conexao;
import com.nsinova.oficina.modelo.Pessoa;
import com.nsinova.oficina.negocio.PessoaNegocio;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "PessoaServlet", urlPatterns = {"/pessoa"})
public class PessoaServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsString()))
            .create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Conexao conexao = null;

        try {
            conexao = new Conexao("postgresql", "oficina", "postgres", "postgresql026");
            PessoaNegocio negocio = new PessoaNegocio(conexao);

            String nome = request.getParameter("nome");
            List<Pessoa> lista = negocio.obterLista(nome);
            

            response.getWriter().write(gson.toJson(lista));

            System.out.println("Lista size: " + lista.size());
            System.out.println("JSON: " + gson.toJson(lista));

        } catch (Exception e) {
            response.setStatus(500);
            response.setContentType("application/json");
            response.getWriter().write("{\"erro\": \"" + e.getMessage() + "\"}");
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                }
            }
        }
    }
}


//@WebServlet(name = "PessoaServlet", urlPatterns = {"/pessoa"})
//public class PessoaServlet extends HttpServlet {
//
//    private final Gson gson = new Gson();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        
//        
//        Conexao conexao = null;
//        
//        try (PrintWriter out = response.getWriter()) {
//            conexao = new Conexao("postgresql", "oficina", "postgres", "postgresql026");
//            PessoaNegocio negocio = new PessoaNegocio(conexao);
//            
//            String nome = request.getParameter("nome");
//            List<Pessoa> lista = negocio.obterLista(nome);
//            
//            
//            response.getWriter().write(gson.toJson(lista));
//            
//            
//            System.out.println("Lista size: " + lista.size());
//            System.out.println("JSON: " + gson.toJson(lista));
//            
//        } catch (Exception e) {
//            response.setStatus(500);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"erro\": \"" + e.getMessage() + "\"}");
//            e.printStackTrace();
//        } finally {
//            if (conexao != null) {
//                try { conexao.close(); } catch (Exception e) {}
//            }
//        }
//    }
//}