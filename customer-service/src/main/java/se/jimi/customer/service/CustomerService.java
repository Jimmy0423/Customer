package se.jimi.customer.service;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;

import se.jimi.customer.model.Customer;
import se.jimi.customer.model.MemberStatus;
import se.jimi.customer.repository.CustomerRepository;

public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer findByEmail(String email) {

		Customer customer = customerRepository.findByEmail(email);

		if (customer == null) {
			throw new IllegalArgumentException("there is no user with that Email");
		} else {
			return customer;
		}
	}

	public List<Customer> findByMemeberStatus(MemberStatus status) {
		if (customerRepository.findByMemberStatus(status).isEmpty()) {
			throw new IllegalArgumentException("There is no Member with this status");
		}
		return customerRepository.findByMemberStatus(status);
	}

	public Customer findByCustomerId(long id) {
		Customer customer = customerRepository.findCustomerById(id);

		if (customer == null) {
			throw new IllegalArgumentException("There is no Member with this id");
		} else {
			return customer;

		}

	}

	public void removeCustomer(long id) {
		customerRepository.delete(id);
	}

}
