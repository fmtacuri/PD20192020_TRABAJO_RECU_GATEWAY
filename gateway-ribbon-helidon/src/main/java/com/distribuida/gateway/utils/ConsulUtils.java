package com.distribuida.gateway.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;

@ApplicationScoped
public class ConsulUtils {
	
	@Inject @ConfigProperty(name="consul.server", defaultValue = "localhost")
	private String consulHost;

	@Inject	@ConfigProperty(name="consul.port", defaultValue = "8500")
	private Integer consulPort;
	
	public String getServices( String name ) {
		
		ConsulClient client = new ConsulClient( consulHost, consulPort );
		
		HealthServicesRequest request = HealthServicesRequest.newBuilder()
							.setPassing(true)
							.setQueryParams(QueryParams.DEFAULT)
							.build();
		
		Response<List<HealthService>> healthyServices = client.getHealthServices( name, request );
		
		List<HealthService> datos = healthyServices.getValue();
		
		String urls = datos.stream()
			.map( s->s.getService() )
			.map( s->String.format("%s:%d", s.getAddress(),s.getPort() ) )
			.collect( Collectors.joining( "," ) );
			;
			
		return urls;
	}
}
