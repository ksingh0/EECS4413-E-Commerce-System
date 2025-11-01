package com.ksingh.studentapp;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;
import com.ecommerce.Payment;

@Path("/payments")
public class PaymentController {
	PaymentDAO paymentDAO = new PaymentDAO();

    @GET
    @Path("/all")
    public Response getAllPayments() {
        List<Payment> payments = paymentDAO.getAllPayments();
        return Response.ok(payments).build();
    }

    @POST
    @Path("/create")
    public Response createPayment(Payment payment) {
        paymentDAO.createPayment(payment);
        return Response.ok("Payment Created").build();
    }

    @PUT
    @Path("/updateStatus/{id}")
    public Response updatePaymentStatus(@PathParam("id") int id, @QueryParam("status") String status) {
        paymentDAO.updatePaymentStatus(id, status);
        return Response.ok("Payment Status Updated").build();
    }

}
