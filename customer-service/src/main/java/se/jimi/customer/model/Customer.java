package se.jimi.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends AbstractModel {

	@Column(unique = true)
	private String email;

	private MemeberStaus memberStatus;

	protected Customer() {
	}

	public Customer(String email, MemeberStaus memberStatus) {
		this.email = email;
		this.memberStatus = memberStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemeberStaus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(MemeberStaus memberStatus) {
		this.memberStatus = memberStatus;
	}

}
