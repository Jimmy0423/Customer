package se.jimi.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import se.jimi.customer.model.Customer;
import se.jimi.customer.model.MemeberStaus;
import se.jimi.customer.repository.CustomerRepository;

public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer findByEmail(String email) {
		if (customerRepository.findByEmail(email) == null) {
			throw new IllegalArgumentException("there is no user with that Email");
		} else {
			return customerRepository.findByEmail(email);
		}
	}

	public List<Customer> findByMemeberStatus(MemeberStaus status) {
		if (customerRepository.findByMemberStatus(status).isEmpty()) {
			throw new IllegalArgumentException("There is no Member with this status");
		}
		return customerRepository.findByMemberStatus(status);
	}

	public Customer findByCustomerId(Long id) {
		return customerRepository.findCustomerById(id);
	}

}
