package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="world_countries")
@Entity
public class World {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String continent;
	private String area;
	private Long population;
	private Long gdp;
	private String capital;
	
	public World() {
		super();
	}

	public World(Integer id, String name, String continent, String area, Long population, Long gdp, String capital) {
		super();
		this.id = id;
		this.name = name;
		this.continent = continent;
		this.area = area;
		this.population = population;
		this.gdp = gdp;
		this.capital = capital;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public Long getGdp() {
		return gdp;
	}

	public void setGdp(Long gdp) {
		this.gdp = gdp;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	
}
