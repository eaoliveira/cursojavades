package Thread.mario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Mario {
	private AnimatedSprite sprite;
	private JFrame frame;
	private boolean direita = true;
	private static final GraphicsConfiguration CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice().getDefaultConfiguration();

	public Mario(JFrame frame, int pos) {
		this.frame = frame;

		BufferedImage[] imgs = splitImages(createTransparentImage("mario2.png", null), 6, 4);

		sprite = new AnimatedSprite(imgs);
		sprite.addNewAnimation("walk-right", new int[] { 9, 10, 11 });
		sprite.addNewAnimation("walk-left", new int[] { 21, 22, 23 });
		sprite.setAnimation("walk-right");
		sprite.setLocation(5, pos * imgs[0].getHeight() + 25);
	}

	public void draw(Graphics2D g) {
		g.setColor(g.getBackground());

		Rectangle2D img;
		if (direita)
			img = new Rectangle2D.Double(sprite.getX(), sprite.getY(), 11, sprite.getHeight() + 1);
		else
			img = new Rectangle2D.Double(sprite.getX() + sprite.getWidth() - 11, sprite.getY(), 11,
					sprite.getHeight() + 1);

		g.fill(img);
		g.draw(img);

		int width = frame.getWidth();

		if (direita) {
			if (sprite.getX() + 10 + sprite.getWidth() < width - 5) {
				sprite.setX(sprite.getX() + 10);
			} else {
				direita = false;
				sprite.setAnimation("walk-left");
			}
		} else {
			if (sprite.getX() - 10 > 5) {
				sprite.setX(sprite.getX() - 10);
			} else {
				direita = true;
				sprite.setAnimation("walk-right");
			}
		}

		sprite.draw(g);
	}

	public BufferedImage[] splitImages(BufferedImage image, int col, int row) {
		int total = col * row;
		int num = 0;
		int w = image.getWidth() / col;
		int h = image.getHeight() / row;
		BufferedImage[] imgs = new BufferedImage[total];

		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				int transparency = image.getColorModel().getTransparency();
				imgs[num] = CONFIG.createCompatibleImage(w, h, transparency);

				Graphics2D g = imgs[num].createGraphics();
				g.drawImage(image, 0, 0, w, h, x * w, y * h, (x + 1) * w, (y + 1) * h, null);
				g.dispose();
				num++;
			}
		}

		return imgs;
	}

	public static BufferedImage createTransparentImage(String img, Color fundo) {
		String imgName = "Thread/mario/" + img;
		Image image = new ImageIcon(Mario.class.getClassLoader().getResource(imgName)).getImage();
		BufferedImage buffImg = CONFIG.createCompatibleImage(image.getWidth(null), image.getHeight(null),
				Transparency.BITMASK);
		Graphics2D g2d = buffImg.createGraphics();
		g2d.setComposite(AlphaComposite.Src);
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		Color cor = new Color(buffImg.getRGB(0, 0));

		for (int k = 0; k < buffImg.getHeight(); k++) {
			for (int l = 0; l < buffImg.getWidth(); l++) {
				int color = buffImg.getRGB(l, k);
				if (color == cor.getRGB()) {
					buffImg.setRGB(l, k, color & 0x00ffffff);
				}
			}
		}

		if (fundo != null) {
			BufferedImage newImg = new BufferedImage(buffImg.getWidth(), buffImg.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			g2d = newImg.createGraphics();
			g2d.setColor(fundo);
			g2d.fillRect(0, 0, buffImg.getWidth(), buffImg.getHeight());
			g2d.drawImage(buffImg, 0, 0, null);
			g2d.dispose();
			buffImg = newImg;
		}

		return buffImg;
	}

}
