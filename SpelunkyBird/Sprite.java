/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script used to pull in new
		sprites. It's here to save space.
***************************************************/
package SpelunkyBird;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite  {
	
	public static BufferedImage getSprite(String fileName) throws IOException {
		return ImageIO.read(Sprite.class.getResourceAsStream(fileName));
	}
	
}