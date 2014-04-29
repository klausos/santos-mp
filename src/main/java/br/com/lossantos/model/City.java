package br.com.lossantos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.lossantos.helper.CustomStringUtils;

@Entity
@Table(name = "cities")
public class City {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	@NotNull
	private String name;

	@Column
	@NotNull
	private String slug;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	@PrePersist
	private void prePersist() {
		preUpdate();
	}

	@PreUpdate
	private void preUpdate() {
		this.slug = CustomStringUtils.slugify(this.name);
	}

}
