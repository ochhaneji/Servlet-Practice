package com.nt.bo;

import java.io.Serializable;

public class RestaurantBO  implements Serializable {
	private int restId;
	private String restName;
	private String restLocation;
	private String rating;
	private int priceFor2;
	private  String category;
	private  String status;
	
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestLocation() {
		return restLocation;
	}
	public void setRestLocation(String restLocation) {
		this.restLocation = restLocation;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public int getPriceFor2() {
		return priceFor2;
	}
	public void setPriceFor2(int priceFor2) {
		this.priceFor2 = priceFor2;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
