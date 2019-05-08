package jarsofttest.banner;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jarsofttest.category.Category;

@Entity
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	@Size(min = 2)
//@Column(unique = true)
	private String name;
	@NotNull
	private double price;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category catid;
	@NotNull
	@Size(min = 2)
	private String text;
	@NotNull
	private boolean deleted;

	public Banner() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCatid() {
		return catid;
	}

	public void setCatid(Category categoryId) {
		this.catid = categoryId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", name=" + name + ", price=" + price + ", categoryId=" + catid + ", text=" + text
				+ ", deleted=" + deleted + "]";
	}

}
