package com.eopi.primitives;

/**
 * 
 * @author Shyam | catch.shyambaitmangalkar@gmail.com
 *
 */
public class RectangleUtil {
	public static boolean isIntersect(Rectangle r1, Rectangle r2) {
		return (r1.x <= r2.x + r2.width && r1.y <= r2.y + r2.height && r2.x <= r1.x + r1.width
				&& r2.y <= r1.y + r1.height);
	}
}
