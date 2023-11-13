package com.example.api.configs;

import com.example.ejb.services.StarShipService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class EjbConfig {

    @Bean
    public Context context() throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:41569");
        return new InitialContext(jndiProps);
    }

    @Bean
    public StarShipService starShipService(Context context) throws NamingException {
        return (StarShipService) context.lookup("ejb:/ejb/StarShipServiceImpl!com.example.ejb.services.StarShipService");
    }
}
