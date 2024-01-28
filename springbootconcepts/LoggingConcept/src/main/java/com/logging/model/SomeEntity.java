package com.logging.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class SomeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String vehicleId;
	private String geoFence;
	private String messageType;
	private String url;
	private String description;
	private String poi;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getGeoFence() {
		return geoFence;
	}
	public void setGeoFence(String geoFence) {
		this.geoFence = geoFence;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPoi() {
		return poi;
	}
	public void setPoi(String poi) {
		this.poi = poi;
	}
	public SomeEntity(Long userId, String vehicleId, String geoFence, String messageType, String url,
			String description, String poi) {
		super();
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.geoFence = geoFence;
		this.messageType = messageType;
		this.url = url;
		this.description = description;
		this.poi = poi;
	}
	public SomeEntity() {
		super();
	}
	@Override
	public String toString() {
		return "SomeEntity [userId=" + userId + ", vehicleId=" + vehicleId + ", geoFence=" + geoFence + ", messageType="
				+ messageType + ", url=" + url + ", description=" + description + ", poi=" + poi + "]";
	}

	
}
