package se.jimi.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Customer extends AbstractModel {

	@Column(unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	private MemberStatus memberStatus;

	protected Customer() {
	}

	public Customer(String email, MemberStatus memberStatus) {
		this.email = email;
		this.memberStatus = memberStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberStatus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
	}

}
