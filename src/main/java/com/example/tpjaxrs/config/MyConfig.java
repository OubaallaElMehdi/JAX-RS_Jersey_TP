package com.example.tpjaxrs.config;

import com.example.tpjaxrs.restapi.CompteRestJaxRSAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider; // JAXB Provider import

@Configuration
public class MyConfig {

	@Bean
	public ResourceConfig resourceConfig() {
		ResourceConfig jersyServlet = new ResourceConfig();
		jersyServlet.register(CompteRestJaxRSAPI.class);
		jersyServlet.register(XmlJaxbElementProvider.App.class);
		return jersyServlet;
	}
}

