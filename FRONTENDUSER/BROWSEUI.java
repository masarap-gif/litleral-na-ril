/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDUSER;

/**
 *
 * @author ivan
 */
import BACKENDUSER.LLhistory;
import DSA.LinkedlistBook;
import DSA.NodeBook;
import LogSigBackEnd.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;



public class BROWSEUI extends parentComponent implements ActionListener, ItemListener { 
//    ImageIcon icon = new ImageIcon("C:\\Users\\danic\\Downloads\\icons8-search-50.png");
   LinkedlistBook bookList;
  LLhistory bookHistory;
    private JComboBox<String> genreComboBox;
    private JPanel bookPanel;
    
    private JLabel a, lb1, lb2, lb3, lb4, b;
    private JTextField isbn;
    private JButton borrow, back, bbrw, search, exit;
    private JPanel pnl1, pnl2, pnl3, pnl4;
     private String[] headers = {"Title", "Author", "ISBN", "Genre", "Availability"};
     User users;
   DefaultTableModel defTab;

     
     public BROWSEUI(User user,LinkedlistBook bookList , LLhistory bookHistory){
                this.users = user;
        this.bookList = bookList;
        this.bookHistory = bookHistory;

        // Add table
        setSize(1476, 896);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(icon);
        setLayout(null);
        setTitle("Browse Book");

        defTab = new DefaultTableModel(bookList.getBookData(), headers);
        JTable table = new JTable(defTab);
        JScrollPane sp = new JScrollPane(table);

        // When this frame displays, the sorted books are displayed without performing an action
        updateTableWithSortedBooks(defTab);

        pnl2 = new JPanel();
        pnl2.setLayout(null);
        pnl2.setBackground(new Color(0xD9D9D9));
        pnl2.setBounds(0, 0, 1530, 86);

        search = new JButton();
        ImageIcon icon = new ImageIcon("images\\search.png");
        Image image = icon.getImage();
        Image image2 = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        search.setIcon(new ImageIcon(image2));
        search.setBounds(1330, 150, 50, 65);
        search.setBorder(null);
        search.setHorizontalTextPosition(JButton.LEFT);
        search.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(0xBB9457));
        search.addActionListener(e -> performSearch(table));

        a = new JLabel(users.getUsername());
        a.setFont(new Font("Bebas Neue", Font.BOLD, 48));
        a.setBounds(116, 20, 315, 60);

        exit = new JButton();
        exit.setBounds(1259, 15, 187, 56);
        exit.setText("Back");
        exit.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        exit.setForeground(Color.white);
        exit.setBackground(new Color(0xBB9457));
        exit.addActionListener(e -> {
            this.dispose(); // Dispose of current frame
            new DashboardUser(user, bookList, bookHistory).setVisible(true); // Open DashboardUser
        });

        lb1 = new JLabel("Title");
        lb1.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        lb1.setBounds(1000, 20, 100, 43);
        lb1.setForeground(new Color(0xBB9457));

        isbn = new JTextField();
        isbn.setBounds(1000, 60, 400, 76);
        isbn.setBackground(new Color(0xBB9457));
        isbn.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
        isbn.setForeground(Color.white);
       isbn.add(search);

        lb2 = new JLabel("Genre");
        lb2.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        lb2.setBounds(80, 20, 200, 43);
        lb2.setForeground(new Color(0xBB9457));

        genreComboBox = new JComboBox<>(new String[]{"All", "Fiction", "Non-Fiction", "Education"});
        genreComboBox.setSelectedIndex(0);
        genreComboBox.setBounds(80, 60, 320, 76);
        genreComboBox.setPreferredSize(new Dimension(333, 76));
        genreComboBox.setBackground(new Color(0x99582A));
        genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
        genreComboBox.setForeground(Color.white);
        genreComboBox.addActionListener(e -> updateTableWithGenreSelection(table));

        // Book panel for displaying books of the selected genre
        bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBounds(33, 250, 728, 300);
        bookPanel.setBackground(new Color(0x6F1D1B));

