/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginAndSignUp;
import FRONTENDUSER.*;
import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import DSA.NodeAccounts;
import FRONTENDADMIN.dashBoard;
import FRONTENDLIB.DASHBOARDUI;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ivan
 */

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ivan
 */
public class LoginSignup extends parentcomponent{
     public static   arryList sharedTransac = new arryList();
    static  LinkedlistBook sharedBookList = new LinkedlistBook();
   static  LinkedListAccounts acc = new LinkedListAccounts();
   static LLhistory history = new LLhistory();
   static User user = new User();

    
    JLabel title = new JLabel();
    JLabel mang = new JLabel();
    JLabel sys = new JLabel();
    JLabel labels = new JLabel();
   
    JButton usersBTN = new JButton();
    JButton AdminBTN = new JButton();
    JButton libBTN = new JButton();
    
    LoginSignup(){
            frame = new JFrame();
        frame.setSize(1758, 1020);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setIconImage(icon);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Create background panel with image
        JPanel red = new JPanel(null);
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                ImageIcon imageIcon = new ImageIcon("images\\nice.gif"); 
//                Image image = imageIcon.getImage();
//                g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
//            }
//        };
        red.setBounds(0, 0, 1758, 1020);
        red.setBackground(Color.MAGENTA);
      

        // Title setup
        title.setText("BookFlow");
        title.setFont(new Font("Bebas Neue", Font.BOLD, 140));
        title.setForeground(Color.white);
        title.setBounds(560, 129, 800, 194);

        // Management label setup
        mang.setText("Library Transaction System");
        mang.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 38));
        mang.setForeground(Color.white);
        mang.setBounds(643, 296, 600, 51);

        // Adding elements to red panel
        red.add(title);
        
        red.add(mang);
        
        // Add the red background panel first
    

        // Add the login panel next
        JPanel login = loginPanel();
        frame.add(login);
        
             frame.add(red);
        
        

        // Make the frame visible after all components are added
      
    
  frame.setVisible(true);   
    }
    public JPanel loginPanel(){
            // Login Panel
        JPanel login = new JPanel();
        login.setLayout(null);
        login.setSize(719, 440);
        login.setBounds(519, 419, 719, 440);
        login.setBackground(new Color(0xFFFFFF));
        login.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        

        // Title Label
        JLabel title = new JLabel("Log - In");
        title.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 100));
        title.setForeground(new Color(0x6F1D1B));
        title.setBounds(180, 13, 1000, 140);
       

        // Username Label
        JLabel us = new JLabel("Username :");
        us.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        us.setForeground(new Color(0xBB9457));
        us.setBounds(34, 174, 500, 37);
     

        // Password Label
        JLabel pass = new JLabel("Password :");
        pass.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        pass.setForeground(new Color(0x99582A));
        pass.setBounds(34, 284, 500, 37);
       

        // Username Text Field
        JTextField users = new JTextField();
        users.setBounds(250, 174, 450, 48);
        users.setBackground(new Color(0xBB9457));
        users.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 29));
        users.setForeground(Color.white);
      
    

        // Password Field
        JPasswordField passField = new JPasswordField();
        passField.setBounds(250, 284, 450, 48);
        passField.setBackground(new Color(0x99582A));
        passField.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 29));
           passField.setForeground(Color.white);
       
        
        JButton log = new JButton("Log in");
        log.setBounds(465, 374, 234, 48);
        log.setFont(new Font("Bebas Neue", Font.BOLD,31));
        log.setForeground(Color.white);
        log.setBackground(new Color(0x6F1D1B));
        log.addActionListener(e -> {
            String username = users.getText();
            String passwordInput = new String(passField.getPassword());
            UserService userService = UserService.getInstance();

            if (username.equals("librarian") && passwordInput.equals("lib123")) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                new DASHBOARDUI(sharedBookList, acc, history, user);
                 frame.dispose();
            } else if (username.equals("admin") && passwordInput.equals("admin123")) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                new dashBoard(userService, acc);
                 frame.dispose();
            } else if (userService.validateLogin(username, passwordInput)) {
                NodeAccounts loggedInUser = userService.getLinkedListAccounts().linearSearch(username);
                if (loggedInUser != null) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    if (loggedInUser.getRole().equalsIgnoreCase("librarian")) {
                        new DASHBOARDUI(sharedBookList, acc, history, user);
                         frame.dispose();
                    } else if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                        new dashBoard(userService, acc);
                         frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid role.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "User not found.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        });
          JButton stu = new JButton();
          stu.setText("For Student");
        stu.setBounds(1, 374, 263, 48);
        stu.setFont(new Font("Bebas Neue", Font.ITALIC,32));
        stu.setForeground(new Color(0x6F1D1B));
        stu.setBackground(Color.white);
        stu.setBorder(null);
        stu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                BROWSEUI his = new BROWSEUI(user,sharedBookList,history);
                frame.dispose();
            }
        }
        );
        
        
         login.add(title);
           login.add(us);
       login.add(pass);
       login.add(users);
        login.add(passField);
        login.add(log);
        login.add(stu);

        return login;
    }
    public static void main (String [] args){
        new LoginSignup();
    }
   
}


