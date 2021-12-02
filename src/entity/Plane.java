package entity;

import util.App;
import util.ImageUtil;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Plane extends Entity{
    private  int i ;
    private List<Fire> fires = new ArrayList<>();
    public Plane(){
        image = ImageUtil.getImage("/Img/hero.png");
        h = image.getHeight()/2 ;
        w = image.getWidth()/2 ;
        x = 200;
        y = 642;
    }
    public void moveTo(int mx) {
        this.x = mx - w/2 ;
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,w,h,null);
        for (Fire f : fires) {
            f.draw(g);
        }
    }
    public void shoot(){
        i++ ;
        if (i>= App.FIRE_NUM) {
            Fire f1 = new Fire(x + 20, y);
            Fire f2 = new Fire(x + 50, y);
            fires.add(f1);
            fires.add(f2);
            i = 0 ;
        }
    }

    public List<Fire> getFires() {
        return fires;
    }

    public void fireMove() {
        for (int i = 0; i < fires.size(); i++) {
            Fire f = fires.get(i);
            f.move();
            if(f.move()){
                fires.remove(f);
            }
        }
    }
}
