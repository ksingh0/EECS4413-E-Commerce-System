package com.ecommerce;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.List;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;



@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserController {
	@Context
	private HttpServletRequest request;

    private final UserDAO userDAO = new UserDAO();

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private boolean checkPassword(String password, String hashedPassword) {
        String hashed = hashPassword(password);
        return hashed.equals(hashedPassword);
    }

    @POST
    @Path("/signup")
    public Response signup(User user) {
        // Validate
        if (user.getUsername() == null || user.getPassword() == null ||
            user.getFirstName() == null || user.getLastName() == null ||
            user.getStreetName() == null || user.getStreetNumber() == null ||
            user.getCity() == null || user.getState()==null || user.getCountry() == null ||
            user.getPostalCode() == null) {
            return Response.status(400).entity(Map.of("error", "All fields required")).build();
//        	return Response.status(400).entity(user.toString()).build();
        }

        if (userDAO.existsByUsername(user.getUsername())) {
            return Response.status(400).entity(Map.of("error", "Username already taken")).build();
        }

        // Hash password
        String hashed = hashPassword(user.getPassword());
        user.setPassword(hashed);
        user.setSeller(false);

        User created = userDAO.create(user);
        created.setPassword(null); // don't return hash

        return Response.status(201).entity(created).build();
    }

    @POST
    @Path("/signin")
    public Response signin(@Context jakarta.servlet.http.HttpServletRequest request,
                        Map<String, String> creds) {

        String username = creds.get("username");
        String password = creds.get("password");

        if (username == null || password == null) {
            return Response.status(400).entity(Map.of("error", "username and password required")).build();
        }

        User user = userDAO.findByUsername(username);
        if (user == null || !checkPassword(password, user.getPassword())) {
            return Response.status(401).entity(Map.of("error", "Invalid credentials")).build();
        }

        //  Create session
        jakarta.servlet.http.HttpSession session = request.getSession(true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userId", user.getId());
        session.setMaxInactiveInterval(60 * 30); // expires 30 min

        user.setPassword(null);

        return Response.ok(Map.of(
            "message", "Logged in successfully",
            "user", user,
            "sessionId", session.getId(),
            "userId", user.getId()// optional to send back
        )).build();
    }
    
    /*
    @GET
    @Path("/getId")
    public long getUserId(@Context jakarta.servlet.http.HttpServletRequest request) {
    	HttpSession session = request.getSession(false);
    	return (Long) session.getAttribute("userId");
    }
    */

    @POST
    @Path("/logout")
    public Response logout(@Context jakarta.servlet.http.HttpServletRequest request) {
        request.getSession().invalidate();
        return Response.ok(Map.of("message","Logged out")).build();
    }

    
    private static final Map<String, String> resetTokens = new HashMap<>();

    @POST
    @Path("/forgot-password")
    public Response forgotPassword(Map<String, String> body) {
        String username = body.get("username");
        if (username == null || !userDAO.existsByUsername(username)) {
            return Response.status(404).entity(Map.of("error", "User not found")).build();
        }

        // Generate reset token
        String token = java.util.UUID.randomUUID().toString();
        resetTokens.put(token, username);

        String resetUrl = "http://localhost:8080/EECS4413-E-Commerce-System/reset-password.html?token=" + token;

        return Response.ok(Map.of(
            "message", "Password reset link generated.",
            "reset_link", resetUrl      // Simulated as email
        )).build();
    }

    @GET
    public Response getAllUsers() {
        List<User> users = userDAO.findAll();
        users.forEach(u -> u.setPassword(null)); // Don't expose password hashes
        return Response.ok(users).build();
    }


    @POST
    @Path("/reset-password")
    public Response resetPassword(Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("password");

        if (token == null || newPassword == null || !resetTokens.containsKey(token)) {
            return Response.status(400).entity(Map.of("error", "Invalid or expired token")).build();
        }

        String username = resetTokens.get(token);
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return Response.status(404).entity(Map.of("error", "User not found")).build();
        }

        user.setPassword(hashPassword(newPassword));
        userDAO.update(user.getId(), user);
        resetTokens.remove(token);

        return Response.ok(Map.of("message", "Password updated successfully")).build();
    }

    @POST
    @Path("/create")
    public Response createUser(User user) {
        if (userDAO.existsByUsername(user.getUsername())) {
            return Response.status(400).entity(Map.of("error", "Username already taken")).build();
        }
        
        // Hash password if provided
        if (user.getPassword() != null) {
            String hashed = hashPassword(user.getPassword());
            user.setPassword(hashed);
        }

        User created = userDAO.create(user);
        created.setPassword(null);
        return Response.status(201).entity(created).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") long id) {
        User user = userDAO.findById(id);
        if (user == null) {
            return Response.status(404).entity(Map.of("error", "User not found")).build();
        }
        user.setPassword(null);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") long id, User updates) {
        User existing = userDAO.findById(id);
        if (existing == null) {
            return Response.status(404).entity(Map.of("error", "User not found")).build();
        }

        updates.setUsername(existing.getUsername());
        updates.setSeller(existing.isSeller());

        if (updates.getPassword() != null) {
            updates.setPassword(hashPassword(updates.getPassword()));
        } else {
            updates.setPassword(existing.getPassword());
        }

        User updated = userDAO.update(id, updates);
        updated.setPassword(null);

        return Response.ok(updated).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        User existing = userDAO.findById(id);
        if (existing == null) {
            return Response.status(404).entity(Map.of("error", "User not found")).build();
        }
        
        // In real app: verify user has permission to delete this ID
        
        userDAO.delete(id);
        return Response.noContent().build();
    }
    
    @POST
    @Path("/logout")
    public Response logout() {
    	// Remove the stored session variables
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return Response.ok("{\"success\":true}").build();
    }
}