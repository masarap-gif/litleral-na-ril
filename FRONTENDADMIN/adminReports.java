/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;

/**
 *
 * @author ivan
 */

  

import BACKENDLIB.arryList;
import DSA.LinkedListAccounts;
import DSA.NodeTransac;
import LogSigBackEnd.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

;

public class adminReports extends parentComponent {

    JPanel pan3 = new JPanel();
    JPanel pan4 = new JPanel();
    JPanel pan4_5 = new JPanel();
    JLabel genrep = new JLabel("Generate Reports");
    JLabel datelbl = new JLabel("Report Date");
    JLabel mostborrlbl = new JLabel("Most Borrowed Books");
    JLabel totalborrlbl = new JLabel("Total Borrowed Books");
    JLabel totalfinelbl = new JLabel("Total Fine");
    JLabel cat = new JLabel("Category");
    JTable table = new JTable();
    JScrollPane tablescp = new JScrollPane(table);
    JTextField datef = new JTextField();
    JTextField totalborrf = new JTextField();
    JTextField mostborrf = new JTextField();
    JTextField totalfinef = new JTextField();
    JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Weekly", "Monthly", "Yearly"});
    JButton updatebtn = new JButton("Update Report");
    JButton viewbtn = new JButton("View Report");
    JButton backbtnReports = new JButton("Back");
    
   
    private LinkedListAccounts accounts;
    private UserService userService;

    final int maxWeek = 104; // equi to 2 years
    final int maxMonth = 60; // 3 years
    final int maxYear = 15; // 15 years

    //static String col[] = {"DATE", "MOST BORROWED BOOK", "TOTAL BORROWED BOOKS", "TOTAL FINE"};
    static DefaultTableModel reporttab = new DefaultTableModel(new String[]{"Transaction ID", "ISBN", "User ID", "Due Date", "Borrow Date", "Fine", "Status"}, 0);
    JScrollPane scpreports = new JScrollPane(table);

    private String date, mostBorrowed;
    private int totalBooksBorrowed;
    private double totalFine;
     static arryList list = new arryList();

    public adminReports(String date, String mostBorrowedBook, int totalBooksBorrowed, double totalFineCollected) {
        this.date = date;
        this.mostBorrowed = mostBorrowedBook;
        this.totalBooksBorrowed = totalBooksBorrowed;
        this.totalFine = totalFineCollected;
    }
      public JTable getTable() {
        return table;
    }
         private void AddedTable() {
        reporttab.setRowCount(0); // Clear existing rows

        for (NodeTransac transaction : list.getTransactionRecords()) {
            Object[] rowData = {
                transaction.getID(),
                transaction.code(),
                transaction.getUserId(),
                transaction.getDueDate(),
                transaction.borrowDate(),
                transaction.getPrice(),
                transaction.getstatus()
            };
            reporttab.addRow(rowData);
        }
    }

