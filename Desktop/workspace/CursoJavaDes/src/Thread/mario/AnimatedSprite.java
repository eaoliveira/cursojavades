package Thread.mario;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

@SuppressWarnings("unused")
public class AnimatedSprite {
	public static final int LOOP_REVERSE = 2;
	public static final int LOOP_BEGINNING = 3;
	private int loopMethod = LOOP_REVERSE;
	private boolean loopDir = true;
	private double x;
	private double y;
	private double lastX;
	private double lastY;
	private HashMap<String, int[]> frames;
	private BufferedImage[] images;
	private String currentAnim;
	private int currentFrame;
	private int[] currentFrameSet;
	
	public AnimatedSprite(BufferedImage[] images) {
		this.images = images;
		frames = new HashMap<String, int[]>();
	}

	public void addNewAnimation(String name, int[] set) {
		frames.put(name, set);
		setAnimation(name);
	}
	
	public void draw(Graphics2D g) {

		int imgNum = currentFrameSet[currentFrame];

		BufferedImage bi = new BufferedImage(getWidth()+1,getHeight()+1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D imgBuff = bi.createGraphics();

		imgBuff.setColor(g.getBackground());
		Rectangle2D img = new Rectangle2D.Double(0, 0, getWidth()+1, getHeight()+1);
		imgBuff.fill(img);
		imgBuff.draw(img);
		
		imgBuff.drawImage(images[imgNum], 0, 0, null);
		imgBuff.dispose();
		g.drawImage(bi, null, (int)x, (int)y);

		if (currentFrame == currentFrameSet.length - 1) {
			if (loopMethod == LOOP_BEGINNING) {
				currentFrame = 0;
			} else {
				loopDir = false;
				currentFrame--;
			}
		} else {
			if (loopMethod == LOOP_BEGINNING) {
				currentFrame++;
			} else {
				if(currentFrame == 0) {
					loopDir = true;
				}
				if (loopDir) {
					currentFrame++;
				}
				if (!loopDir) {
					currentFrame--;
				}
			}
		}
		lastX = x;
		lastY = y;
	}
	
	public void setAnimation(String name) {
		if(frames.containsKey(name)) {
		currentAnim = name;
		currentFrameSet = frames.get(currentAnim);
		currentFrame = 0;
		}
	}
	
	public void setLoopMethod(int method) {
		if(method == LOOP_REVERSE || method == LOOP_BEGINNING) {
		this.loopMethod = method;
		}
	}
	
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getCurrentAnim() {
		return currentAnim;
	}
	
	public int getWidth() {
		return images[0].getWidth();
	}
	
	public int getHeight() {
		return images[0].getHeight();
	}
}
