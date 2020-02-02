package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */
public class AddressDTO {

	private Integer id;
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private GeoDTO geo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public GeoDTO getGeo() {
		return geo;
	}

	public void setGeo(GeoDTO geo) {
		this.geo = geo;
	}

	@Override
	public String toString() {
		return "AddressDTO [id=" + id + ", street=" + street + ", suite=" + suite + ", city=" + city + ", zipcode="
				+ zipcode + ", geo=" + geo + "]";
	}
	
	

}
