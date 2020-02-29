package com.distribuida.gateway.gt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.configuration.AbstractConfiguration;

import com.distribuida.gateway.ribbon.UsuarioRibbonRest;
import com.distribuida.gateway.utils.ByteBufUtils;
import com.distribuida.gateway.utils.ConsulUtils;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.config.ConfigurationManager;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonRequest;

import io.netty.buffer.ByteBuf;

@Path("/usuarios")
@Produces("application/json")
@ApplicationScoped
public class UsuarioGateway {
	
	private UsuarioRibbonRest proxy;	
	
	@Inject private ConsulUtils utils;
	
	@PostConstruct
	protected void inicializar( ) {
		// inicializar RIBBON
		AbstractConfiguration config = ConfigurationManager.getConfigInstance();
		
		config.setProperty( "usuario-server.ribbon." + CommonClientConfigKey.ListOfServers
				, utils.getServices("usuario-server") );
		
		proxy = Ribbon.from( UsuarioRibbonRest.class );
	}
	
	@GET
	@Path("/")
	public byte[] findAll( ) {
		
		RibbonRequest<ByteBuf> ret = proxy.findAll();
		
		ByteBuf result =  ret.execute( );
		
		// convertir a String
		return ByteBufUtils.extract( result );
	}
	
	@GET
	@Path("/{id}")
	public byte[] findById( @PathParam(value="id") Integer id ) {
		
		RibbonRequest<ByteBuf> ret = proxy.findById( id );
		
		ByteBuf result =  ret.execute( );
		
		// convertir a String
		return ByteBufUtils.extract( result );
	}
}
