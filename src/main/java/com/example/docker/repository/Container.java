// tag::sample[]
package com.example.docker.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.Gson;

@Entity
public class Container {

	@Id
	private String id;
	private String name;
	private int port;
	private Date createDate;
	private String uuid;

	protected Container() {
	}

	public Container(String id, String name, int port) {
		this.id = id;
		this.name = name;
		this.port = port;
		this.createDate = new Date();
	}

	public Container(String id, String name, String uuid) {
		this.id = id;
		this.name = name;
		this.uuid = uuid;
		this.createDate = new Date();
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPort() {
		return port;
	}

	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
