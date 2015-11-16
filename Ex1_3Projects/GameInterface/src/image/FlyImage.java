package image;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FlyImage extends JComponent implements ChangeListener {

	private static final long serialVersionUID = -8479397590475220694L;

	Image i;
	int posX;
	int posY;

	public FlyImage() {
		super();
		this.i = new ImageIcon("fliege-t20678.jpg").getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(i, posX, posY, null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
