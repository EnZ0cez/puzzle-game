package com.enzochen.puzzlegame;

import javax.swing.*;
import java.awt.*;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame() throws HeadlessException {
        this.setSize(488,500);

        //设置界面的标题
        this.setTitle("拼图 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        this.setVisible(true);
    }
}
