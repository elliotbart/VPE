package beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Game implements Serializable {
	
	@Id
	@Column(name="pk_title")
	private String title;
	
	private Double price;
	
	@Column(name="fk_console")
	private String console;
	
	@Column(name="description")
	private String description;
	
	public Game() {
		
	}
	
	public Game(String title, String console, Double price, String description) {
		this.title = title;
		this.console = console;
		this.price = price;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

