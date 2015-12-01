package soap_server;


public class Product {
	public String itemname = "";
	public double price = 0.0;
	public int available_in_store = 0;
	public int item_id = 0;
	public int item_unique_id = 0;
	
	public Product() {

	}
	
	public Product(int item_unique_id,String itemname, double price, int available_in_store) {
		super();
		this.item_unique_id = item_unique_id;
		this.itemname = itemname;
		this.price = price;
		this.available_in_store = available_in_store;
	}

	@Override
	public String toString() {

		return new StringBuffer(" Item : ").append(this.itemname)
				.append("  ,Price : ").append(this.price).append(" ,Amount : ")
				.append(this.available_in_store).append("   Danke").toString();
	}
}
