package com.ecommerce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/")
public class GatewayApp extends Application {

	// Add controllers to GatewayApp
//	@Override
//	public Set<Class<?>> getClasses() {
//	    Set<Class<?>> resources = new HashSet<>();
//	    resources.add(com.ecommerce.AuctionController.class);
//	    return resources;
//	}

}
