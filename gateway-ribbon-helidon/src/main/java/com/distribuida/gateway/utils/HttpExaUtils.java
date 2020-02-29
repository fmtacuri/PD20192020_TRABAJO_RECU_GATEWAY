package com.distribuida.gateway.utils;

import javax.ws.rs.core.MediaType;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpExaUtils {

	public static Object invoke(String url) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet get = new HttpGet(url);

		get.addHeader("Accept", MediaType.APPLICATION_JSON);

		Object dto = httpClient.execute(get, response -> {

			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {

				return EntityUtils.toString(response.getEntity());
			}

			return null;
		});

		return dto;
	}
	
}
