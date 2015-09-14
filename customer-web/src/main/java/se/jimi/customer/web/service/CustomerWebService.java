package se.jimi.customer.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import se.jimi.customer.model.Customer;
import se.jimi.customer.service.CustomerService;

@Path("customer")
public class CustomerWebService {

	@Autowired
	private CustomerService customerService;

	@Path("{id}")
	@GET
	public Response getCustomerById(@Param("id") Long id) {

		Customer customer = customerService.findByCustomerId(id);

		return Response.ok(customer).build();
	}

}
