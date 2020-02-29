package com.distribuida.gateway.ribbon;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.Var;

import io.netty.buffer.ByteBuf;

@ResourceGroup(name="todo-server")
public interface TodoRibbonRest {
	@Http(
		method = Http.HttpMethod.GET,
		uri = "/todos"
	)
	public RibbonRequest<ByteBuf> findAll();
	
	@Http(
			method = Http.HttpMethod.GET,
			uri = "/todos/{id}"
	)
	public RibbonRequest<ByteBuf> findById( @Var("id") Integer id );
}
