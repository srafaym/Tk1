import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.util.LinkedList;

import common.Product;

public class View implements ActionListener {

	JFrame frame;
	String serviceName;
	Collection<Product> products;
	JButton buyButton;
	HashMap<String, JButton> addToCart;
	HashMap<String, JTextField> amount;
	Client client;
	int productCount;
	int clientId;
	JPanel panel;

	public View(String serviceName, LinkedList<Product> products, Client client) {
		// TODO Auto-generated constructor stub
		this.serviceName = serviceName;
		this.products = products;
		System.out.println(products.get(0).name + " "
				+ products.get(0).itemCount);
		this.productCount = products.size();
		this.client = client;
		this.clientId = client.getId();

		this.buyButton = new JButton("Buy");
		this.addToCart = new HashMap<>();
		this.amount = new HashMap<>();

		for (Product product : products) {
			JButton button = new JButton("Add to cart");
			button.addActionListener(this); // Action listener add
			this.addToCart.put(product.name, button);
			this.amount.put(product.name, new JTextField("0"));
		}

	}

	public void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame(serviceName);

		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Container contentPane = frame.getContentPane();
		// contentPane.setLayout(new BorderLayout());

		this.refreshProductPanel();

		buyButton.addActionListener(this); // Action listener add
	}

	private void refreshProductPanel() {
		panel = new JPanel(new SpringLayout());
		String[] columnNames = { "Amount", "Title", "Price", "Available Amount" };

		int rows = productCount + 1;
		int cols = 5;

		// Add column names
		for (int i = 0; i < 4; i++) {
			JLabel l = new JLabel(columnNames[i], JLabel.TRAILING);
			panel.add(l);
		}
		panel.add(buyButton);

		for (Product p : products) {
			// amount

			amount.get(p.name).setText("");
			panel.add(amount.get(p.name));

			// product name
			JLabel name = new JLabel(p.name, JLabel.TRAILING);
			panel.add(name);

			// price
			JLabel price = new JLabel(Double.toString(p.price), JLabel.TRAILING);
			panel.add(price);

			// available
			JLabel a = new JLabel(Integer.toString(p.itemCount),
					JLabel.TRAILING);
			panel.add(a);

			panel.add(addToCart.get(p.name));
		}

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, // parent
				rows, cols, 3, 3, // initX, initY
				3, 3); // xPad, yPad

		// Set up the content pane.
		panel.setOpaque(true); // content panes must be opaque
		frame.setContentPane(panel);

		frame.setResizable(false);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(buyButton)) {
			client.notifyServerWithBuy(clientId);
			this.products = client.getProductsFromServer();
			this.refreshProductPanel();
		} else {
			for (Product product : this.products) {
				if (e.getSource().equals(addToCart.get(product.name))) {
					String productName = product.name;
					String purchaseAmount = amount.get(product.name).getText();
					System.out.println("add to cart " + productName
							+ " clicked with value " + purchaseAmount);
					int a;
					try {
						a = Integer.parseInt(purchaseAmount);

						if (!client.checkItemInStore(new Product(productName,
								0, a))) {
							notifyNotAvailableAmount();
							this.products = client.getProductsFromServer();
							this.refreshProductPanel();
						} else {
							client.addToCart(productName, a);
						}
					} catch (Exception e2) {
						// TODO: handle exception
						notifyInvalidAmount();
					}

				}
			}
		}
	}

	public void notifyInvalidAmount() {
		JOptionPane.showMessageDialog(frame,
				"The product amount needs to be a number.");
	}

	public void notifyNotAvailableAmount() {
		JOptionPane.showMessageDialog(frame,
				"The product amount needs to be in the available range.");
	}

	public void notifyAlreadySold() {
		JOptionPane.showMessageDialog(frame,
				"The product is no longer available.");
	}

}
