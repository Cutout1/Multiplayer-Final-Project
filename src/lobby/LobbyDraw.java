package lobby;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import battleShips.BattleShips;

public class LobbyDraw extends Canvas {

	private JFrame frame;
	private Image image;
	private int time;
	public boolean onScreen;

	private int[][] points;

	public LobbyDraw() {

		super();

		try {
			image = ImageIO.read(new File("src/images/titlescreenAPCS.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		onScreen = true;
		setSize(1000, 600);
		setBackground(Color.green);
	}

	public void updatePoints(int[][] thePoints) {
		points = thePoints;
		System.out.println(points);
	}

	public void paint(Graphics g) {
		if (onScreen == true) {
			Graphics2D g2d = (Graphics2D) g;

			g2d.drawImage(image, 0, 0, this);

			int x = MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x;
			int y = MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y;

			g2d.setColor(Color.GREEN);
			g2d.setFont(new Font("Helvetica", Font.PLAIN, 36));

			if (y > 0 && y < 600) {
				if (x <= 250) {
					// battle ships
					g2d.drawString("BattleShips", 10, 50);
				} else if (x <= 500) {
					// tic tac toe
					g2d.drawString("Tic Tac Toe", 260, 50);
				} else if (x <= 750) {
					// rock paper scissors
					g2d.drawString("Rock Paper Scissors", 510, 50);
				} else if (x <= 1000) {
					// snake bikes
					g2d.drawString("Snake Bikes", 760, 50);
				}
			}
		}
	}
}
