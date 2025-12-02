package com.ecommerce;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/wishlist")
public class WishlistController {

    @Context
    private HttpServletRequest request;

    private WishlistDAO wdao = new WishlistDAO();

    /** Helper to safely extract logged-in userId */
    private Long getLoggedInUserId() {
        HttpSession session = request.getSession(false);
        if (session == null) return null;

        Object val = session.getAttribute("userId");
        if (val instanceof Long) {
            return (Long) val;
        }
        if (val instanceof Integer) { 
            return ((Integer) val).longValue();
        }
        System.out.println(val); //debug
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuctions() {
        Long userId = getLoggedInUserId();

        if (userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"User must be logged in\"}").build();
        }

        List<Auction> auctions = wdao.readAllWishlists(userId);
        return Response.ok(auctions).build();
    }

    @POST
    @Path("/{auctionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToWishlist(@PathParam("auctionId") int auctionId) {

        Long userId = getLoggedInUserId();

        if (userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"User must be logged in\"}").build();
        }

        boolean ok = wdao.createWishlist(userId, auctionId);

        if (!ok) {
            return Response.status(400)
                    .entity("{\"error\":\"Already wishlisted or invalid\"}").build();
        }

        return Response.ok("{\"success\":true}").build();
    }

    @DELETE
    @Path("/{auctionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeFromWishlist(@PathParam("auctionId") int auctionId) {

        Long userId = getLoggedInUserId();

        if (userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"User must be logged in\"}").build();
        }

        wdao.deleteWishlist(userId, auctionId);
        return Response.ok("{\"success\":true}").build();
    }
}
