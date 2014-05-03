package br.com.lossantos.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.AutoPopulatingList;

import br.com.lossantos.helper.CustomStringUtils;

@Entity
@Table(name = "businesses")
public class Business {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	@NotNull
	@Size(min = 2, message = "deve ter ao menos dois caracteres")
	private String title = "Nutella " + UUID.randomUUID();

	@Column
	private String slug;

	@Column
	private String address;

	@Column
	private String email;

	@Column(name = "phone_number")
	private String phoneNumbers;

	@Column(name = "images")
	@Min(0)
	private Integer imagesCount = 0;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business", cascade = CascadeType.ALL)
	@OrderBy("description ASC")
	private List<Service> services = new AutoPopulatingList<Service>(
			Service.class);

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

	public Integer getImagesCount() {
		return imagesCount;
	}

	public void setImagesCount(Integer imagesCount) {
		this.imagesCount = imagesCount;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	@PrePersist
	private void prePersist() {
		this.createdAt = new Timestamp(System.currentTimeMillis());

		preUpdate();
	}

	@PreUpdate
	private void preUpdate() {
		this.slug = CustomStringUtils.slugify(this.title);

		this.updatedAt = new Timestamp(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "Business ["
				+ (id != null ? "id=" + id + ", " : "")
				+ (title != null ? "title=" + title + ", " : "")
				+ (slug != null ? "slug=" + slug + ", " : "")
				+ (address != null ? "address=" + address + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (phoneNumbers != null ? "phoneNumbers=" + phoneNumbers + ", "
						: "")
				+ (imagesCount != null ? "imagesCount=" + imagesCount + ", "
						: "")
				+ (createdAt != null ? "createdAt=" + createdAt + ", " : "")
				+ (updatedAt != null ? "updatedAt=" + updatedAt + ", " : "")
				+ (city != null ? "city=" + city + ", " : "")
				+ (services != null ? "services=" + services : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Business other = (Business) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
