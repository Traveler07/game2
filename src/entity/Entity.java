package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
    BufferedImage image ;
    int x ;
    int y ;
    int w ;
    int h ;
    public void draw(Graphics g) {
        g.drawImage(image,x,y,w,h,null);
    }
}
