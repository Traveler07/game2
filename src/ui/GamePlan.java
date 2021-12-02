package ui;

import entity.Ball;
import entity.Fire;
import entity.Plane;
import util.App;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePlan extends JPanel {
    BufferedImage bg;
    BufferedImage money;
    BufferedImage st;
    BufferedImage ed;
    int i ;
    Plane hero = new Plane();
    List<Ball> balls = new ArrayList<>();
    Thread1 thread1 = new Thread1();
    public GamePlan(){
        setBackground(Color.BLACK);
        bg = ImageUtil.getImage("/Img/BG.png");
        money = ImageUtil.getImage("/Img/money.png");
        st = ImageUtil.getImage("/Img/st.png");
        ed = ImageUtil.getImage("/Img/ed.png");

    }
    public void action(){
        MouseAdapter adapter = new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int mx = e.getX();
                hero.moveTo(mx);
                repaint();
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        thread1.start();
    }
    class Thread1 extends Thread{
        public void run() {
                    while(true){
                        hero.shoot();
                        hero.fireMove();
                        ballsEnter();
                        ballsMove();
                        shootBall();
                        repaint();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
        }
    }
    private void shootBall() {
        for (int j = 0; j < balls.size(); j++) {
            Ball ball = balls.get(j);
            bang(ball);
        }
    }

    private void bang(Ball ball) {
        for (int j = 0; j < hero.getFires().size(); j++) {
            Fire fire = hero.getFires().get(j);
            if (ball.shoot(fire)){
                ball.changNum();
                hero.getFires().remove(j);
                if(ball.changNum()){
                    balls.remove(ball);
                }
            }
        }
    }

    private void ballsMove() {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            ball.move();
        }
    }
    int l = 0 ;
    private void ballsEnter() {
        System.out.println(i);
        if (i >= App.BALL_COUNT&&balls.size()<App.BALL_NUM) {
            Ball b = new Ball();
            balls.add(b);
            i = 0 ;
        }
        i++;
    }

    public void paint(Graphics g){
        g.drawImage(bg,0,0,App.WIDTH,App.HEIGHT-10,null);

        g.drawImage(money,300,10,30,30,null);
        g.setColor(Color.white);
        g.setFont(new Font("楷体",Font.BOLD ,25));
        g.drawString("0.00",340,32);
        g.drawString("分数：",30,30);
        g.setFont(new Font("楷体",Font.BOLD ,20));
        g.drawString("关卡",30,60);
        g.drawString("历史最高：",300,60);
        g.drawImage(st,20,80,5,25,null);
        g.drawImage(ed, App.WIDTH-30,80,5,25,null);
        hero.draw(g);
        for (int i = 0; i < 30; i++) {
            g.setColor(Color.gray);
            g.fillRect(30+i*15,80,10,25);
        }
        for (int i = 0; i < 10; i++) {
            g.setColor(Color.cyan);
            g.fillRect(30+i*15,80,10,25);
        }
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }
}
