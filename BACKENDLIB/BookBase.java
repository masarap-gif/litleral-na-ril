/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDLIB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ivan
 */
 public abstract interface BookBase {
     
     static ArrayList<String> title = new ArrayList<>();
    static ArrayList<String> author = new ArrayList<>();
    static ArrayList<Integer> ISBN = new ArrayList<>();
    static ArrayList<String> availability = new ArrayList<>();
    static ArrayList<String> bookGenres = new ArrayList<>();
   public static Object[][] data = {
    {"1001", "Book One", "Author One", "Fiction", "Available"},
    {"1002", "Book Two", "Author Two", "Non-Fiction", "Checked Out"},
    {"1003", "Book Three", "Author Three", "Science", "Available"},
    {"1004", "Book Three", "Author Three", "Science", "Available"},
    {"1005", "Book Three", "Author Three", "Science", "Available"},
    {"1006", "Book Three", "Author Three", "Science", "Available"}
            
};
     public static Object [] header = {"ISBN","Title","Author","Genre","Availability"};
        public static Object [] header1 = {"ISBN","BorrowDate","DueDate","UserID"};
        
      
            
      
        public static String []genre ={ "Fiction", "Non-Fiction", "Education","All"};
  
   
    
    }
    

    

