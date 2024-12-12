/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;
import BACKENDLIB.BookBase;
import BACKENDLIB.arryList;
import DSA.LinkedlistBook;
import static DSA.LinkedlistBook.quickSortByTitle;
import DSA.NodeBook;
import DSA.NodeTransac;
import FRONTENDLIB.BORROWINGUI;

   //import static Project.VIEWLISTUI.*;
//import Project.arryList.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ivan
 */
public class ReturnUI extends parentComponent implements BookBase,ActionListener {
   private JLabel userLbl, isbnLbl, borrowLbl, dueDateLbl, fineAmountLbl, titleLbl;
    private JTextField userField, isbnField, transacIDField, fineAmountField;
    private JSpinner borrowDateSpinner, dueDateSpinner;
    private JButton returnButton, cancelButton, updateButton;
    private JPanel topPanel, sidePanel, mainPanel;
    private DefaultTableModel tableModel;
    private JTable transactionTable;
    private JScrollPane tableScrollPane;
    JPanel MainPanel = new JPanel(null);
    
    private Date borrowDate, dueDate,returnDate;
    
    public static RecordPayUI pay ;
    public static arryList transactionList;
    public static LinkedlistBook bookList;
   
  

    public ReturnUI(arryList transactionList,RecordPayUI pay, LinkedlistBook bookList) {
        this.transactionList = transactionList;
        this.bookList = bookList;
        this.pay = pay;
        initUI();
      
    }

    // Initialize the user interface
    public void initUI() {
        
          MainPanel.setSize(1280, 1049);
            MainPanel.setBounds(0, 0, 1280, 1049);
            MainPanel.setVisible(true);
            MainPanel.setLayout(null);

        // Panels
        initPanels();

        // Labels
        initLabels();

        // Text Fields
        initTextFields();

        // Buttons
        initButtons();

        // Table
        initTable();

        // Add components to panels
        addComponentsToPanels();

        // Make frame visible
        setVisible(true);
    }

    // Initialize panels
    private void initPanels() {
        topPanel = createPanel(0, 0, 1530, 86, new Color(0xD9D9D9));
        sidePanel = createPanel(718, 86, 758, 810, new Color(0x6F1D1B));
        mainPanel = createPanel(0, 86, 725, 840, new Color(0x99582A));
        
        setIconImage(icon);
       MainPanel. add(topPanel);
       MainPanel. add(sidePanel);
      MainPanel.  add(mainPanel);
        
    }

    // Create a panel with specified bounds and background color
    private JPanel createPanel(int x, int y, int width, int height, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(bgColor);
        panel.setBounds(x, y, width, height);
        return panel;
    }

