package com.puyixiaowo.rsnake.test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;  
  
public class Test extends JFrame implements ActionListener {  
    private int now_s=0;  
      public Test(){  
          new Timer(1000,this).start();  
          while(true);  
      }  
  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        System.out.println("now_s="+now_s);  
        now_s++;  
    }  
      
    public static void main(String[] args){  
        new Test();  
    }  
}  