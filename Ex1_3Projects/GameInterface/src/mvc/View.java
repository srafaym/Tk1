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

/**
 * This class represents the view of the game for every game client. It uses the
 * controller to send messages to the server, and receives messages from the
 * Model on the server.
 */
public class View {

	private JFrame frame;
	private JLabel playersLabel;

	private Controller controller;

	private String playerName;

	private FlyImage fly = new FlyImage();

	private Dimension windowSize;

	public View(String playerName, Controller controller) {
		this.playerName = playerName;
		this.controller = controller;
	}

	public void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Catch the fly");

		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		fly.setVisible(false);
		// Add a component presenting the fly.
		contentPane.add(fly, BorderLayout.CENTER);

		// add button quit and player list
		JPanel menuPanel = new JPanel();
		FlowLayout experimentLayout = new FlowLayout();
		menuPanel.setLayout(experimentLayout);

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
		menuPanel.add(quitButton);
		menuPanel.add(playersLabel);

		contentPane.add(menuPanel, BorderLayout.NORTH);

		contentPane.setPreferredSize(windowSize);

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
		// Make the window not resizable, so that the fly is bound to be visible
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	public void addFlyAtPosition(Point point) {
		fly.setPosX(point.x);
		fly.setPosY(point.y);
		fly.setVisible(true);
		fly.repaint();
		frame.revalidate();
		frame.repaint();
	}

	private void printNewPoints(HashMap<String, Integer> points) {
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
		printNewPoints(points);
	}

	public void receiveWindowSize(Dimension windowSize) {
		this.windowSize = windowSize;
	}

}
