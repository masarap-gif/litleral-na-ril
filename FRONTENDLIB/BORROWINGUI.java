/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;
import BACKENDLIB.BookBase;
import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import BACKENDUSER.NodeHistory;
import BACKENDUSER.TranascHisotry;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import static DSA.LinkedlistBook.quickSortByTitle;
 //import static DSA.LinkedlistBook.quickSort;
import DSA.NodeBook;
import DSA.NodeTransac;
import static FRONTENDLIB.VIEWLISTUI.bookList;
import FRONTENDUSER.BOOKHISTORYUI;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
//import static FRONTEND.VIEWLISTUI.bookList;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author ivan
 */
public class BORROWINGUI extends parentComponent {
 

    private JComboBox<String> genreComboBox;
    private JPanel bookPanel;
    private Map<String, List<String>> booksByGenre;
    private JLabel a, lb1, lb2, lb3, lb4, b;
    private JTextField isbn;
    private JButton prc, cnl, bbrw, search;
    private JPanel pnl1, pnl2, pnl3, pnl4;
    private JTextField userId, date, days, transac, title, code, authors, avails, genre;
    JPanel  panel2, panel3, panel4, panel5;
    JPanel Mainpanel = new JPanel();
    private JSpinner dateSpinnerBorrow;
    private JSpinner dateSpinnerDue;
    private JScrollPane pane;
    private JScrollPane sp;
    public static LinkedlistBook book;
    public LinkedListAccounts acc;
    private static VIEWLISTUI viewListUI;
    public LLhistory hh;
   public static arryList transacs;
    private DefaultTableModel tableModel;
    private JTable table;
    public NodeBook books;
    public UserService users;
    public TranascHisotry his;
    private User user;
    private static RecordPayUI list;
   JPanel panel = new JPanel();
    
    List<NodeBook> selectedBooks = new ArrayList<>();
    ImageIcon searchIMG = new ImageIcon("images\\search.png");
       String[] columnNames = {"Title", "Author", "ISBN", "Genre", "Availability"};

