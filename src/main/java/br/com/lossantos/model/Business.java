package br.com.lossantos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.lossantos.helper.CustomStringUtils;

@Entity
@Table(name = "businesses")
public class Business {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String title;

	@Column
	private String slug;

	@Column
	private String address;

	@Column
	private String email;

	@Column(name = "phone_number")
	private String phoneNumbers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Override
	public String toString() {
		return "Business [" + (id != null ? "id=" + id + ", " : "")
				+ (title != null ? "title=" + title + ", " : "")
				+ (slug != null ? "slug=" + slug + ", " : "")
				+ (address != null ? "address=" + address + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (phoneNumbers != null ? "phoneNumbers=" + phoneNumbers : "")
				+ "]";
	}

	@PrePersist
	private void generateSlug() {
		this.slug = CustomStringUtils.slugify(this.title);
	}

}
