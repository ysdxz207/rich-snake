package com.puyixiaowo.rsnake.test;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * 弹出dialog
 * @author weishaoqiang
 * @date 2016年12月25日 上午1:10:31
 */
public class MyFrame extends JFrame {

    public void MyFrame(){
        JFrame jf=new JFrame();//实例化一个JFrame对象
        Container container=jf.getContentPane();//将窗体转化为容器
        //Container container=getContentPane();
        container.setLayout(null);
        
        JLabel jl=new JLabel("这是一个JFrame窗体");//在窗体中设置标签
        jl.setHorizontalAlignment(JLabel.CENTER);//将标签中的文字置于标签中间的位置
        container.add(jl);//将标签添加到容器中
        
        JButton jb=new JButton("点我");//实例化一个按钮属性
        jb.setBounds(20, 20,100, 50);
        jb.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //使MyJDialog窗体可见
                new MyJDialog(MyFrame.this).setVisible(true);
            //上面一句话使对话框窗体可见，这样就实现了当用户单机该按钮后将弹出对话框的功能
            }
        });
        container.add(jb);//将按钮属性添加到容器中
        
        //设置容器里面的属性特点
        container.setBackground(Color.blue);
        //设置容器的框架结构特性
        jf.setTitle("这是一个容器");//设置容器的标题
        jf.setVisible(true);//设置容器可视化
        jf.setSize(450, 400);//设置容器的大小
        //设置容器的关闭方式
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyFrame fm=new MyFrame();
        fm.MyFrame();
    }

}

class MyJDialog extends JDialog{
    //本实例代码可以看到，JDialog窗体和JFrame窗体形式基本相同，甚至在设置窗体的特性
    //时调用的方法名称都基本相同，如设置窗体的大小，设置窗体的关闭状态等
    public MyJDialog(MyFrame frame){//定义一个构造方法
        //实例化一个JDialog类对象，指定对话框的父窗体，窗体标题，和类型
        super(frame,"第一个JDialog窗体",true);
        Container container=getContentPane();//创建一个容器
        container.add(new JLabel("这是一个对话框"));//在容器中添加标签
        container.setBackground(Color.green);
        setBounds(120,120,100,100);
        
    }
}