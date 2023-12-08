package com.enzochen.puzzlegame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener,ActionListener {

    //创建功能选项，关于我们选项
    JMenu functionJMenu = new JMenu("功能");
    JMenu aboutJMenu = new JMenu("关于我们");
    JMenu changeJMenu = new JMenu("更换图片");

    //创建条目
    JMenuItem restartJMenuItem = new JMenuItem("重新游戏");
    JMenuItem reLoginJMenuItem = new JMenuItem("重新登录");
    JMenuItem closeJMenuItem = new JMenuItem("关闭游戏");

    JMenuItem gzhJMenuItem = new JMenuItem("公众号");

    JMenuItem girlJMenuItem = new JMenuItem("美女");
    JMenuItem animalJMenuItem = new JMenuItem("动物");
    JMenuItem sportJMenuItem = new JMenuItem("运动");

    //跟游戏相关的所有逻辑都写在这个类中
    //创建数据数组
    int data[][]=new int[4][4];
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    int step=0;

    //这次使用的土坯名
    String filePath="image\\sport\\sport1";

    public GameJFrame() throws HeadlessException{

        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱图片顺序）
        initData();


        //初始化图片
        initImage();

        //让界面显示出来
        this.setVisible(true);
    }

    private void initData() {
        step=0;
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            int ranInt=random.nextInt(arr.length);
            int temp=arr[i];
            arr[i]=arr[ranInt];
            arr[ranInt]=temp;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j]=arr[i*4+j];
            }
        }

    }

    private void initImage() {

        this.getContentPane().removeAll();
        JLabel steps=new JLabel("步数："+String.valueOf(step));
        steps.setBounds(40,30,100,50);
        this.getContentPane().add(steps);
        if(victory()){
            System.out.println("你赢了！");
            JLabel winner = new JLabel(new ImageIcon("image\\win.png"));
            winner.setBounds(203,283,197,73);
            this.getContentPane().add(winner);

        }
        int place=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                place=data[i][j];
                //创建JLable对象并制定图片位置，并把ImageIcon对象传递给JLable
                JLabel jLabel = new JLabel(new ImageIcon(filePath+"\\"+place+".jpg"));
                //指定图片位置
                jLabel.setBounds(j*105+83,i*105+134,105,105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //将JLable对象添加到整个界面中
                this.getContentPane().add(jLabel);
//                this.add(jLabel1);
            }

        }
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);


        this.getContentPane().repaint();
    }

    private boolean victory() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    private void initJMenuBar() {
        //创建菜单
        JMenuBar jMenuBar = new JMenuBar();



        //把条目添加到选项中
        functionJMenu.add(restartJMenuItem);
        functionJMenu.add(reLoginJMenuItem);
        functionJMenu.add(closeJMenuItem);
        aboutJMenu.add(gzhJMenuItem);
        changeJMenu.add(girlJMenuItem);
        changeJMenu.add(animalJMenuItem);
        changeJMenu.add(sportJMenuItem);

        //把选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        jMenuBar.add(changeJMenu);

        //给条目绑定事件
        restartJMenuItem.addActionListener(this);
        reLoginJMenuItem.addActionListener(this);
        closeJMenuItem.addActionListener(this);
        gzhJMenuItem.addActionListener(this);
        girlJMenuItem.addActionListener(this);
        animalJMenuItem.addActionListener(this);
        sportJMenuItem.addActionListener(this);


        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603,680);
        //设置界面的标题
        this.setTitle("拼图小游戏 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //添加键盘时间监听器
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65){
            //先将当前界面清除
            this.getContentPane().removeAll();
            //创建JLable对象并制定图片位置，并把ImageIcon对象传递给JLable
            JLabel all = new JLabel(new ImageIcon(filePath+"\\all.jpg"));
            //指定图片位置
            all.setBounds(83,134,420,420);
            //将JLable对象添加到整个界面中
            this.getContentPane().add(all);
            //添加背景，步骤与上面相似
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
//            System.out.println("a");
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        if (e.getKeyCode()==65){
            initImage();
        }

        int x=0,y=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j]==0) {
                    x=i;
                    y=j;
                }
            }
        }
        if(e.getKeyCode()==87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            step++;
            initImage();
        }
        int code=e.getKeyCode();
        if (code==37){
            if(y==0){
                System.out.println("不能向上移动啦");
            }else{
                System.out.println("向上移动");
                data[x][y]=data[x][y-1];
                data[x][y-1]=0;
                y--;
                step++;
                initImage();
            }

        }
        if (code==38){


            if(x==0){
                System.out.println("不能向左移动啦");
            }else{
                System.out.println("向左移动");
                data[x][y]=data[x-1][y];
                data[x-1][y]=0;
                x--;
                step++;
                initImage();
            }

        }
        if (code==39){

            if(y==3){
                System.out.println("不能向右移动啦");
            }else{
                System.out.println("向右移动");
                data[x][y]=data[x][y+1];
                data[x][y+1]=0;
                y++;
                step++;
                initImage();
            }

        }
        if (code==40){

            if(x==3){
                System.out.println("不能向下移动啦");
            }else{
                System.out.println("向下移动");
                data[x][y]=data[x+1][y];
                data[x+1][y]=0;
                x++;
                step++;
                initImage();
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 检查事件来源
        Object source = e.getSource();

        if (source == restartJMenuItem) {
            // 处理 Restart 菜单项的逻辑
            System.out.println("重新游戏");
            initData();
            initImage();
        } else if (source == reLoginJMenuItem) {
            // 处理 Re-login 菜单项的逻辑
            System.out.println("重新登录");
            //关闭当前的游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (source == closeJMenuItem) {
            // 处理 Close 菜单项的逻辑
            System.out.println("游戏结束");
            System.exit(0);
        } else if (source == gzhJMenuItem) {
            // 处理 GZH 菜单项的逻辑
            System.out.println("公众号");
            JDialog jDialog = new JDialog();
            jDialog.setSize(344,344);

            JLabel gzh = new JLabel(new ImageIcon("image\\about.png"));
            gzh.setBounds(0,0,258,258);

            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);

            jDialog.getContentPane().add(gzh);
            gzh.setBounds(0,0,258,258);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }else if (source == girlJMenuItem){
            System.out.println("美女图片");
            Random random = new Random();
            int num=random.nextInt(13)+1;
            filePath="image\\girl\\girl"+num;
            initData();
            initImage();
        }else if (source == animalJMenuItem){
            System.out.println("动物图片");
            Random random = new Random();
            int num=random.nextInt(8)+1;
            filePath="image\\animal\\animal"+num;
            initData();
            initImage();
        }else if (source == sportJMenuItem){
            System.out.println("运动图片");
            Random random = new Random();
            int num=random.nextInt(10)+1;
            filePath="image\\sport\\sport"+num;
            initData();
            initImage();
        }
    }
}