        pnl1 = new JPanel();
        pnl1.setLayout(null);
        pnl1.setBackground(new Color(0x6F1D1B));
        pnl1.setBounds(-7, 86, 1500, 875);

        borrow = new JButton("Borrow");
        borrow.setBounds(950, 6550, 250, 88);
        borrow.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        borrow.setForeground(new Color(0x6F1D1B));
        borrow.setBackground(Color.white);
        borrow.addActionListener(e -> performBorrowAction(table));

        back = new JButton("Back");
        back.setBounds(1200, 750, 226, 88);
        back.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        back.setForeground(new Color(0x6F1D1B));
        back.setBackground(Color.white);
        back.addActionListener(e -> {
            this.dispose(); // Dispose of current frame
            new DashboardUser(user, bookList, bookHistory).setVisible(true); // Navigate back
        });

        pnl4 = new JPanel();
        pnl4.setLayout(null);
        pnl4.setBounds(33, 240, 1390, 450);
        pnl4.setBackground(new Color(0xBB9457));

        b = new JLabel("Borrowing");
        b.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        b.setBounds(270, 0, 826, 100);
        b.setForeground(new Color(0x6F1D1B));

        sp.setBounds(33, 20, 1320, 400);
        table.setFont(new Font("Bebas Neue", Font.PLAIN, 15));

        pnl2.add(a);
        pnl2.add(exit);
        pnl1.add(lb1);
        pnl1.add(lb2);
        pnl1.add(isbn);
        pnl1.add(sp);
        pnl1.add(genreComboBox);
        pnl1.add(bookPanel);
        pnl4.add(sp);

        add(borrow);
        add(pnl4);
       // add(search);
        add(pnl2);
        add(pnl1);

        this.setVisible(true);
    }

    private void performSearch(JTable table) {
        String title = isbn.getText();
         

        if (!title.isEmpty()) {
            try {
                // Search for the book by title in the LinkedList
                NodeBook book = bookList.linearSearchByTitle(title);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear the table before adding new rows

                // Check if the book is found
                if (book != null) {
                    model.addRow(new Object[]{
                            book.getTitle(), book.getAuthor(), book.getISBN(), book.getGenre(), book.getIsAvailable() ? "Available" : "Checked Out"
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "No book found with title: " + title, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while searching for the book.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a title to search.", "Error", JOptionPane.ERROR_MESSAGE);
            resetTableData(table); // Reset the table data if no title is entered
        }
    }

    private void resetTableData(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[][] getBookData = bookList.getBookData();
        model.setRowCount(0); // Clear the table

        for (Object[] book : getBookData) {
            String availability = (boolean) book[4] ? "Available" : "Checked Out";
            model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
        }
    }

    private void updateTableWithSortedBooks(DefaultTableModel model) {
        Object[][] sortedBooks = bookList.getBookData();
        model.setRowCount(0); // Clear the table before adding new rows

        for (Object[] book : sortedBooks) {
            String availability = (boolean) book[4] ? "Available" : "Checked Out";
            model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
        }
    }

    private void updateTableWithGenreSelection(JTable table) {
        String selectedGenre = (String) genreComboBox.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table before adding new rows

        // Update table data based on selected genre
        Object[][] filteredBooks = bookList.getFilteredBooksByGenre(selectedGenre);
        for (Object[] book : filteredBooks) {
            String availability = (boolean) book[4] ? "Available" : "Checked Out";
            model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
        }
    }

    private void performBorrowAction(JTable table) {
        // Implementation for borrowing action
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String isbn = table.getValueAt(selectedRow, 2).toString();
            int cd = Integer.parseInt(isbn);
            NodeBook book = bookList.getBookByIsbn(cd);

            if (book != null && book.getIsAvailable()) {
                book.setIsAvailable(false);
                JOptionPane.showMessageDialog(this, "Book borrowed successfully!");
                updateTableWithSortedBooks((DefaultTableModel) table.getModel());
            } else {
                JOptionPane.showMessageDialog(this, "This book is currently unavailable.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}