    // Initialize labels
    private void initLabels() {
        titleLbl = createLabel("Returning", new Font("Bebas Neue", Font.BOLD, 70), Color.white, 349, 37, 500, 115);
        userLbl = createLabel("UserID", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0xBB9457), 83, 276, 250, 43);
        isbnLbl = createLabel("ISBN", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0xBB9457), 83, 136, 100, 45);
        borrowLbl = createLabel("Borrow Date", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0xBB9457), 83, 399, 250, 43);
        dueDateLbl = createLabel("Due Date", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0xBB9457), 510, 399, 250, 43);
        fineAmountLbl = createLabel("Fine Amount", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0xBB9457), 83, 523, 250, 43);
        
        topPanel.add(createLabel("Pangalan mo", new Font("Bebas Neue", Font.BOLD, 48), Color.black, 116, 20, 315, 60));
        sidePanel.add(titleLbl);
        sidePanel.add(userLbl);
        sidePanel.add(isbnLbl);
        sidePanel.add(borrowLbl);
        sidePanel.add(dueDateLbl);
        sidePanel.add(fineAmountLbl);
    }

    // Create a label with specified font, color, and bounds
    private JLabel createLabel(String text, Font font, Color color, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }

    // Initialize text fields
    private void initTextFields() {
        userField = createTextField(83, 323, 302, 76);
        isbnField = createTextField(83, 195, 613, 76);
        transacIDField = createTextField(407, 323, 289, 76);
        fineAmountField = createTextField(83, 566, 614, 76);
        
        fineAmountField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
        transacIDField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
       isbnField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
         userField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
                    
        sidePanel.add(userField);
        sidePanel.add(isbnField);
        sidePanel.add(transacIDField);
        sidePanel.add(fineAmountField);

        borrowDateSpinner = createDateSpinner(83, 439, 302, 76);
        dueDateSpinner = createDateSpinner(407, 439, 289, 76);
        
       dueDateSpinner.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
        borrowDateSpinner.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));

        sidePanel.add(borrowDateSpinner);
        sidePanel.add(dueDateSpinner);
    }

    // Create a text field with specified bounds
    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(Color.white);
        return textField;
    }

    // Create a date spinner with specified bounds
    private JSpinner createDateSpinner(int x, int y, int width, int height) {
        SpinnerDateModel model = new SpinnerDateModel();
    JSpinner dateSpinner = new JSpinner(model);
    
    // Set the size of the spinner
    dateSpinner.setBounds(x, y, width, height);

    // Format the date to dd/MM/yyyy
    JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
    dateSpinner.setEditor(editor);
        dateSpinner.setBounds(x, y, width, height);
        dateSpinner.setBackground(Color.white);
        return dateSpinner;
    }
    public void resetTable() {
    DefaultTableModel model = (DefaultTableModel) transactionTable.getModel();
    model.setRowCount(0); // Clears all rows
}

    // Initialize buttons
    private void initButtons() {
    // Button initialization
    returnButton = createButton("Return", 83, 660, 310, 88);
    cancelButton = createButton("Back", 1256, 15, 187, 56);
    updateButton = createButton("Update", 428, 660, 261, 88);

    // Add action listeners
    updateButton.addActionListener(new UpdateActionListener());
    cancelButton.addActionListener(new Clear());

    // Add buttons to panels
    sidePanel.add(returnButton);
    sidePanel.add(updateButton);
    topPanel.add(cancelButton);
    

    // Add ActionListener to returnButton
    returnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int row = transactionTable.getSelectedRow();

        // Check if a row is selected
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to return!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate inputs
        if (userField.getText().isEmpty() || isbnField.getText().isEmpty() || 
            transacIDField.getText().isEmpty() || fineAmountField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Parse inputs
            String userID = userField.getText();
            String transactionID = transacIDField.getText();
            String fineAmountText = fineAmountField.getText();
            Date borrowDate = (Date) borrowDateSpinner.getValue();
            Date dueDate = (Date) dueDateSpinner.getValue();

            // Parse fineAmountText and isbn safely
            double overdue = Double.parseDouble(fineAmountText);
            int cd = Integer.parseInt(isbnField.getText());

            // Format dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String borrowDateStr = dateFormat.format(borrowDate);
            String dueDateStr = dateFormat.format(dueDate);
            
             int index = arryList.TransacId.indexOf(transactionID);
             if (index == -1) {
    JOptionPane.showMessageDialog(null, "Transaction ID not found!");
    return; // Exit the method to prevent further execution
}
             
             System.out.println(index);
            // Set status
            String status = "Returned";

            // Perform action
            JOptionPane.showMessageDialog(null, "Redirecting to record...");
            
            // Call add method from RecordPayUI instance
            
          //create new isntancec of archived for recordpyment
           DefaultTableModel tb = (DefaultTableModel) pay.table.getModel();
           pay.updateTransactionStatus(index,status,overdue,tb);
            
            // Remove selected row from the table
            bookList.updateAvailability(cd, true);
            //pay.updateTransactionStatus(index, status, overdue);
            if (index >= 0) {
                
            arryList.updateTransaction(index, "Returned", overdue);

            arryList.removeTransaction(index);
            tableModel.removeRow(row);

            JOptionPane.showMessageDialog(null, "Book return recorded and row deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Transaction not found in the backend data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
            
           
            

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input in numeric fields!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    });
}
                 

      
      
     
      
        
        
   
    
   

    // Create a button with specified text and bounds
    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Bebas Neue", Font.BOLD, 48));
        button.setForeground(new Color(0x6F1D1B));
        button.setBackground(Color.white);
        button.setBounds(x, y, width, height);
        return button;
    }

    // Initialize the table
    private void initTable() {
        
        tableModel = new DefaultTableModel(new String[]{"UserID", "ISBN", "Transaction ID", "Due date", "Borrow Date"}, 0);
        transactionTable = new JTable(tableModel);
       
        transactionTable.addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                 table();
            }
           
        });
      

        tableScrollPane = new JScrollPane(transactionTable);
        tableScrollPane.setBounds(40, 49, 646, 700);
        mainPanel.add(tableScrollPane);

        populateTable();
        
         
    }

    // Populate the table with transaction data
    private void populateTable() {
        tableModel.setRowCount(0); // Clear existing rows

        for (NodeTransac transaction : arryList.getTransactions()) {
            Object[] rowData = {
                transaction.getID(),
                transaction.code(),
                transaction.getUserId(),
                transaction.getDueDate(),
                transaction.borrowDate(),
                transaction.getPrice(),
                transaction.getstatus()
                
                    
            };
            tableModel.addRow(rowData);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     private class Clear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            resetTable();
           
    }
     }

    // Action listener for update button
    private class UpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = transactionTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row to update.");
                return;
            }

            // Validate inputs
            String userId = userField.getText().trim();
            int isbnCode = parseISBN(isbnField.getText().trim());
            String transactionId = transacIDField.getText().trim();
          Date  borrowDate = (Date) borrowDateSpinner.getValue();
           Date dueDate = (Date) dueDateSpinner.getValue();

            if (userId.isEmpty() || transactionId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields.");
                return;
            }

            // Update transaction
             returnDate = new Date();
             String genre =bookList.getGenre(isbnCode);
            double fine = caculateFine(genre,borrowDate,dueDate,returnDate);
            
            NodeTransac updatedTransaction = new NodeTransac(userId,isbnCode, transactionId, borrowDate, dueDate);
            arryList.updateTransaction(row, updatedTransaction);

            // Refresh table
            populateTable();
        }
    }

    // Parse ISBN from string input
    private int parseISBN(String isbn) {
        try {
            return Integer.parseInt(isbn);
        } catch (NumberFormatException e) {
            return -1; // Invalid ISBN
        }
    }

    // Mouse listener for the table
    public void table(){
      
            int row = transactionTable.getSelectedRow();
             if (row != -1) {
              userField.setText(tableModel.getValueAt(row, 0).toString());
        isbnField.setText(tableModel.getValueAt(row, 1).toString());
        transacIDField.setText(tableModel.getValueAt(row, 2).toString());
 SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
             try {
        // Retrieve the Date objects directly from the table (assuming columns 3 and 4 store Date objects)
        Object borrowDateObj = tableModel.getValueAt(row, 3); // Borrow Date column
        Object dueDateObj = tableModel.getValueAt(row, 4);   // Due Date column

        if (borrowDateObj instanceof Date && dueDateObj instanceof Date) {
            Date borrowDate = (Date) borrowDateObj;
            Date dueDate = (Date) dueDateObj;

            // Print the dates for debugging
            System.out.println("Borrow Date: " + borrowDate);
            System.out.println("Due Date: " + dueDate);

            // Set the spinners to the Date objects
            borrowDateSpinner.setValue(borrowDate);
            dueDateSpinner.setValue(dueDate);

            // Get ISBN from the text field
            String getISBN = isbnField.getText();
            try {
                int ISBN = Integer.parseInt(getISBN);  // Convert ISBN from string to integer
                String genre = bookList.getGenre(ISBN);  // Get the genre based on the ISBN
                
                // Print the genre for debugging
                System.out.println("Genre: " + genre);
                
                if (genre != null) {
                    // Calculate the fine using the retrieved genre
                     returnDate = new Date();
                     System.out.println(returnDate);// Assuming the current date as the return date
                    double fine = caculateFine(genre, borrowDate, dueDate, returnDate);
                    
                    // Print the fine for debugging
                    System.out.println("Calculated fine: " + fine);
                    
                    fineAmountField.setText(String.format("%.2f", fine)); // Display the fine amount
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found for the given ISBN", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException b) {
                JOptionPane.showMessageDialog(null, "Invalid ISBN format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Error: Retrieved values are not Date objects.");
        }
    } catch (Exception c) {
        System.out.println("Error retrieving date: " + c.getMessage());
    }
            return null;
        }

        @Override
        protected void done() {
            // Any final UI updates after background task is complete
        }
    };

    worker.execute();  // Start the background task
}
    }
    
    private void addComponentsToPanels() {
    // Add components to the top panel (if needed)
    topPanel.add(cancelButton); // Button for closing the window

    // Add components to the side panel
    sidePanel.add(titleLbl);
    sidePanel.add(userLbl);
    sidePanel.add(isbnLbl);
    sidePanel.add(borrowLbl);
    sidePanel.add(dueDateLbl);
    sidePanel.add(fineAmountLbl);
    sidePanel.add(userField);
    sidePanel.add(isbnField);
    sidePanel.add(transacIDField);
    sidePanel.add(fineAmountField);
    sidePanel.add(borrowDateSpinner);
    sidePanel.add(dueDateSpinner);
    sidePanel.add(returnButton);
    sidePanel.add(updateButton);

    // Add components to the main panel
    mainPanel.add(tableScrollPane); // Add the table to the main panel
}
    
    private void Confirm(String id, int code,String userID , Date dueDate, Date brrwDate, Double amount, String Status){
        JOptionPane.showMessageDialog(null, "The book has been added in recordPayment");
         arryList.addTransaction(id, code, userID, brrwDate , dueDate, amount,Status);
          bookList.updateAvailability(code, false);
     //    pay.addRecord(id,code,userID,brrwDate,dueDate,amount,Status);
        JOptionPane.showMessageDialog(null, "Book has been returned!", id, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateTable() {
        Object[][] bookData = bookList.getBookData();

        // Clear any existing rows in the table
        tableModel.setRowCount(0);

        // Add each book's data to the table
        for (Object[] book : bookData) {
            tableModel.addRow(book);
        }
    }
    private void processRecordPayment() {
    // Get user inputs
    String userID = userField.getText();
    String isbnText = isbnField.getText();
    String transactionID = transacIDField.getText();
    Date borrowDate = (Date) borrowDateSpinner.getValue();
    Date dueDate = (Date) dueDateSpinner.getValue();
    Date returnDates = returnDate; // Assume you have a returnDate field in the UI

    try {
        int isbn = Integer.parseInt(isbnText);

        // Search for the book in the linked list
        NodeBook book = LinkedlistBook.head;
        while (book != null) {
            if (book.getISBN() == isbn) {
                String genre = book.getGenre();
                double fine = caculateFine(genre, borrowDate, dueDate, returnDates);

                // Add record to the Record Payment table
                updateRecordPaymentTable(transactionID, isbn, userID, borrowDate, dueDate, returnDates, fine);
                return;
            }
            book = book.getNext();
        }

        JOptionPane.showMessageDialog(null, "Book with ISBN " + isbn + " not found", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid ISBN input", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateRecordPaymentTable(String transactionID, int isbn, String userID, Date borrowDate, Date dueDate, Date returnDate, double fine) {
    // Assuming `recordPaymentTable` is your JTable for Record Payment
//    DefaultTableModel model = (DefaultTableModel) RecordPayUI.table.getModel();
//
//    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
//    model.addRow(new Object[]{
//        transactionID,
//        isbn,
//        userID,
//        dateFormat.format(borrowDate),
//        dateFormat.format(dueDate),
//        dateFormat.format(returnDate),
//        fine == 0.0 ? "Paid" : "Overdue: " + fine
//   });
}


    public double  caculateFine(String genre, Date brrwDate, Date DueDate, Date returnDate){
        System.out.println("Borrow Date: " + brrwDate);
System.out.println("Due Date: " + DueDate);
System.out.println("Return Date: " + returnDate);
    long diffInMiles = returnDate.getTime() - DueDate.getTime();
    
    long overDue = TimeUnit.MILLISECONDS.toDays(diffInMiles);
    System.out.println("overdue: " + overDue);
    
  if (overDue <= 0) {
        return 0.0;
    }

    // Fine rates based on genre
    double fineFee = 0.0;

    if (genre.equals("Fiction")) {
        fineFee = 5.0;
          System.out.println("genre: Fiction");
    } else if (genre.equals("Non-Fiction")) {
        fineFee = 3.0;
          System.out.println("genre: non ficrtion");
    } else if (genre.equals("Education")) {
        fineFee = 8.0;
         System.out.println("genre: education");
    } else {
        JOptionPane.showMessageDialog(null, "Invalid genre", "Error", JOptionPane.ERROR_MESSAGE);
        return 0.0; // Return 0 if the genre is invalid
    }

    // Return the total fine
    return overDue * fineFee;

}


   public static void main(String [] args){
       arryList list = new arryList();
       RecordPayUI nice ;
   //    new ReturnUI(nice);
   }
}

