package se.jimi.customer.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.jimi.customer.model.Customer;
import se.jimi.customer.model.MemeberStaus;
import se.jimi.customer.service.CustomerService;

public class Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.jimi.customer.config");
			context.refresh();

			CustomerService service = context.getBean(CustomerService.class);
			Customer customer = new Customer("jimmy@aol.com", MemeberStaus.NO_MEMBER);
			Customer cus2 = new Customer("bajs", MemeberStaus.NO_MEMBER);
			Customer cus1 = new Customer("bajsss", MemeberStaus.VIP);

			service.addCustomer(customer);
			service.addCustomer(cus2);
			service.addCustomer(cus1);

			// System.out.println(service.findByEmail("jimmy@aol.om"));

			System.out.println(service.findByMemeberStatus(MemeberStaus.NO_MEMBER));

		}

	}
}
