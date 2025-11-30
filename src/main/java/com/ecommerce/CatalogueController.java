package com.ecommerce;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;



@Path("")
public class CatalogueController {
	
	CatalogueDAO catalogueDAO = new CatalogueDAO();
	DAO auctionDAO = new DAO();
	
	@GET
	@Path("/items")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Catalogue> getAllItems() {
		return catalogueDAO.readAllItems();
	}
	
	@POST
	@Path("/item")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Catalogue createItem(Catalogue item) {
		return catalogueDAO.createItem(item);
	}
	@GET
	@Path("/item/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Catalogue getItem(@PathParam("id") int id) {
		return catalogueDAO.readItem(id);
	}
	@PUT
	@Path("/item/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Catalogue updateItem(@PathParam("id") int id, Catalogue item) {
		return catalogueDAO.updateItem(id, item);
	}
	
	@DELETE
	@Path("/item/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteItem(@PathParam("id") int id) {
		catalogueDAO.deleteItem(id);
		auctionDAO.deleteAuction(id); //delete auction as well for referential integrity
	}
}














