package common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	public String name = "";
	public double price;
	public int itemCount;

	public Product() {

	}

	public Product(String name, double price, int itemCount) {
		this.name = name;
		this.price = price;
		this.itemCount = itemCount;
	}

	@Override
	public String toString() {

		return new StringBuilder(" Item : ").append(this.name)
				.append(" ,  Price : ").append(this.price)
				.append(" ,  ItemCount : ").append(this.itemCount).toString();
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

}
