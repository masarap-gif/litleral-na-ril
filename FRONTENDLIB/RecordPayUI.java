/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;

import BACKENDLIB.BookBase;
import BACKENDLIB.arryList;
import BACKENDLIB.transac;
import DSA.LinkedlistBook;
import DSA.NodeTransac;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivan
 */
public class RecordPayUI extends parentComponent implements BookBase  {
    JPanel panel = new JPanel();
    JPanel pnl2 = new JPanel();
    JLabel a;
    JLabel title = new JLabel("Record Payment");
      DefaultTableModel tb;
      JTable table;
    JScrollPane sp;
     static arryList list = new arryList();
     public static LinkedlistBook bookList;
    private JComboBox<String> option;

    public RecordPayUI(arryList list,LinkedlistBook bookList) {
         tb = new DefaultTableModel(
            new String[]{"User ID", "ISBN", "Transaction ID", "Due Date", "Borrow Date", "Fine", "Status"},
            0
        );
        table = new JTable(tb);
        sp = new JScrollPane(table);
        this.list = list;
        this.bookList = bookList;
      
//        addRecord("1001", 101, "1", currentDate, returnDate, 350, "Paid");
//            addRecord("1001", 101, "1", currentDate, returnDate, 350, "Paid");
//               addRecord("1001", 101, "1", currentDate, returnDate, 350, "Paid");
//                  addRecord("1001", 101, "1", currentDate, returnDate, 350, "Paid");
      
    }

    public void design() {
        this.setSize(1476, 896);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setIconImage(icon);
        this.setTitle("Record Payment");
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date returnDate = calendar.getTime();
        // Initialize the table with headers
       

        panel.setBounds(0, 86, 1483, 810);
        panel.setBackground(new Color(0x6F1D1B));
        panel.setLayout(null);

        pnl2.setLayout(null);
        pnl2.setBackground(new Color(0xD9D9D9));
        pnl2.setBounds(0, 0, 1530, 86);

        a = new JLabel("Pangalan mo");
        a.setFont(new Font("Bebas Neue", Font.BOLD, 48));
        a.setBounds(116, 20, 315, 60);
        pnl2.add(a);

        title.setBounds(61, 60, 500, 77);
        title.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 54));
        title.setForeground(Color.white);

        sp.setBounds(61, 150, 1340, 618);

        option = new JComboBox<>(new String[]{"Option1", "Option2"}); // Replace with real options
        option.setBounds(1161, 70, 233, 56);
        option.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 16));
        option.setBackground(new Color(0x99582A));
        option.setForeground(Color.white);

        panel.add(title);
        panel.add(sp);
        panel.add(option);

        this.add(panel);
        this.add(pnl2);

        // Populate with default values
       //populateTable();
        AddedTable();

        this.setVisible(true);
    }

    private void populateTable() {
//        tb.setRowCount(0); // Clear table
//        Object[][] defaultData = DefaultValues();
//        for (Object[] row : defaultData) {
//            tb.addRow(row);
        }
    
   private void AddedTable() {
        tb.setRowCount(0); // Clear existing rows

        for (NodeTransac transaction : list.getTransactionRecords()) {
            Object[] rowData = {
                transaction.getUserId(),
                transaction.code(),
                transaction.getID(),
                transaction.getDueDate(),
                transaction.borrowDate(),
                transaction.getPrice(),
                transaction.getstatus()
            };
            tb.addRow(rowData);
        }
    }
public void updateTransactionStatus(int index, String status, double fine, DefaultTableModel tb) {
    System.out.println("Row count: " + tb.getRowCount());
        if (index >= 0 && index < arryList.getTransactionRecords().size()) {
            // Get the existing transaction
            NodeTransac transaction = arryList.getTransactionRecords().get(index);

            // Update the status and fine
            transaction.setstatus(status);
            transaction.setPrice(fine);
            
             if (index >= 0 && index < tb.getRowCount()) {
    tb.setValueAt(status, index, 6); // Update Status column
    tb.setValueAt(fine, index, 5); // Update Fine column
} else {
    System.out.println("Invalid index: " + index);
}

            // Update the transaction in the list
           arryList. getTransactions().set(index, transaction);
            } else {
            System.out.println("Index out of bounds or transaction not found");
        }
    }

    
    public static void main(String[] args) {
        new RecordPayUI(list, bookList);
    }
    
}
 
    

   




