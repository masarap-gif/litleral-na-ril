/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;
import BACKENDLIB.BookBase;
import DSA.LinkedlistBook;
import   DSA.LinkedlistBook.*;
import DSA.NodeBook;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.random;
import java.text.ParseException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author ivan
 */
public class VIEWLISTUI extends parentComponent implements BookBase{
    

   public   static LinkedlistBook bookList = new LinkedlistBook();
   String[] columnNames = {"Title", "Author", "ISBN", "Genre", "Availability","BookID","Quantity","Book Status","Shelf Number","Year of Publication"};
    DefaultTableModel tableModel;
    JTable bookTable;
    JScrollPane sp;
     JLabel title = new JLabel("Inventory");
     JPanel panel = new JPanel();
       JPanel pnl2 = new JPanel();
     JLabel a = new JLabel();
     JButton add, delete,update;   
     JLabel isbn,booktitle,author,form,genere,Images,bookID,quant;
     JSpinner yrPubliSpinner; 
     JTextField ttFld, codfld, authfld,isbnfld,BookID,Quantity,cell,avail,isbns;
     JButton adds,cancel,remove,cancels,updabbt;
      private JComboBox<String> genreComboBox;
       ImageIcon searchIMG = new ImageIcon("images\\search.png");
      

  
    

    // Constructor
  public  VIEWLISTUI() {
     // VIEWLISTUI.bookList = book;
        // Add default books to the bookList
      
               designFrame();
  }
  public void table(){
       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
               centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);  

for (int i = 0; i < bookTable.getColumnCount(); i++) {
    bookTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
                     
      
                    bookTable.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent evt) {
        try {
            int row = bookTable.getSelectedRow();
            int columnCount = bookTable.getColumnCount();

            if (row < 0 || row >= bookTable.getRowCount()) {
                JOptionPane.showMessageDialog(null, "Please select a valid row.");
                return;
            }

            // Get the course/genre from the selected row
            String course = String.valueOf(bookTable.getValueAt(row, 3)).trim();
            System.out.println("Course selected: " + course);

            // Ensure comboBox is populated correctly
            System.out.println(Arrays.toString(BookBase.genre));

            // Attempt to set the selected item in the genre combo box
            boolean itemFound = false;
            for (int i = 0; i < genreComboBox.getItemCount(); i++) {
                String genreItem = genreComboBox.getItemAt(i).toString().trim();
                if (course.equals(genreItem)) {
                    genreComboBox.setSelectedItem(genreItem);
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                JOptionPane.showMessageDialog(null, "Course not found in combo box.");
            }

            // Populate other fields
            ttFld.setText(String.valueOf(bookTable.getValueAt(row, 0)));
            authfld.setText(String.valueOf(bookTable.getValueAt(row, 1)));
            isbnfld.setText(String.valueOf(bookTable.getValueAt(row, 2)));
            BookID.setText(String.valueOf(bookTable.getValueAt(row, 5)));
            Quantity.setText(String.valueOf(bookTable.getValueAt(row, 6)));
            cell.setText(String.valueOf(bookTable.getValueAt(row, 8)));
           Object dateObj = bookTable.getValueAt(row, 9); // Get the object from the table
Date date = null;

if (dateObj instanceof Date) {
    date = (Date) dateObj;  // If it's already a Date, cast it directly
} else if (dateObj instanceof String) {
    // If it's a string, parse it into a Date
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  // Adjust format as needed
        date = sdf.parse((String) dateObj);
    } catch (ParseException b) {
        JOptionPane.showMessageDialog(null, "Invalid date format: " + b.getMessage());
        return;
    }
}
            // cell.setText(String.valueOf(bookTable.getValueAt(row, 9)));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
});
    }
  

    // Method to add default books
  

    
   
    public void designFrame(){
        tableModel = new DefaultTableModel(columnNames, 0); 
         bookTable  = new JTable(tableModel);
        sp = new JScrollPane(bookTable);
        

           
        panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(1280, 1049);
        panel.setLayout(null);
        panel.setBounds(0, 0, 1280, 1049);
        panel.setBackground(new Color(0x6F1D1B));

        //textFld
            ttFld = new JTextField();
           ttFld.setBounds(60, 149, 300, 47);
        ttFld.setBackground(new Color(0xBB9457));
        ttFld.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        ttFld.setForeground(Color.white);
        
              cell = new JTextField();
               cell.setBounds(60, 350, 300, 47);
        cell.setBackground(new Color(0xBB9457));
        cell.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        cell.setForeground(Color.white);
        
              authfld = new JTextField();
            authfld.setBounds(500, 149, 300, 47);
        authfld.setBackground(new Color(0xBB9457));
        authfld.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        authfld.setForeground(Color.white);
        
                isbnfld = new JTextField();
                 isbnfld.setBounds(60, 247, 300, 47);
        isbnfld.setBackground(new Color(0xBB9457));
        isbnfld.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        isbnfld.setForeground(Color.white);
        
            String id = Integer.toString(bookList.generateUniqueBookID());
        
                
       
                  BookID = new JTextField();
        BookID.setBounds(500, 247, 300, 47);
        BookID.setBackground(new Color(0xBB9457));
        BookID.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        BookID.setForeground(Color.white);
         BookID.setText(id);
         
                    Quantity = new JTextField();
                         Quantity.setBounds(900, 247, 300, 47);
        Quantity.setBackground(new Color(0xBB9457));
        Quantity.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        Quantity.setForeground(Color.white);
        
        Date currentDate = new Date();
        SpinnerDateModel model = new SpinnerDateModel(currentDate, null, null, java.util.Calendar.YEAR);
        yrPubliSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(yrPubliSpinner, "yyyy"); // Set format to year only
        yrPubliSpinner.setEditor(editor);
        
        yrPubliSpinner.setBounds(500, 350, 300, 47);
        yrPubliSpinner.setBackground(new Color(0xBB9457));
        yrPubliSpinner.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        yrPubliSpinner.setForeground(Color.white);
        
         genreComboBox = new JComboBox<>(BookBase.genre); 
         genreComboBox.setBounds(900, 149, 300, 47);
        genreComboBox.setBackground(new Color(0xBB9457));
        genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        genreComboBox.setForeground(Color.white);
        
        
       //lables
        JLabel titlelbl= new JLabel("Title");
        JLabel authorlbl = new JLabel("Author");
        JLabel genreLbl = new JLabel("Genre");
        JLabel isbnLbl = new JLabel("ISBN");
        JLabel yrOfPubLbl = new JLabel(" Year of publication");
        JLabel quantityLbl = new JLabel("Quantity");
        JLabel cellLbl = new JLabel("Shelf number");
        JLabel bokId= new JLabel("Book ID");
        
        titlelbl.setBounds(60, 113, 375, 30);
        titlelbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        titlelbl.setForeground(Color.white);
        
        authorlbl.setBounds(500, 113, 375, 30);
        authorlbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        authorlbl.setForeground(Color.white);
        
         genreLbl.setBounds(900, 113, 375, 30);
        genreLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        genreLbl.setForeground(Color.white);
        
           isbnLbl.setBounds(60, 208, 375, 30);
        isbnLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        isbnLbl.setForeground(Color.white);

            bokId.setBounds(500, 208, 375, 30);
        bokId.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        bokId.setForeground(Color.white);
        
            quantityLbl.setBounds(900, 208, 375, 30);
        quantityLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        quantityLbl.setForeground(Color.white);
        
           cellLbl.setBounds(60, 311, 375, 30);
        cellLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        cellLbl.setForeground(Color.white);
        
           yrOfPubLbl.setBounds(500, 311, 375, 30);
        yrOfPubLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,24));
        yrOfPubLbl.setForeground(Color.white);
   
        add = new JButton("Add");
        add.setBounds(500, 467, 187, 56);
        add.setFont(new Font ("Bebas Neue", Font.BOLD, 23));
        add.setBackground(new Color(0xBB9457));
        add.setForeground(Color.white);
       add.addActionListener(e ->addFrame());
       
     Image img = searchIMG.getImage();  // Transform it 
            Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
            searchIMG = new ImageIcon(newImg);

           JButton search = new JButton();
            search.setBounds(266, 5, 81, 53);
            search.setBackground(new Color(0xBB9457));
            search.setBorderPainted(false);
            search.setOpaque(true);
            search.setContentAreaFilled(true);
            search.setIcon(searchIMG);
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int searchISBN = Integer.parseInt(isbns.getText());  // ISBN entered by user
                        NodeBook foundBook = bookList.LinearSeach(searchISBN);  // Call search method

                        if (foundBook != null) {
                            // Book found, display its details in pnl3
                            JOptionPane.showMessageDialog(null, "ISBN Does exist");
                             DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
                model.setRowCount(0);  // Clear the table

                model.addRow(new Object[] {
                    foundBook.getTitle(), 
                    foundBook.getAuthor(), 
                    foundBook.getISBN(), 
                    foundBook.getGenre(),
                    foundBook.getIsAvailable() ? "Available" : "Not Available", 
                    foundBook.getBookId(), 
                    foundBook.getQuan(),
                    foundBook.getStatus(),
                    foundBook.shelft()
                });

                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ISBN Does not exist");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid ISBN number.");
                    }
                }
            });

              isbns = new JTextField();
            isbns.setBounds(80, 470, 350, 56);
            isbns.setBackground(new Color(0xBB9457));
            isbns.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            isbns.setForeground(Color.white);
            isbns.setLayout(null);
            isbns.setBorder(null);
            isbns.add(search);
            
            
      
       
        update = new JButton("Update");
        update.setBounds(761, 467, 187, 56);
        update.setFont(new Font ("Bebas Neue", Font.BOLD, 23));
        update.setBackground(new Color(0xBB9457));
        update.setForeground(Color.white);
       update.addActionListener(e ->updateFrame());
        
          delete = new JButton("Delete");
        delete.setBounds(1000, 467, 187, 56);
        delete.setFont(new Font ("Bebas Neue", Font.BOLD, 23));
        delete.setBackground(new Color(0x99582A));
        delete.setForeground(Color.white);
        delete.addActionListener(e->deleteFrame());

        
        title.setBounds(31, 19, 500, 77);
        title.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 64));
        title.setForeground(Color.white);
        
        sp.setBounds(40, 560, 1190, 419);
        bookTable.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,12));
        bookTable.setForeground(new Color(0x6F1D1B));
        

        panel.add(title);
        panel.add(update);
        panel.add(sp);
        panel.add(add);
        panel.add(delete);
        panel.add(ttFld);
         panel.add(cell);
         panel.add(authfld);
         panel.add(isbnfld);
         panel.add(BookID);
         panel.add(Quantity);
         panel.add(yrPubliSpinner);
         panel.add(titlelbl);
           panel.add(authorlbl);
             panel.add(genreLbl);
               panel.add(isbnLbl);
                panel.add(bokId);
                 panel.add(quantityLbl);
                  panel.add(cellLbl);
                        panel.add(yrOfPubLbl);
                        panel.add(genreComboBox);
                        panel.add(isbns);
                            
         

        
                table();
    
   
        
        populateTable();
         panel.setVisible(true);
        
     
    }
   public void populateTable() {
    DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
    model.setRowCount(0);  // Clear the table
    NodeBook current = LinkedlistBook.head;
    while (current != null) {
        model.addRow(new Object[] {
            current.getTitle(), current.getAuthor(), current.getISBN(), current.getGenre(),
           current.getIsAvailable() ? "Available" : "Not Available", current.getBookId(), current.getQuan(),current.getStatus(),current.shelft()
        });
        current = current.next;  // Ensure the bookData includes the BookID field
    }
   }
    
    public void addFrame(){
       
        // Retrieve input fields
        String title = ttFld.getText();
        String author = authfld.getText();
        String isbnText = isbnfld.getText();
        String quan = Quantity.getText();
      //  Date getYr = yrPubliSpinner.get
       String shelf = cell.getText();

        // Validate Quantity
        int quantity;
        int shelfNum;
        try {
    quantity = Integer.parseInt(Quantity.getText());
    shelfNum = Integer.parseInt(cell.getText());
    if (quantity <= 0) {
        JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.");
        return;
    }
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
    return;
}
        // Validate ISBN
        int ISBN;
        try {
            ISBN = Integer.parseInt(isbnText);  // Convert ISBN to int
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ISBN number.");
            return;
        }

        // Generate unique BookID
       int auto = bookList.generateUniqueBookID();
       Date yrPublished =(Date) yrPubliSpinner.getValue();

        // Retrieve selected genre
        String genre = genreComboBox.getSelectedItem().toString();

        // Debug log
        System.out.println("Adding Book - Title: " + title + ", Author: " + author + 
                           ", ISBN: " + ISBN + ", Genre: " + genre + 
                           ", BookID: " + auto + ", Quantity: " + quantity + "Yr pbulsied" +yrPublished + "shelfNum" +shelfNum);
                           

        // Add the book to the bookList
        bookList.addBook(title, author, ISBN, genre, auto, quantity, true,yrPublished,shelfNum);

        // Refresh the table with updated data
        populateTable();

        // Clear fields after adding the book
        JOptionPane.showMessageDialog(null, "New book has been added");
        
       ttFld.setText("");
       authfld.setText("");
          BookID.setText("");
           genreComboBox.setSelectedIndex(0); 
           Quantity.setText("");
           isbnfld.setText("");
          cell.setText("");

    }
    
    
    public void deleteFrame(){
        int selectedRow = bookTable.getSelectedRow(); // Get the selected row index
        
        if (selectedRow == -1) { // Check if no row is selected
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }
        
        // Retrieve the ISBN from the selected row
        int ISBN = (int) bookTable.getValueAt(selectedRow, 2); // Assuming ISBN is in column index 2
        
        // Remove the book from the bookList
        bookList.removeBook(ISBN);
        
        // Refresh the table to reflect the changes
        populateTable();

        // Optionally, show a success message
        JOptionPane.showMessageDialog(null, "Book removed successfully.");
    }
    

                
      
        
      

    public void updateFrame(){
        int row = bookTable.getSelectedRow();
    
    if (row != -1) { // Ensure a row is selected
        // Assuming 'bookTable' has data like ISBN, Title, Author, Genre in columns 0, 1, 2, 3 respectively
        String isbnData =  bookTable.getValueAt(row, 2).toString();  // ISBN
        String titleData = bookTable.getValueAt(row, 0).toString();  // Title
        String authorData =  bookTable.getValueAt(row,1 ).toString();  // Author
        String genreData =  bookTable.getValueAt(row, 3).toString(); 
         String quan =  bookTable.getValueAt(row, 6).toString();// Genre

        JFrame update = new JFrame("Update Book");
        update.setSize(719, 676);
        update.setLayout(null);
        update.setResizable(false);
        update.setLocationRelativeTo(null);
        
        form = new JLabel("Update Form");
        form.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        form.setForeground(new Color(0x6F1D1B));
        form.setBounds(380, 65, 400, 50);
        
        isbn = new JLabel("ISBN");
        isbn.setBounds(45, 220, 165, 37);
        isbn.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        isbn.setForeground(new Color(0x6F1D1B));
        
        booktitle = new JLabel("Title");
        booktitle.setBounds(45, 140, 165, 37);
        booktitle.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        booktitle.setForeground(new Color(0x6F1D1B));
         
        author = new JLabel("Author");
        author.setBounds(45, 300, 145, 37);
        author.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        author.setForeground(new Color(0x6F1D1B));
             
        genere = new JLabel("Genre");
        genere.setBounds(45, 380, 145, 37);
        genere.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        genere.setForeground(new Color(0x6F1D1B));
        
         quant = new JLabel("Quantity");
        quant.setBounds(45, 460, 145, 37);
        quant.setFont(new Font("Bebas Neue", Font.BOLD, 23));
        quant.setForeground(new Color(0x6F1D1B));
        
        
        
        // textField
        ttFld = new JTextField();
        ttFld.setBounds(203, 140, 493, 48);
        ttFld.setBackground(new Color(0xD9D9D9));
        ttFld.setText(titleData); // Set title

        codfld = new JTextField();
        codfld.setBounds(203, 220, 493, 48);
        codfld.setBackground(new Color(0xD9D9D9));
        codfld.setText(isbnData); // Set ISBN

        authfld = new JTextField();
        authfld.setBounds(203, 300, 493, 48);
        authfld.setBackground(new Color(0xD9D9D9));
        authfld.setText(authorData); // Set Author
        
       Quantity = new JTextField();
        Quantity.setBounds(203, 460, 493, 48);
        Quantity.setBackground(new Color(0xD9D9D9));
        Quantity.setText(quan); // Set Author
        
        genreComboBox = new JComboBox<>(BookBase.genre);
        genreComboBox.setSelectedItem(genreData); // Set Genre
        genreComboBox.setBounds(203, 380, 493, 48);
        genreComboBox.setBackground(new Color(0xD9D9D9));
        genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
        
        // Buttons
        updabbt = new JButton("Update");
        updabbt.setBounds(203, 578, 234, 48);
        updabbt.setBackground(new Color(0xD9D9D9));
        updabbt.setForeground(new Color(0x6F1D1B));
        updabbt.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        updabbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = ttFld.getText();
        String author = authfld.getText();
        String isbnText = codfld.getText();
         String quan = Quantity.getText();

        // Validate Quantity
        int quantity;
        try {
    quantity = Integer.parseInt(Quantity.getText());
    if (quantity <= 0) {
        JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.");
        return;
    }
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
    return;
}

        int ISBN = 0;
        try {
            ISBN = Integer.parseInt(isbnText);  // Convert ISBN to integer
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ISBN number.");
            return;
        }

        String genre = genreComboBox.getSelectedItem().toString();

        // Check if the book exists in the LinkedList and update it
        boolean bookUpdated = bookList.updateBook(ISBN, title, author, genre,quantity);

        if (bookUpdated) {
            // Refresh the table to reflect updated data
            populateTable();

            // Clear the fields after update
            update.dispose();
            ttFld.setText("");
            authfld.setText("");
            codfld.setText("");
            BookID.setText("");
     
            cell.setText("");
            genreComboBox.setSelectedIndex(0); 
            JOptionPane.showMessageDialog(null, "The book has been Updated!");// Reset the genre selection
        } else {
            JOptionPane.showMessageDialog(null, "Book not found, please check the ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});

            
            
        cancel = new JButton("Cancel");
        cancel.setBounds(460, 578, 234, 48);
        cancel.setBackground(new Color(0xD9D9D9));
        cancel.setForeground(new Color(0x6F1D1B));
        cancel.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        cancel.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(cancel);
            if (currentFrame != null) {
                currentFrame.dispose();
            }
            this.setVisible(true);
        });
        
        // Add components to the frame
        update.add(form);
        update.add(isbn);
        update.add(booktitle);
        update.add(author);
        update.add(genere);
        update.add(ttFld);
        update.add(codfld);
        update.add(authfld);
        update.add(genreComboBox);
        update.add(updabbt);
        update.add(cancel);
        update.add(quant);
        update.add(Quantity);
        
        // Set frame visibility
        update.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(null, "Please select a book to update.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}    

    
   
    public static void main (String[] args){
        LinkedlistBook books = new LinkedlistBook();
       new VIEWLISTUI();
    }

    
      
   }

