package ui;

import util.App;

import javax.swing.*;

public class GameFrame extends JFrame {
   public GameFrame(){
       setTitle("123123");//标题
       setSize(App.WIDTH,App.HEIGHT);//窗口大小
       setLocationRelativeTo(null);//相对于（）居中
       setResizable(false);//是否可调节大小
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口后结束进程
   }
   public static void main(String [] ages){
       GameFrame frame = new GameFrame();
       GamePlan plan = new GamePlan();
       frame.add(plan);
       plan.action();
       frame.setVisible(true);//设置窗口可见性
   }
}
