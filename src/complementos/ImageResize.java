package complementos;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
// TODO: Auto-generated Javadoc

/**
 * The Class ImageResize.
 *
 * @author Ingrid-E & Internet
 * @version 1.0
 * Class created to resize images such as gifs,png,jpg etc..
 * Found on the internet and modified to meet game requirements.
 */
public class ImageResize {
	
	/** The height. */
	//Atributes
	private int width, height;
	
	/** The image type. */
	private String imageType;
	
	/** The image. */
	private ImageIcon image;
	//Methods
	/**
	 * Image Resize constructor.
	 *
	 * @param image the image
	 * @param width the width
	 * @param height the height
	 */
	public ImageResize(ImageIcon image, int width, int height) {
		this.width = width;
		this.height = height;
		this.image = image;
		this.imageType = image.getDescription();
		this.imageType = this.imageType.substring(this.imageType.indexOf(".")+1, this.imageType.length());
	}
	
	/**
	 * Image Resize constructor.
	 *
	 * @param image the image
	 * @param width the width
	 * @param height the height
	 */
	public ImageResize(Icon image, int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new ImageIcon(iconToImage(image));
		this.imageType = image.toString();
		this.imageType = this.imageType.substring(this.imageType.indexOf(".")+1, this.imageType.length());
	}
	
	/**
	 * Changed the icon object to an Image object so it 
	 * can be manipulated and resized.
	 *
	 * @param icon the icon
	 * @return Image
	 */
	private Image iconToImage(Icon icon) {
		if (icon instanceof ImageIcon) {
		      return ((ImageIcon)icon).getImage();
		   } 
		   else {
		      int w = icon.getIconWidth();
		      int h = icon.getIconHeight();
		      GraphicsEnvironment ge = 
		      GraphicsEnvironment.getLocalGraphicsEnvironment();
		      GraphicsDevice gd = ge.getDefaultScreenDevice();
		      GraphicsConfiguration gc = gd.getDefaultConfiguration();
		      BufferedImage image = gc.createCompatibleImage(w, h);
		      Graphics2D g = image.createGraphics();
		      icon.paintIcon(null, g, 0, 0);
		      g.dispose();
		      return image;
		   }
	}
	
	/**
	 * Resizes the image and returns it with the new size.
	 *
	 * @return ImageIcon
	 */
	public ImageIcon resize() {
		Image original = this.image.getImage();
		Image originalResized;
		if(this.imageType.equals("gif")) {
			originalResized = original.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		}else {
			originalResized = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}
		ImageIcon resized = new ImageIcon(originalResized);
		return resized;
	}
	
	
}
