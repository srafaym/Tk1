package mvc;

import image.FlyImage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;

public class View {

	private HashMap<String, Integer> points = new HashMap<String, Integer>();

	private JFrame frame;
	private JLabel playersLabel;

	private Controller controller;

	private String playerName;

	private FlyImage fly = new FlyImage();

	public View(String playerName, Controller controller) {
		this.playerName = playerName;
		this.controller = controller;
	}

	public void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Catch the fly");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		fly.setVisible(false);
		// Add a component presenting the model.
		contentPane.add(fly, BorderLayout.CENTER);

		// add buttons and player list
		JPanel menuPanel = new JPanel();
		FlowLayout experimentLayout = new FlowLayout();
		menuPanel.setLayout(experimentLayout);

		JButton startButton = new JButton("Do nothing for now");
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.notifyQuit(playerName);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		playersLabel = new JLabel("Players list: ");
		menuPanel.add(startButton);
		menuPanel.add(quitButton);
		menuPanel.add(playersLabel);

		contentPane.add(menuPanel, BorderLayout.NORTH);

	
		fly.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (fly != null) {
					Point clickPoint = e.getPoint();
					// Fly image has width 53 and height 67

					if (clickPoint.getX() >= fly.getPosX()
							&& clickPoint.getX() <= fly.getPosX() + 53
							&& clickPoint.getY() >= fly.getPosY()
							&& clickPoint.getY() <= fly.getPosY() + 67) {
						controller.notifyHit(playerName);
					}
				}
			}
		});

		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);
	}

	public void addFlyAtPosition(int x, int y) {
		System.out.println(x + " " + y);
		fly.setPosX(x);
		fly.setPosY(y);
		fly.setVisible(true);
		fly.repaint();

		frame.revalidate();
		frame.repaint();
	}

	public void changePoints(String playerName, int newPoints) {
		points.put(playerName, newPoints);
		printNewPoints();
	}

	private void printNewPoints() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, Integer> entry : points.entrySet()) {
			builder.append(entry.getKey());
			builder.append(": ");
			builder.append(entry.getValue());
			builder.append("<br>");
		}
		playersLabel.setText("<html> Players List: <br>" + builder.toString()
				+ "</html>");
	}

	public void setPoints(HashMap<String, Integer> points) {
		this.points = points;
		printNewPoints();
	}

	public void removePlayer(String playerName) {
		this.points.remove(playerName);
		printNewPoints();
	}

	public void addPlayer(String playerName) {
		this.points.put(playerName, 0);
		printNewPoints();
	}

}
