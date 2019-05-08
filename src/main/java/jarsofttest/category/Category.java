package jarsofttest.category;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jarsofttest.banner.Banner;

@Entity

public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	@Size(min = 2)
	private String name;
	@NotNull
	@Size(min = 2)
	private String reqName;
	private boolean deleted;
	@OneToMany(mappedBy = "catid", cascade = CascadeType.ALL)
	private Set<Banner> banners;

	public Category() {
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String req_name) {
		this.reqName = req_name;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", req_name=" + reqName + ", deleted=" + deleted + "]";
	}

	public Set<Banner> getBanners() {
		return banners;
	}

	public void setBanners(Set<Banner> b) {
		this.banners = b;
	}

}