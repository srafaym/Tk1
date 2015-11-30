package soapserverpackage;

public class Product {
	public Product() {

	}

	public Product(String name, double price, int quantity) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String name;
	public double price;
	public int quantity;

	@Override
	public String toString() {

		return new StringBuffer(" name : ").append(this.name)
				.append(" price : ").append(this.price).append(" quantity : ")
				.append(this.quantity).toString();
	}
}
