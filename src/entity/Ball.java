package entity;

import util.App;
import util.ImageUtil;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity{
    private int loc ;
    private int dir ;
    private int t1 = 0 ;
    private int t2 = 0 ;
    private int t3 = 0 ;
    private int num ;
    Random r = new Random();
    int rd = r.nextInt(14)+1;
    public Ball(){
        image = ImageUtil.getImage("/Img/c"+rd+".png");
        w = image.getWidth();
        h = image.getHeight();
        loc = r.nextInt(2);
        if(loc == App.BALL_LOC_LEFT){
            x = -w ;
        }else{
            x = App.WIDTH ;
        }
        y = 100;
        num = r.nextInt(15);
    }

    public void draw(Graphics g) {
        String str = (num<10?"0":"") + num ;
        g.drawImage(image,x,y,w,h,null);
        g.setColor(Color.white);
        g.setFont(new Font( "楷体",Font.BOLD,25));
        g.drawString(str,x+w/2 - 10,y+h/2 + 7);
    }

    public void move() {
        int down = r.nextInt(App.WIDTH - w - 170) + 170;
        if(t3<2) {
            if (loc == App.BALL_LOC_LEFT) {
                if (t1 == 0) {
                    x += App.BALL_X_SPEED;
                }
                if (x > down) {
                    dir = App.DIR_LIFE_DOWN;
                    t1 = 1;
                    t3++;
                }
            } else {
                if (t2 == 0) {
                    x -= App.BALL_X_SPEED;
                }
                if (x < down) {
                    dir = App.DIR_RIGHT_DOWN;
                    t2 = 1;
                    t3++;
                }
            }
        }
        if(dir == App.DIR_LIFE_UP){
            //左上
            x-=App.BALL_X_SPEED;
            y-=App.BALL_Y_SPEED;
            if(x<=0){
                dir =App.DIR_RIGHT_UP;
            }
            if(y<=0){
                dir =App.DIR_LIFE_DOWN;
            }
        }else if(dir == App.DIR_RIGHT_UP){
            //右上
            x+=App.BALL_X_SPEED;
            y-=App.BALL_Y_SPEED;
            if(x>=App.WIDTH-w){
                dir = App.DIR_LIFE_UP;
            }
            if(y<=0){
                dir = App.DIR_RIGHT_DOWN;
            }
        }else if(dir == App.DIR_LIFE_DOWN){
            //左下
            x-=App.BALL_X_SPEED ;
            y+=App.BALL_Y_SPEED ;
            if(x <= 0){
                dir = App.DIR_RIGHT_DOWN;
            }
            if(y>=App.HEIGHT-h){
                dir = App.DIR_LIFE_UP;
            }
        }else if(dir == App.DIR_RIGHT_DOWN){
            //右下
            x+=App.BALL_X_SPEED;
            y+=App.BALL_Y_SPEED;
            if(y>=App.HEIGHT-h){
                dir = App.DIR_RIGHT_UP;
            }
            if(x>=App.WIDTH-w){
                dir = App.DIR_LIFE_DOWN;
            }
        }
    }

    public boolean changNum() {
        num--;
        return num<=0;
    }

    public boolean shoot(Fire plane) {
        return x<=plane.x+plane.w&&x>=plane.x-w&&y<=plane.y+plane.h&&y>=plane.y-h;
    }
}
