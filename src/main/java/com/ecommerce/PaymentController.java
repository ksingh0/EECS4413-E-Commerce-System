package com.ecommerce;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentController {

    private PaymentDAO paymentDAO = new PaymentDAO();

    // UC5: Create a new payment
    @POST
    public Response createPayment(Payment payment) {
        try {
            if (payment == null || payment.getAmount() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Invalid payment data.\"}")
                        .build();
            }

            paymentDAO.createPayment(payment);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\": \"Payment created successfully.\"}")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // UC6: Get payment by ID
    @GET
    public Response getPayment(@QueryParam("id") Integer id) {
        try {
            if (id != null) {
                Payment payment = paymentDAO.getPaymentById(id);
                if (payment == null) {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("{\"error\": \"Payment not found.\"}")
                            .build();
                }
                return Response.ok(payment).build();
            } else {
                List<Payment> payments = paymentDAO.getAllPayments();
                return Response.ok(payments).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Update payment status
    @PUT
    @Path("/status")
    public Response updateStatus(@QueryParam("id") int id, Payment updateRequest) {
        try {
            if (updateRequest == null || updateRequest.getStatus() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Missing status.\"}")
                        .build();
            }

            paymentDAO.updatePaymentStatus(id, updateRequest.getStatus());
            return Response.ok("{\"message\": \"Payment status updated successfully.\"}").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