    public adminReports( LinkedListAccounts accounts, UserService userService ) {
        this.accounts = accounts;
        this.userService = userService;
        
        pan3.setBackground(new Color(0x6F1D1B));
        pan3.setLayout(null);
        pan3.setPreferredSize(new Dimension(800, 875));

        pan4.setBackground(new Color(0x99582A));
        pan4.setLayout(null);
        pan4.setBounds(850, 86, 700, 875);
        
        pan4_5.setLayout(null);
        pan4_5.setBackground(new Color(0xD9D9D9));
        pan4_5.setBounds(0, 0, 1530, 86);

        genrep.setBounds(70, 21, 560, 77);
        genrep.setFont(new Font("Bebas Neue", Font.BOLD, 64));
        genrep.setForeground(new Color(0xBB9457));

        table.setModel(reporttab);
        scpreports.setBounds(35, 145, 780, 490);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                datef.setText(reporttab.getValueAt(row, 0).toString());
                mostborrf.setText(reporttab.getValueAt(row, 1).toString());
                totalborrf.setText(reporttab.getValueAt(row, 2).toString());
                totalfinef.setText(reporttab.getValueAt(row, 3).toString());
            }
        });

        datelbl.setBounds(20, 228, 300, 30);
        datelbl.setFont(new Font("Cambria", Font.BOLD, 30));
        datelbl.setForeground(new Color(0xBB9457));

        mostborrlbl.setBounds(20, 278, 480, 30);
        mostborrlbl.setFont(new Font("Cambria", Font.BOLD, 30));
        mostborrlbl.setForeground(new Color(0xBB9457));

        totalborrlbl.setBounds(20, 328, 480, 30);
        totalborrlbl.setFont(new Font("Cambria", Font.BOLD, 30));
        totalborrlbl.setForeground(new Color(0xBB9457));

        totalfinelbl.setBounds(20, 378, 480, 30);
        totalfinelbl.setFont(new Font("Cambria", Font.BOLD, 30));
        totalfinelbl.setForeground(new Color(0xBB9457));

        cat.setBounds(20, 420, 480, 40);
        cat.setFont(new Font("Cambria", Font.BOLD, 30));
        cat.setForeground(new Color(0xBB9457));

        datef.setBounds(350, 228, 250, 30);
        datef.setFont(new Font("Cambria", Font.BOLD, 15));
        mostborrf.setBounds(350, 278, 250, 30);
        mostborrf.setFont(new Font("Cambria", Font.BOLD, 15));
        totalborrf.setBounds(350, 328, 250, 30);
        totalborrf.setFont(new Font("Cambria", Font.BOLD, 15));
        totalfinef.setBounds(350, 378, 250, 30);
        totalfinef.setFont(new Font("Cambria", Font.BOLD, 15));

        categoryBox.setBounds(350, 428, 250, 30);
        categoryBox.setFont(new Font("Cambria", Font.BOLD, 15));
        
        backbtnReports.setBounds(460, 690, 310, 88);
        backbtnReports.setBackground(new Color(0xD9D9D9));
        backbtnReports.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        backbtnReports.setForeground(new Color(0x6F1D1B));
        backbtnReports.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
       
               
                   dashBoard dashb = new dashBoard(userService,accounts);
                   dashb.setVisible(true);
                   
                   ((JFrame) SwingUtilities.getWindowAncestor(backbtnReports)).dispose();
          
            }
            
        }
        );
        
        updatebtn.setBounds(290, 480, 310, 88);
        updatebtn.setBackground(new Color(0xD9D9D9));
        updatebtn.setFont(new Font("Cambria", Font.BOLD, 30));
        updatebtn.setForeground(new Color(0x6F1D1B));
        updatebtn.addActionListener(e -> updateReport());

        viewbtn.setBounds(120, 690, 310, 88);
        viewbtn.setBackground(new Color(0xD9D9D9));
        viewbtn.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        viewbtn.setForeground(new Color(0x6F1D1B));
        viewbtn.addActionListener(e -> viewReport());
        
        //backbtnReports.setBounds

        pan3.add(pan4);
        pan3.add(pan4_5);
        pan3.add(scpreports);
        pan3.add(backbtnReports);
        pan3.add(viewbtn);
        
        pan4.add(genrep);
        pan4.add(datelbl);
        pan4.add(mostborrlbl);
        pan4.add(totalborrlbl);
        pan4.add(totalfinelbl);
        pan4.add(cat);
        pan4.add(datef);
        pan4.add(mostborrf);
        pan4.add(totalborrf);
        pan4.add(totalfinef);
        pan4.add(categoryBox);
        pan4.add(updatebtn);
        
         AddedTable();
         
        this.add(pan3);
        this.setSize(1537, 875);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Generate Reports");
          this.setIconImage(icon);
        this.setResizable(false);
    }

    private void updateReport()  { //enqueue
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String updatedDate = datef.getText();
            String updatedMostBorr = mostborrf.getText();
            int updatedTotalBorr = Integer.parseInt(totalborrf.getText());
            double updatedTotalFine = Double.parseDouble(totalfinef.getText());

            reporttab.setValueAt(updatedDate, selectedRow, 0);
            reporttab.setValueAt(updatedMostBorr, selectedRow, 1);
            reporttab.setValueAt(updatedTotalBorr, selectedRow, 2);
            reporttab.setValueAt(updatedTotalFine, selectedRow, 3);

            String category = (String) categoryBox.getSelectedItem();
            switch (category) {
                case "Weekly":
//                    weekly.get(selectedRow).date = updatedDate;
//                    weekly.get(selectedRow).mostBorrowed = updatedMostBorr;
//                    weekly.get(selectedRow).totalBooksBorrowed = updatedTotalBorr;
//                    weekly.get(selectedRow).totalFine = updatedTotalFine;
                    break;
                case "Monthly":
//                    monthly.get(selectedRow).date = updatedDate;
//                    monthly.get(selectedRow).mostBorrowed = updatedMostBorr;
//                    monthly.get(selectedRow).totalBooksBorrowed = updatedTotalBorr;
//                    monthly.get(selectedRow).totalFine = updatedTotalFine;
                    break;
                case "Yearly":
//                    yearly.get(selectedRow).date = updatedDate;
//                    yearly.get(selectedRow).mostBorrowed = updatedMostBorr;
//                    yearly.get(selectedRow).totalBooksBorrowed = updatedTotalBorr;
//                    yearly.get(selectedRow).totalFine = updatedTotalFine;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid category selected!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            JOptionPane.showMessageDialog(null, "Report updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter proper values!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewReport() { //dequeue
        reporttab.setRowCount(0);

        LinkedList<adminReports> selectedList;
        switch (categoryBox.getSelectedItem().toString()) {
            case "Weekly":
//                selectedList = weekly;
//                break;
            case "Monthly":
//                selectedList = monthly;
//                break;
            case "Yearly":
//                selectedList = yearly;
//                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid category!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

////        for (adminReports report : selectedList) {
////            reporttab.addRow(new Object[]{report.date, report.mostBorrowed, report.totalBooksBorrowed, report.totalFine});
////        }
//
//        JOptionPane.showMessageDialog(null, "Reports loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//    }
    }
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new adminReports().setVisible(true));
    }
    }
