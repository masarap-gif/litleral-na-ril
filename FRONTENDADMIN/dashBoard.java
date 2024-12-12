/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;

import DSA.LinkedListAccounts;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ivan
 */
public class dashBoard extends parentComponent implements ActionListener{
    LinkedListAccounts acc;
       UserService userService;
    JPanel userPan = new JPanel();
    JPanel pan1 = new JPanel();
    JButton generateReports = new JButton("Generate Reports");
    JButton accountManagement = new JButton("Account Management");
    JLabel w = new JLabel("W");
    JLabel welcome = new JLabel("elcome to your dashboard, ");
    User user;
             
    public dashBoard(UserService userService, LinkedListAccounts acc) {
         
         this.acc = acc;
         this.userService = userService;
         
        userPan.setLayout(null);
        userPan.setBackground(new Color(0xD9D9D9));
        userPan.setBounds(0, 0, 1530, 86);
        

        pan1.setSize(1476, 896);
        pan1.setBackground(new Color(0x6F1D1B));
        pan1.setLayout(null);
       
        JButton exit = new JButton();
         exit.setBounds(1259, 15, 187, 56);
        exit.setBackground(new Color(0xBB9457));
        exit.setLayout(null);
        JLabel d = new JLabel("Exit");
        d.setBounds(50, 10, 150, 47);
        d.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        d.setForeground(Color.white);
        exit.add(d);

        exit.addActionListener(e -> {
            System.out.println("Exit button clicked");
            ((JFrame) SwingUtilities.getWindowAncestor(exit)).dispose();
        });

        generateReports.setBackground(new Color(0xBB9457));
        generateReports.setBounds(100, 332, 500, 250);
        generateReports.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        generateReports.setForeground(new Color(0xFFFFFF));
        
        accountManagement.setBackground(new Color(0xBB9457));
        accountManagement.setBounds(800, 332, 500, 250);
        accountManagement.setFont(new Font("Bebas Neue", Font.BOLD, 35));
        accountManagement.setForeground(new Color(0xFFFFFF));
        
        w.setForeground(new Color(0xBB9457));
        w.setBounds(65, 120, 200, 200);
        w.setFont(new Font("Bebas Neue", Font.BOLD, 110));

        welcome.setForeground(new Color(0xBB9457));
        welcome.setBounds(156, 142, 800, 200);
        welcome.setFont(new Font("Bebas Neue", Font.BOLD, 55));
        
        userPan.add(exit);
        
        
        add(pan1);
        pan1.add(generateReports);
        pan1.add(accountManagement);
        pan1.add(w);
        pan1.add(welcome);
        this.add(userPan);
        this.add(pan1);
        this.setSize(1476, 896);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x6F1D1B));
        this.setIconImage(icon);
        this.setTitle("DashBoard");
        this.setResizable(false);

        generateReports.addActionListener(this);
        accountManagement.addActionListener(this);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
         LinkedListAccounts accountsList = new LinkedListAccounts();
       // dashBoard dashb = new dashBoard(accountsList);
       // dashb.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateReports) {
            adminReports reportsWindow = new adminReports(acc,userService);
            reportsWindow.setVisible(true);
            this.setVisible(false); 
        } else if (e.getSource() == accountManagement) {
            adminCOA reportsWindow = new adminCOA(userService,acc);
          //  reportsWindow.setupUI();
            reportsWindow.setVisible(true);
            this.setVisible(false);
        }
    }
}