    public BORROWINGUI(LinkedlistBook list,RecordPayUI ist,LinkedListAccounts acc, User user,LLhistory hh) {
//        try {
            
            BORROWINGUI.book = list;
           BORROWINGUI.list =ist;
           this.acc = acc;
           this.user = user;
           users = UserService.getInstance(); 
           this.his = new TranascHisotry(user);
           this.hh = hh;
          
          
           
            tableModel = new DefaultTableModel(columnNames, 0);
            table = new JTable(tableModel);
        

            // Initialize main panels and components
            pnl2 = new JPanel();
            pnl2.setLayout(null);
            pnl2.setBackground(new Color(0xD9D9D9));
            pnl2.setBounds(0, 0, 1530, 86);

            a = new JLabel("Pangalan mo");
            a.setFont(new Font("Bebas Neue", Font.BOLD, 48));
            a.setBounds(116, 20, 315, 60);

            lb1 = new JLabel("ISBN");
            lb1.setFont(new Font("Bebas Neue", Font.BOLD, 36));
            lb1.setBounds(405, 100, 100, 43);
            lb1.setForeground(new Color(0x99582A));

            Image img = searchIMG.getImage();  // Transform it 
            Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
            searchIMG = new ImageIcon(newImg);

            search = new JButton();
            search.setBounds(266, 11, 81, 53);
            search.setBackground(new Color(0xBB9457));
            search.setBorderPainted(false);
            search.setOpaque(true);
            search.setContentAreaFilled(true);
            search.setIcon(searchIMG);
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int searchISBN = Integer.parseInt(isbn.getText());  // ISBN entered by user
                        NodeBook foundBook = bookList.LinearSeach(searchISBN);  // Call search method

                        if (foundBook != null) {
                            // Book found, display its details in pnl3
                            

                            title.setText(foundBook.getTitle());
                            code.setText(String.valueOf(foundBook.getISBN()));
                            authors.setText(foundBook.getAuthor());
                            genre.setText(foundBook.getGenre());
                            avails.setText(String.valueOf(foundBook.getIsAvailable()));
                            // Exit loop as book is found
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ISBN Does not exist");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid ISBN number.");
                    }
                }
            });

            isbn = new JTextField();
            isbn.setBounds(405, 144, 356, 76);
            isbn.setBackground(new Color(0xBB9457));
            isbn.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            isbn.setForeground(Color.white);
            isbn.setLayout(null);
            isbn.add(search);

            lb2 = new JLabel("Genre");
            lb2.setFont(new Font("Bebas Neue", Font.BOLD, 36));
            lb2.setBounds(33, 100, 200, 43);
            lb2.setForeground(new Color(0xBB9457));

            genreComboBox = new JComboBox<>(BookBase.genre);
            genreComboBox.addItem("Select Genre");
            genreComboBox.setSelectedIndex(4);
            genreComboBox.setBounds(33, 144, 333, 76);
            genreComboBox.setPreferredSize(new Dimension(333, 76));
            genreComboBox.setBackground(new Color(0x99582A));
            genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            genreComboBox.setForeground(Color.white);

           // quickSort(BookBase.genre, 0, BookBase.genre.length - 1);
            genreComboBox.addActionListener((ActionEvent e) -> {
                 DefaultTableModel model = (DefaultTableModel) tableModel;
        model.setRowCount(0); 
         boolean found = false;
                 String selectedGenre = (String) genreComboBox.getSelectedItem();
             if (!selectedGenre.equals("All")) {
            Object[][] getBookData = bookList.getBookData(); // Get the book data

            // Loop the books t check the genre
            for (Object[] book : getBookData) {
                String bookGenre = (String) book[3];
                if (bookGenre.equalsIgnoreCase(selectedGenre)) {
                    String availability = (boolean) book[4] ? "Available" : "Checked Out";
                    model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
                    found = true;
                }
            }

            // If no books were found for slected gen
            if (!found) {
                JOptionPane.showMessageDialog(this, "No books found in the genre: " + selectedGenre, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //display sorted books "All"
            updateTableWithSortedBooks(tableModel); 
        }  // Assuming populateTable can accept the sorted array
});

            // Book panel for displaying books of the selected genre
            bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
            bookPanel.setBounds(33, 250, 728, 300);
            bookPanel.setBackground(new Color(0x6F1D1B));

            pnl1 = new JPanel();
            pnl1.setLayout(null);
            pnl1.setBackground(new Color(0x6F1D1B));
            pnl1.setBounds(-7, 86, 1537, 875);

            pnl3 = new JPanel();
            pnl3.setLayout(null);
            pnl3.setBounds(836, 86, 640, 840);
            pnl3.setBackground(new Color(0x99582A));

            prc = new JButton("Proceed");
            prc.setBounds(896, 750, 250, 88);
            prc.setFont(new Font("Bebas Neue", Font.BOLD, 50));
            prc.setForeground(new Color(0x6F1D1B));
            prc.setBackground(Color.white);
            prc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(prc)) {
                        Confirm();
                    }
                   // updateBookDisplay(selectedGenre);
                }
            });

            cnl = new JButton("Cancel");
            cnl.setBounds(1200, 750, 226, 88);
            cnl.setFont(new Font("Bebas Neue", Font.BOLD, 50));
            cnl.setForeground(new Color(0x6F1D1B));
            cnl.setBackground(Color.white);
            cnl.addActionListener(e -> {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(cnl);
                if (currentFrame != null) {
                    currentFrame.dispose();
                }
            });
             code = new JTextField();
         code.setBounds(68, 294, 392, 61);
         code.setBackground(new Color(0x99582A));
         code.setForeground(Color.white);
            code.setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
         code.setBorder(null);
         
           title = new JTextField();
         title.setBounds(68, 67, 392, 61);
         title.setBackground(new Color(0x99582A));
         title.setForeground(Color.white);
         title.setFont(new Font("Plus Jakarta Sans",Font.ITALIC,28));
          title.setBorder(null);
         
           authors = new JTextField();
         authors.setBounds(68, 176, 392, 61);
         authors.setBackground(new Color(0x99582A));
         authors.setForeground(Color.white);
         authors.setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
          authors.setBorder(null);
         
          genre = new JTextField();
         genre.setBounds(68, 398 , 213, 61);
         genre.setBackground(new Color(0x99582A));
         genre.setForeground(Color.white);
         genre  .setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
          genre.setBorder(null);
         
         avails = new JTextField();
         avails.setBounds(294, 398, 166, 61);
         avails.setBackground(new Color(0x99582A));
         avails.setForeground(Color.white);
         avails  .setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
          avails.setBorder(null);
         
          JLabel  codelbl = new JLabel("ISBN");
          codelbl.setFont(new Font("Bebas Neue", Font.BOLD,36));
          codelbl.setBounds(68, 250, 200, 54);
          codelbl.setForeground(new Color(0x6F1D1B));
          
           JLabel  titlelbl = new JLabel("Title");
          titlelbl.setFont(new Font("Bebas Neue", Font.BOLD,36));
          titlelbl.setBounds(68, 22, 200, 54);
          titlelbl.setForeground(new Color(0x6F1D1B));
          
            JLabel  authorlbl = new JLabel("Author");
          authorlbl.setFont(new Font("Bebas Neue", Font.BOLD,36));
          authorlbl.setBounds(68, 130, 200, 54);
          authorlbl.setForeground(new Color(0x6F1D1B));
          
              JLabel  genrelbl = new JLabel("Genre");
          genrelbl.setFont(new Font("Bebas Neue", Font.BOLD,36));
          genrelbl.setBounds(68, 355, 200, 54);
          genrelbl.setForeground(new Color(0x6F1D1B));   
          
             JLabel  avail = new JLabel("Availability");
          avail.setFont(new Font("Bebas Neue", Font.BOLD,30));
          avail.setBounds(305, 355, 200, 54);
          avail.setForeground(new Color(0x6F1D1B));   
          
          
          
        
         
         table.addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent evt) {
                                Table();
                            }
                            });
         
     
        pnl4 = new JPanel();
        pnl4.setLayout(null);
        pnl4.setBounds(896, 221, 528, 513);
        pnl4.setBackground(new Color(0xBB9457));
        
       
          
                 pnl4.add(code);
        pnl4.add(authors);
        pnl4.add(title);
         pnl4.add(genre);
          pnl4.add(avails);
          pnl4.add(codelbl);
           pnl4.add(authorlbl);
            pnl4.add(titlelbl);
             pnl4.add(genrelbl);
              pnl4.add(avail);

            // More component initializations here...

            sp = new JScrollPane(table);
            sp.setBounds(33, 240, 728, 588);
            updateTable();
             table.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Extract ISBN as an integer (make sure to parse it correctly)
            String isbnString = tableModel.getValueAt(selectedRow, 2).toString();
            int isbn = Integer.parseInt(isbnString);
                 books = list.getBookByIsbn(isbn);// Convert to int if needed
            
            // Find the book using ISBN
            NodeBook selectedBook = bookList.LinearSeach(isbn);
            
            if (selectedBook != null) {
                selectedBooks.add(selectedBook);  // Add to the list of selected books
                updateSelectedBooksPanel();  // Refresh the UI to show selected books
            } else {
                // Handle book not found, if necessary
            }
        }
    }
});
                
                b = new JLabel("Borrowing");
        b.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        b.setBounds(240, 0, 826, 100);
        b.setForeground(new Color(0xBB9457));
            // Add panels to the JFrame
            
          
            pnl2.add(a);
            pnl1.add(lb1);
            pnl1.add(lb2);
            pnl1.add(isbn);
           //pnl1.add(search);
            pnl1.add(sp);
            pnl1.add(genreComboBox);
            pnl1.add(bookPanel);
            pnl3.add(b);
            Mainpanel.add(cnl);
            Mainpanel.add(prc);
            Mainpanel.add(pnl4);
            Mainpanel.add(pnl3);
            Mainpanel.add(pnl2);
            Mainpanel.add(pnl1);
            

            // JFrame settings
            Mainpanel.setSize(1280, 1049);
            Mainpanel.setBounds(0, 0, 1280, 1049);
            Mainpanel.setVisible(true);
            Mainpanel.setLayout(null);


