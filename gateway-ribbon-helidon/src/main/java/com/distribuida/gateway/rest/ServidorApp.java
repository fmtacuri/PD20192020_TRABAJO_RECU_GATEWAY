package com.distribuida.gateway.rest;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.distribuida.gateway.gt.TodoGateway;
import com.distribuida.gateway.gt.UsuarioGateway;

@ApplicationScoped
@ApplicationPath(value="/gateway")
public class ServidorApp extends Application {
	
	@Override
    public Set<Class<?>> getClasses() {
        return Set.of(
        	UsuarioGateway.class,
        	TodoGateway.class
        );
    }
}
