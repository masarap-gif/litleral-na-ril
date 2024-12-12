/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;

/**
 *
 * @author ivan
 */

import LogSigBackEnd.User;
import java.util.ArrayList;
    import javax.swing.JOptionPane;

public class LinkedListAccounts{
    public static NodeAccounts head;
    private final int maxSize = 100;
      private final ArrayList<NodeAccounts> blockedAccounts = new ArrayList<>();

    private int size = 0;
   

    public LinkedListAccounts() {
        this.head = null;
        //this.user = user;
        
         String defaultUsername = "admin";
    String defaultPassword = "admin123";
    String defaultEmail = "admin@example.com";
    String defaultContact = "1234567890";
    int defaultId = 1;
    String defaultRole = "Admin";
    
     insertAtBeginning(defaultUsername, defaultPassword, defaultEmail, defaultContact, defaultId, defaultRole);
    }

   
    public boolean isFull() {
        return size >= maxSize;
    }

    public void insertAtBeginning(String userName, String password, String eMail, String contactNum,int id,  String role) {
        if (isFull()) {
            System.out.println("List is full. Cannot add more accounts.");
            return;
        }
       // int memberID = generateRandomID();
        NodeAccounts newNode = new NodeAccounts(userName, password, eMail, contactNum, id, role);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public int generateRandomID() {
    int id = (int) (Math.random() * 100); // Generates a random ID within a specific range.
    while (!isUniqueID(id)) { // Keep generating until a unique ID is found
        id = (int) (Math.random() * 100);
    }
    return id;
}
    

public boolean isUniqueID(int id) {
    NodeAccounts current = getHead();
    while (current != null) {
        if (current.getMemberID()== id) {
            return false; // ID is not unique
        }
        current = current.getNext();
    }
    return true; // ID is unique
}

    public void deleteAtEnd() {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        if (head.next == null) {
            head = null;
        } else {
            NodeAccounts current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
    }

  public boolean deleteById(int userID) {
    if (head == null) {
        System.out.println("The list is empty.");
        return false;
    }

    // If the head node matches
    if (head.getMemberID() == userID) {
        int choice = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this account?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.NO_OPTION) {
            System.out.println("Deletion canceled.");
            return false;
        }

        head = head.next; // Remove head node
        size--;
        System.out.println("Deleted account with userID: " + userID);
        return true;
    }

    // Traverse the list to find the matching node
    NodeAccounts current = head;
    while (current.next != null) {
        if (current.next.getMemberID() == userID) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete this account?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (choice == JOptionPane.NO_OPTION) {
                System.out.println("Deletion canceled.");
                return false;
            }

            current.next = current.next.next; // Remove the matching node
            size--;
            System.out.println("Deleted account with userID: " + userID);
            return true;
        }
        current = current.next;
    }

    // If no match is found
    System.out.println("Account with userID: " + userID + " not found.");
    return false;
}

    public void createAccount(String userName, String password, String eMail, String contactNum,int id, String role) {
    if (isFull()) {
        System.out.println("List is full. Cannot create new account.");
        return;
    }
   // int memberID = generateRandomID();
    NodeAccounts newNode = new NodeAccounts(userName, password, eMail, contactNum, id, role);

    if (head == null) {
        head = newNode;
    } else {
        NodeAccounts current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    size++;
}

    public NodeAccounts getHead() {
        return head;
    }

    public NodeAccounts linearSearch(String userName) {
         NodeAccounts current = head; // Assuming `head` is the start of your linked list.
    while (current != null) {
        if (current.getUserName().equals(userName)) {
            System.out.println("Found user in linked list during search: " + userName);
            return current;
        }
        current = current.getNext();
    }
    System.out.println("User not found in linked list during search: " + userName);
    return null;
    }
    public boolean isExisted(int id){
        NodeAccounts current = head;
        while(current != null){
         if(current.getMemberID()==id){
             System.out.println("Found user in linked list during search: " + id);
            return true;
         }
         current = current.getNext();
    }
        System.out.println("User not found in linked list during search: " + id);
    return false;
    }
     public boolean isBlocked(String role ) {
       for (NodeAccounts account : blockedAccounts ) {
           if (account.getRole().equals(role)) {
               return true;
           }
       }
       return false;
   }
   public void unblockAccount(String userName) {
        NodeAccounts toUnblock = null;
        for (NodeAccounts account : blockedAccounts) {
            if (account.getUserName().equals(userName)) {
                toUnblock = account;
                break;
            }
        }

        if (toUnblock != null) {
            blockedAccounts.remove(toUnblock);
            JOptionPane.showMessageDialog(null, "Account unblocked successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Account not found in blocked list.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        public boolean verifyAdminPassword(String enteredPassword) {
                // Check if the list is empty
    if (head == null) {
        System.out.println("No accounts in the list.");
        return false;
    }

    // Check if the head node is the admin account
    if ("Admin".equals(head.getRole())) {
        // Verify the password
        return head.getPassword().equals(enteredPassword);
    }

    System.out.println("The first account is not an admin account.");
    return false;
}
        public boolean changePassword(int userId, String currentPassword, String newPassword) {
    NodeAccounts current = head; // Start at the head of the linked list.
    
    // Traverse the linked list to find the user with the matching ID.
    while (current != null) {
        if (current.getMemberID() == userId) { // Match found
            // Check if the current password matches the stored password.
            if (!current.getPassword().equals(currentPassword)) {
                System.out.println("Current password is incorrect.");
                return false; // Current password doesn't match.
            }
            
            // Check if the new password meets the requirements.
            if (newPassword.length() < 6) {
                JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long.");
                //System.out.println("");
                return false; // Password validation failed.
            }

            // Update the password and confirm success.
            current.setPassword(newPassword);
            System.out.println("Password changed successfully for user ID: " + userId);
            return true;
        }
        current = current.getNext(); // Move to the next node.
    }
    
    System.out.println("User ID not found in the list.");
    return false; // User ID not found.
}


   
   

    


}
