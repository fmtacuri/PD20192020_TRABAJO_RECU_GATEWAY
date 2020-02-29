package com.distribuida.gateway.ribbon;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.Var;

import io.netty.buffer.ByteBuf;

@ResourceGroup(name="usuario-server")
public interface UsuarioRibbonRest {
	@Http(
		method = Http.HttpMethod.GET,
		uri = "/usuarios"
	)
	public RibbonRequest<ByteBuf> findAll();
	
	@Http(
			method = Http.HttpMethod.GET,
			uri = "/usuarios/{id}"
	)
	public RibbonRequest<ByteBuf> findById( @Var("id") Integer id );
}
