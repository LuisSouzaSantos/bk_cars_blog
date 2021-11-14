package br.com.supersim.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import br.com.supersim.blog.enums.Fuel;
import br.com.supersim.blog.utils.Utils;

@Entity
public class Car {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "NAME_IS_MANDATORY")
	private String name;
	
	private String model;
	
	private Integer year;
	
	private String fipe;
	
	private Fuel fuel;
	
	@Column(unique = true)
	private String photoKey;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	public Car(String name, String model, Integer year, String fipe, Fuel fuel, String photoKey, Brand brand) {
		this.setName(name);
		this.setModel(model);
		this.year = year;
		this.setFipe(fipe);
		this.fuel = fuel;
		this.photoKey = photoKey;
		this.brand = brand;
	}

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
		this.name = Utils.sanitize(name);
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = Utils.sanitize(model);
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getFipe() {
		return fipe;
	}

	public void setFipe(String fipe) {
		this.fipe = Utils.sanitize(fipe);
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public String getPhotoKey() {
		return photoKey;
	}

	public void setPhotoKey(String photoKey) {
		this.photoKey = photoKey;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}