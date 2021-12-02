package entity;

import util.App;
import util.ImageUtil;

public class Fire extends Entity{
    public Fire(int px,int py){
         image = ImageUtil.getImage("/Img/fire.png") ;
         w = image.getWidth()/2;
         h = image.getHeight()/2;
         x = px ;
         y = py ;
    }
    public boolean move() {
        y-= App.FIRE_SPEED ;
        return y <= -h  ;
    }
}
