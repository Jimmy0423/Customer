package se.jimi.customer.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.jimi.customer.model.Customer;
import se.jimi.customer.model.MemberStatus;
import se.jimi.customer.service.CustomerService;

public class Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.jimi.customer.config");
			context.refresh();

			CustomerService service = context.getBean(CustomerService.class);
			Customer customer = new Customer("jimmy@aol.com", MemberStatus.NO_MEMBER);
			Customer cus2 = new Customer("bajs", MemberStatus.NO_MEMBER);
			Customer cus1 = new Customer("bajsss", MemberStatus.VIP);

			service.addCustomer(customer);
			service.addCustomer(cus2);
			service.addCustomer(cus1);

		}

	}
}