//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "An error occurred" + e.getMessage());
//            System.out.println(e.getStackTrace());
//          
//        }
    }
    

     private void populateTable(String genreFilter) {
    // Clear the table
    tableModel.setRowCount(0);
    
    
        
    // Populate based on genre filter
    NodeBook current = book.head;
    while (current != null) {
        // Apply genre filter
        if (genreFilter.equals("All") || current.getGenre().equalsIgnoreCase(genreFilter)) {
            tableModel.addRow(new Object[]{
                    current.getTitle(),
                    current.getAuthor(),
                    current.getISBN(),
                    current.getGenre(),
                    current.getIsAvailable()
                    
            });
            
        }
        current = current.next;
    }
     }

    private void Confirm() {
    try {
        JFrame frm = new JFrame();
        JLabel label = new JLabel("Student ID");
        JLabel label1 = new JLabel("Borrow Date(DD/MM/YY)");
        JLabel label2 = new JLabel("Period(Days)");
        JLabel label3 = new JLabel("Transaction ID");

        label.setBounds(127, 107, 400, 43);
        label1.setBounds(126, 230, 400, 43);
        label2.setBounds(590, 230, 400, 43);
        label3.setBounds(127, 350, 400, 43);

        label.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        label1.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        label2.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        label3.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));

        label.setForeground(new Color(0x6F1D1B));
        label1.setForeground(new Color(0x6F1D1B));
        label2.setForeground(new Color(0x6F1D1B));
        label3.setForeground(new Color(0x6F1D1B));

        dateSpinnerBorrow = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorBorrow = new JSpinner.DateEditor(dateSpinnerBorrow, "dd/MM/yyyy");
        dateSpinnerBorrow.setEditor(editorBorrow);
        dateSpinnerBorrow.setBounds(127, 274, 302, 76);
        dateSpinnerBorrow.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        dateSpinnerBorrow.setForeground(new Color(0x6F1D1B));
        dateSpinnerBorrow.setBackground(new Color(0xD9D9D9));

        // Due Date Picker using JSpinner
        dateSpinnerDue = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorDue = new JSpinner.DateEditor(dateSpinnerDue, "dd/MM/yyyy");
        dateSpinnerDue.setEditor(editorDue);
        dateSpinnerDue.setBounds(458, 274, 281, 76);
        dateSpinnerDue.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        dateSpinnerDue.setForeground(new Color(0x6F1D1B));
        dateSpinnerDue.setBackground(new Color(0xD9D9D9));

        userId = new JTextField();
        userId.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));
        transac = new JTextField();
        transac.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));

        userId.setBounds(127, 154, 613, 76);
        transac.setBounds(126, 397, 613, 76);

        userId.setBackground(new Color(0xD9D9D9));
        transac.setBackground(new Color(0xD9D9D9));

        bbrw = new JButton();

        bbrw.setBounds(279, 524, 310, 88);
        bbrw.setBackground(new Color(0xD9D9D9));
        bbrw.setText("Confirm");
        bbrw.setForeground(new Color(0x6F1D1B));
        bbrw.setFont(new Font("Bebas Neue", Font.BOLD, 64));
        bbrw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                  
                   // String(tableModel.getValueAt(row, 4).toString())
                    
                    String userid = userId.getText().trim();
                    int userID = Integer.parseInt(userid);
                    String transacs = transac.getText().trim();

                    if (userid.isEmpty() || transacs.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all required fields.");
                        return;
                    }

                    int row = table.getSelectedRow();
                       String title = (tableModel.getValueAt(row, 0).toString());
                    String author = (tableModel.getValueAt(row, 1).toString());
                    
                   // String title=(tableModel.getValueAt(row, 1);
                   String genre = (tableModel.getValueAt(row, 3).toString());
                  
                    
                    if (row == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a row from the table.");
                        return;
                    }
                   

                    // Get the value of column 4 (availability) and check its type
                    Object value = table.getValueAt(row, 4); // Assuming column 4 contains availability (boolean or string)

                    // Default availability is false
                    boolean isAvailable = false;
                   

                    if (value instanceof String) {
                        // If it's a String, parse it as a boolean ("true" becomes true, "false" becomes false)
                        isAvailable = Boolean.parseBoolean((String) value);
                    } else if (value instanceof Boolean) {
                        // If it's already a Boolean, cast it directly
                        isAvailable = (Boolean) value;
                    } else {
                        // Handle unexpected data types (optional, just to be safe)
                        JOptionPane.showMessageDialog(null, "Unexpected value in the availability column.");
                        return;
                    }

                    // Now check if the book is available
                    if (!isAvailable) {
                        JOptionPane.showMessageDialog(null, "This book is not available for borrowing.");
                        return;
                    }

                    // Fetch the book ISBN and dates
                    int isbn = (int) table.getValueAt(row, 2);
                    Date borrowDate = (Date) dateSpinnerBorrow.getValue();
                    Date dueDate = (Date) dateSpinnerDue.getValue();

                    // Clear the time portion of dates
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                    String borrowDateStr = dateFormat.format(borrowDate);
                    String dueDateStr = dateFormat.format(dueDate);

                    System.out.println(borrowDateStr);
                    System.out.println(dueDateStr);

                    if (borrowDate.after(dueDate)) {
                        JOptionPane.showMessageDialog(null, "Due date must be after the borrow date.");
                        return;
                    }

                    if (arryList.TransacId.contains(transacs)) {
                        JOptionPane.showMessageDialog(null, "Transaction ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                     
                         //NodeHistory newNodeHistory = new NodeHistory(title,author,isbn,genre,borrowDate,dueDate,0.0);
             
                      
              User user = users.getUserByID(userID);
            if (user != null) {
               
                // Create a new NodeHistory object with the data from the table
                NodeHistory newHistory = new NodeHistory(
                    title,  // Use the title variable (from the table row)
                    author,  // Use the author variable (from the table row)
                    user.getUserID(),
                    genre,
                    borrowDate,
                    dueDate,
                    0.0
                );

                // Add the new history entry to the user's history
                hh.addNode(newHistory);
                user.getHistory().addNode(newHistory);

                // Optionally, update the table immediately after borrowing a book
                updateTable();  // This will refresh the table with the new data

                // Debugging: Print confirmation
                System.out.println("Adding book to history: " + title + ", " + author);
                System.out.println("Book successfully borrowed by user: " + user.getUserID());
                System.out.println("Book successfully borrowed by user: " + user.getUsername());
                   

                    // Add transaction
                    NodeTransac newNode = new NodeTransac(transacs, isbn, userid, dueDate, borrowDate, 0.0, "Borrowed");
                    arryList.addTransaction(transacs, isbn, userid, borrowDate, dueDate);
                    arryList.archiveTransaction(newNode);

                    tableModel.addRow(new Object[]{transacs, isbn, userid, borrowDate, dueDate, 0.0, "Borrowed"});

                    // Format the date columns for proper display
                    table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
                        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

                        @Override
                        protected void setValue(Object value) {
                            if (value instanceof Date) {
                                setText(dateFormat.format((Date) value));
                            } else {
                                super.setValue(value);
                            }
                        }
                    });

                    table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
                        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

                        @Override
                        protected void setValue(Object value) {
                            if (value instanceof Date) {
                                setText(dateFormat.format((Date) value));
                            } else {
                                super.setValue(value);
                            }
                        }
                    });

                    // Update book availability
                    book.updateAvailability(isbn, false);
                    updateTable();

                    // Reflect changes in the UI
                    tableModel.setValueAt(false, row, 4); // Set availability to false
                    avails.setText("false");
                    JOptionPane.showMessageDialog(null, "Book has been borrowed!");

                    // Debugging Output
                    System.out.println("ISBNs: " + arryList.ISBN);
                    System.out.println("Transaction IDs: " + arryList.TransacId);
                    System.out.println("Borrow Dates: " + arryList.BrrwDate);
                    System.out.println("Due Dates: " + arryList.DueDate);

                    frm.dispose();
                } else {
   JOptionPane.showMessageDialog(null, "The user id does not exist", "message", JOptionPane.ERROR_MESSAGE);
}
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
                } 
      
        });

        frm.add(label);
        frm.add(label1);
        frm.add(label2);
        frm.add(label3);

        frm.add(userId);
        frm.add(transac);

        frm.add(dateSpinnerBorrow); // Add the Borrow Date spinner
        frm.add(dateSpinnerDue);

        frm.add(bbrw);
        frm.setResizable(false);
        frm.setLayout(null);
        frm.setSize(868, 689);
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
        frm.setTitle("Confirm transaction");
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred");
    }
}
//    public void updateUserHistoryTable(User user) {
//          LLhistory userHistory = user.getHistory();
//    // Fetch the user's history
//  Object[][] historyData = userHistory.getHistoryData();  // Get data from user's history
//
//        // Update the table model with the new data
//        DefaultTableModel model = (DefaultTableModel) nook.table.getModel();
//        model.setRowCount(0);  // Clear the table
//        for (Object[] row : historyData) {
//            model.addRow(row);  // Add new rows
//        }
//
//        // Optional: refresh/revalidate the table
////        historyTable.revalidate();
////        historyTable.repaint();
//}


