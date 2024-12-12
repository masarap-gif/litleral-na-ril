/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;

/**
 *
 * @author ivan
 */

import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import LogSigBackEnd.UserService;
    import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class adminCOA extends parentComponent{
     private JPanel pan5 = new JPanel();
    private JPanel pan6 = new JPanel();
    private JPanel pan7 = new JPanel();
    private JLabel amlbl = new JLabel("Account Management");
    private JTable accountTable = new JTable();
    private JScrollPane accountTableScp = new JScrollPane(accountTable);
    private JTextField searchField = new JTextField();
    private JButton searchbtn = new JButton("Search");
    private JButton addAccount = new JButton("Add Account");
    private JButton deleteAccount = new JButton("Delete Account");
    private JButton unblockAccount = new JButton("Unblock Account"); // no function yet
    private JButton changePassword = new JButton("Change Password");
    
    private JButton backbtnCOA = new JButton("Back");
    private JComboBox<String> userCategory = new JComboBox<>(new String[]{"All Accounts", "Librarian Accounts", "Admin Accounts", "User Accounts", "Blocked Accounts"});

    private static String[] columnsForAccounts = {"Member ID", "Username", "Email", "Contact", "Role"};
    private static DefaultTableModel accountTableModel = new DefaultTableModel(columnsForAccounts, 0);

    private LinkedListAccounts accounts;
    private UserService userService;
    
    public adminCOA(UserService userService,LinkedListAccounts accounts) {
        this.userService =  userService;
        this.accounts = accounts;

   
    JPasswordField newPasswordField = new JPasswordField();
 
       
        
        pan5.setBackground(new Color(0x99582A));
        pan5.setLayout(null);
        pan5.setPreferredSize(new Dimension(800, 875));

        pan6.setBackground(new Color(0x6F1D1B));
        pan6.setLayout(null);
        pan6.setBounds(850, 86, 758, 840);
        
        pan7.setLayout(null);
        pan7.setBackground(new Color(0xD9D9D9));
        pan7.setBounds(0, 0, 1530, 86);

        amlbl.setBounds(70, 21, 560, 77);
        amlbl.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        amlbl.setForeground(new Color(0xBB9457));

        accountTable.setModel(accountTableModel);
        accountTableScp.setBounds(50, 175, 780, 490);

        searchField.setBounds(50, 125, 250, 30);
        searchbtn.setBounds(310, 125, 100, 30);
        userCategory.setBounds(420, 125, 200, 30);
        
        addAccount.setBounds(185, 200, 310, 88);
        addAccount.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        addAccount.setForeground(new Color(0x6F1D1B));
        addAccount.setBackground(new Color (0xD9D9D9));
        
        deleteAccount.setBounds(185, 350, 310, 88);
        deleteAccount.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        deleteAccount.setForeground(new Color(0x6F1D1B));
        deleteAccount.setBackground(new Color (0xBB9457));
        
        unblockAccount.setBounds(500, 700, 310, 88);
        unblockAccount.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        unblockAccount.setForeground(new Color(0x6F1D1B));
        unblockAccount.setBackground(new Color (0xD9D9D9));
        unblockAccount.setVisible(false);
        
        changePassword.setBounds(500, 700, 310, 88);
        changePassword.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        changePassword.setForeground(new Color(0x6F1D1B));
        changePassword.setBackground(new Color (0xD9D9D9));
        

        backbtnCOA.setBounds(185, 500, 310, 88);
        backbtnCOA.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        backbtnCOA.setForeground(new Color(0x6F1D1B));
        backbtnCOA.setBackground(new Color (0xD9D9D9));
        backbtnCOA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                   dashBoard dashb = new dashBoard(userService,accounts);
                   dashb.setVisible(true);
                   
                   ((JFrame) SwingUtilities.getWindowAncestor(backbtnCOA)).dispose();
            }
        });
        
        addAccount.addActionListener(e -> addAccountDialog());
        deleteAccount.addActionListener(e -> {
    int selectedRow = accountTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select an account to delete.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Step 1: Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete this account?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Step 2: Prompt for admin password
            JPasswordField passwordField = new JPasswordField();
            int passwordPrompt = JOptionPane.showConfirmDialog(
                    null,
                    passwordField,
                    "Enter Admin Password",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (passwordPrompt == JOptionPane.OK_OPTION) {
                // Step 3: Get the entered password
                String enteredPassword = new String(passwordField.getPassword());
                
                // Step 4: Verify admin password
                if (accounts.verifyAdminPassword(enteredPassword)) { // Replace this with your verification logic
                    // Step 5: Proceed with account deletion
                     int userID = (int) accountTable.getValueAt(selectedRow, 0);

    // Perform deletion using userID
                      boolean deleted = accounts.deleteById(userID);
// Adjust for LinkedList 1-based index
                    loadAccounts("All Accounts");
                    JOptionPane.showMessageDialog(null, "Account successfully deleted.");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect password. Deletion cancelled.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Deletion cancelled.");
            }
        }
    }
});
     
        unblockAccount.addActionListener(e -> {
            int selectedRow = accountTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a blocked account to unblock", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String memberID = (String) accountTable.getValueAt(selectedRow, 0);
                NodeAccounts account = accounts.linearSearch(memberID);
                if (account!= null && "blocked".equalsIgnoreCase(account.getRole())) {
                    account.setRole("user");
                    JOptionPane.showMessageDialog(null, "Account unblocked successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadAccounts("Blocked Accounts"); //reload
                    
                }
                else {
                    JOptionPane.showMessageDialog(null, "This account isn't blocked", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        changePassword.addActionListener(e-> {
        JPanel passwordPanel = new JPanel(new GridLayout(3, 2));
        
        JPasswordField currentPasswordField = new JPasswordField();
        JPasswordField newPasswordFields = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();   
        
        passwordPanel.add(new JLabel("Current Password:"));
        passwordPanel.add(currentPasswordField);
        passwordPanel.add(new JLabel("New Password:"));
        passwordPanel.add(newPasswordFields);
        passwordPanel.add(new JLabel("Confirm New Password:"));
        passwordPanel.add(confirmPasswordField);
        
        System.out.println("Before dialog, NewPasswordField: " + newPasswordField.getPassword().length);
       
        int selectedRow = accountTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select an account to change password", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
        int option = JOptionPane.showConfirmDialog(null, passwordPanel, "Change Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
             String currentPassword = new String(currentPasswordField.getPassword());
         // Proper conversion
            String newPassword = new String(newPasswordFields.getPassword());    
        // Proper conversion
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String id = accountTable.getValueAt(selectedRow, 0).toString();
            int userId = Integer.parseInt(id);
            
 System.out.println("Current Password: " + currentPassword);
    System.out.println("New Password: " + newPassword);
    System.out.println("Confirm Password: " + confirmPassword);
    
        
        boolean success = accounts.changePassword(userId, currentPassword, newPassword);
            
if (success) {
    JOptionPane.showMessageDialog(null, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
} else {
    JOptionPane.showMessageDialog(null, "Failed to change password. Check details and try again.", "Error", JOptionPane.ERROR_MESSAGE);
}
        }   
            }
        });
        userCategory.addActionListener(e -> filterAccounts());
        userCategory.addActionListener(e -> {
             String selectedCategory = (String) userCategory.getSelectedItem();
            if ("Blocked Accounts".equals(selectedCategory)) {
            unblockAccount.setVisible(true);
            changePassword.setVisible(false);
        }
        else {
            unblockAccount.setVisible(false);
            changePassword.setVisible(true);
        }
        });
        searchbtn.addActionListener(e -> searchAccounts());
        searchField.addActionListener(e -> searchAccounts());
        
        
        
        loadAccounts("All Accounts");
        
        
        pan5.add(pan6);
        pan5.add(pan7);
        pan5.add(accountTableScp);
        pan5.add(searchField);
        pan5.add(searchbtn);
        pan5.add(userCategory);
        pan5.add(unblockAccount);
        pan5.add(changePassword);
        
        pan6.add(amlbl);
        pan6.add(addAccount);
        pan6.add(deleteAccount);
        pan6.add(backbtnCOA);

        this.add(pan5);
        
        this.setSize(1537, 875);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Account Management");
          this.setIconImage(icon);
        this.setResizable(false);
    
    }
    private void addAccountDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JTextField contactField = new JTextField();
        JComboBox<String> roleField = new JComboBox<>(new String[]{"Librarian", "Admin"});

        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField,
            "Email:", emailField,
            "Contact Number:", contactField,
            "Role:", roleField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String contact = contactField.getText();
            String role = (String) roleField.getSelectedItem();
            
           int userID = userService.addUser(username, password, contact, email, role);
                 
           userService.syncUsersWithLinkedList();

           // accounts.insertAtBeginning(username, password, email, contact,userID, role);
            JOptionPane.showMessageDialog(null, "Account added successfully with user ID!" +userID, "Success", JOptionPane.INFORMATION_MESSAGE);
            loadAccounts("All Accounts");
        }
    }
    private void filterAccounts() {
        String selectedCategory = (String) userCategory.getSelectedItem();
        
        loadAccounts(selectedCategory);
    }

    private void searchAccounts() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
       
        accountTableModel.setRowCount(0);
        NodeAccounts current = accounts.getHead();
        while (current != null) {
            if (String.valueOf(current.getMemberID()).contains(searchText) || 
    current.getUserName().contains(searchText) || 
    current.getRole().contains(searchText.toLowerCase())) {
    accountTableModel.addRow(new Object[]{
        current.getRole(),
        current.getUserName(),
        current.getEMail(),
        current.getContactNum(),
        current.getRole()
    });
}

            current = current.next;
        }
        if (accountTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No results found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }

    private void loadAccounts(String filterRole) {
    accountTableModel.setRowCount(0); // Clear the table

    NodeAccounts current = accounts.getHead();
    while (current != null) {
        boolean addRow = false;

        // Determine if the row should be added based on the filter
        if (filterRole.equals("All Accounts")) {
            addRow = true; // Show all accounts
        } else if (filterRole.equals("Librarian Accounts") && current.getRole().equalsIgnoreCase("librarian")) {
            addRow = true;
        } else if (filterRole.equals("Admin Accounts") && current.getRole().equalsIgnoreCase("admin")) {
            addRow = true;
        } else if (filterRole.equals("User Accounts") && current.getRole().equalsIgnoreCase("user")) {
            addRow = true;
        } else if (filterRole.equals("Blocked Accounts") && current.getRole().equalsIgnoreCase("blocked")) {
            addRow = true;
        }

        // Add matching rows to the table
        if (addRow) {
            accountTableModel.addRow(new Object[]{
                current.getMemberID(),
                current.getUserName(),
                current.getEMail(),
                current.getContactNum(),
                current.getRole()
            });
        }

        current = current.next;
    }
}
    
    private void viewBlockedAccounts() {
    StringBuilder blockedAccountsList = new StringBuilder("Blocked Accounts:\n\n");

    NodeAccounts current = accounts.getHead();
    boolean hasBlockedAccounts = false;

    while (current != null) {
        if ("blocked".equalsIgnoreCase(current.getRole())) {
            hasBlockedAccounts = true;
            blockedAccountsList.append("ID: ").append(current.getMemberID()).append("\n")
                               .append("Username: ").append(current.getUserName()).append("\n")
                               .append("Email: ").append(current.getEMail()).append("\n")
                               .append("Contact: ").append(current.getContactNum()).append("\n")
                               .append("Role: ").append(current.getRole()).append("\n\n");
        }
        current = current.next;
    }

    if (hasBlockedAccounts) {
        JOptionPane.showMessageDialog(this, blockedAccountsList.toString(), "Blocked Accounts", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No blocked accounts found.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
       
    
    

    public static void main(String[] args) {
//        adminCOA create = new adminCOA();
//        create.setVisible(true);
    }
}

