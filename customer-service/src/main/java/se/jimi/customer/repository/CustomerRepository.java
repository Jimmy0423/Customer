package se.jimi.customer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.jimi.customer.model.Customer;
import se.jimi.customer.model.MemberStatus;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findCustomerById(long id);

	Customer findByEmail(String email);

	List<Customer> findByMemberStatus(MemberStatus status);

	

}
