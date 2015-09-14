package se.jimi.customer.web.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.query.Param;

import se.jimi.customer.model.Customer;
import se.jimi.customer.service.CustomerService;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Scope("request")
public class CustomerWebService {

	@Autowired
	private CustomerService customerService;

	@GET
	@Path("{customerId}")
	public Response getCustomerById(@PathParam("customerId") long customerId) {
		Customer customer = customerService.findByCustomerId(customerId);
		return Response.ok(customer).build();
	}

	@Path("email/{email}")
	@GET
	public Response getCustomerByEmail(@PathParam("email") String email) {
		Customer customer = customerService.findByEmail(email);
		return Response.ok(customer).build();
	}

	@Path("{id}")
	@DELETE
	public Response removeCustomer(@PathParam("id") long id) {
		Customer customer = customerService.findByCustomerId(id);
		if(customer == null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		customerService.removeCustomer(id);
		return Response.ok(customer).build();
	}

}