private void updateTable() {
    Object[][] bookData = bookList.getBookData();

    // Clear any existing rows in the table
    tableModel.setRowCount(0);

    // Add each book's data to the table
    for (Object[] book : bookData) {
        tableModel.addRow(book);
    }
}
         


   
//    public void actionPerformed(ActionEvent e) {
//        // Handle action events
//    }
     private void populateTable() {
          Object[][] bookData = hh.getHistoryData(user);

        // Clear any existing rows in the table
        tableModel.setRowCount(0);

        // Add each book's data to the table
        for (Object[] book : bookData) {
            tableModel.addRow(book);
        }
    }
//     private void updateTable(User user) {
//    if (user == null || user.getHistory() == null) {
//        System.out.println("Error: User or history is null.");
//        tableModel.setRowCount(0);  // Clear table
//        return;
//    }
//
//    Object[][] historyData = user.getHistory().getHistoryData(); // Get user history as 2D array
//
//    // Debugging output
//    System.out.println("Updating table with the following data:");
//    for (Object[] row : historyData) {
//        System.out.println(Arrays.toString(row));
//    }
//
//    // Clear existing rows in the table
//    tableModel.setRowCount(0);
//
//    // Add each history record to the table
//    for (Object[] record : historyData) {
//        tableModel.addRow(record);
//    }
//}
     private void updateTableWithSortedBooks(DefaultTableModel defTab) {
    // Get the sorted books data
    Object[][] sortedBooks = bookList.sortByTitle(); // Calls quickSortByTitle() inside sortByTitle()

    // Clear the table model
    defTab.setRowCount(0);

    // Add the sorted books to the table model
    for (Object[] book : sortedBooks) {
        defTab.addRow(book);
    }
}
                
    public void resetTableData() {
    DefaultTableModel model = (DefaultTableModel) tableModel;
    Object[][] getBookData = bookList.getBookData();
    model.setRowCount(0); // Clear the table
    
    for (Object[] book : getBookData) {
        String availability = (boolean) book[4] ? "Available" : "Checked Out";
        model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
    }
}
    

     private void updateSelectedBooksPanel() {
    JPanel selectedBooksPanel = new JPanel();
    selectedBooksPanel.removeAll(); // Clear previous selections
    for (NodeBook book : selectedBooks) {
        JLabel label = new JLabel(book.getTitle() + " (ISBN: " + book.getISBN() + ")");
        selectedBooksPanel.add(label);
    }
    selectedBooksPanel.revalidate();
    selectedBooksPanel.repaint();
}
     public void Table(){
      if(table.getSelectedRow()!= -1){
      int row = table.getSelectedRow();
      title.setText(tableModel.getValueAt(row, 0).toString());
    code.setText(tableModel.getValueAt(row, 2).toString());
        authors.setText(tableModel.getValueAt(row, 1).toString());
          genre.setText(tableModel.getValueAt(row, 3).toString());
            avails.setText(tableModel.getValueAt(row, 4).toString());
     }    
   
     }
     public static void main (String [] args){
   
     }
     }
      


