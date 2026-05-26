package com.nsinova.oficina.api;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author gabs
 */

@ApplicationPath("")
public class Aplicacao extends ResourceConfig {

    public Aplicacao() {
        packages("com.nsinova.oficina.controle");
//        packages("br.com.nsinova.oficina.controle");

        register(JacksonFeature.class);
        
    }
}
