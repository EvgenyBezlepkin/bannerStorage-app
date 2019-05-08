package jarsofttest.request;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int bannerId;
	private String userAgent;
	private String ipAddr;
	private LocalDateTime date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBannerId() {
		return bannerId;
	}

	public void setBannerId(int banner_id) {
		this.bannerId = banner_id;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String user_agent) {
		this.userAgent = user_agent;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ip_address) {
		this.ipAddr = ip_address;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", bannerId=" + bannerId + ", date=" + date + "]";
	}
	
	

}
