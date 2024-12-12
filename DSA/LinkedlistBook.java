/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author ivan
 */
public class LinkedlistBook {
     private Random random = new Random();
       public  static NodeBook head;
    private int size;

    // Constructor to initialize and add some default books
    public LinkedlistBook() {
        head = null;
        size = 0;
   addBooks();

        
    }
    public NodeBook getBookByIsbn(int isbn) {
      NodeBook current = head; // Start from the head of the linked list
    while (current != null) { // Traverse until the end of the list
        if (current.getISBN() == isbn) {
            return current; // Return the node if ISBN matches
        }
        current = current.getNext(); // Move to the next node
    }
    return null; // Return null if no book is found with the given ISBN
}  // Return null if book with the given ISBN is not found
    

    // Retrieve all books
  public ArrayList<NodeBook> getAllBooks() {
    ArrayList<NodeBook> books = new ArrayList<>(); // Create an ArrayList to store books
    NodeBook current = head; // Start from the head of the linked list
    while (current != null) { // Traverse the linked list
        books.add(current); // Add each node to the ArrayList
        current = current.getNext(); // Move to the next node
    }
    return books; // Return the populated ArrayList
}

    // Method to add a book
    public void addBook(String title, String author, int ISBN, String genre, int bookId, int quanity,boolean isAvailable,Date yrPublisheed,int shelfNum) {
        NodeBook newNode = new NodeBook(title, author, ISBN, genre, isAvailable,bookId,quanity,yrPublisheed,shelfNum);
        
        int currentYear = new Date().getYear() + 1900;  // Adjust for the Date class (years are offset by 1900)
    int bookYear = yrPublisheed.getYear() + 1900;
    int bookAge = currentYear - bookYear;

    // Check if the book is new or old
    if (bookAge < 5) {
        newNode.setStatus("New");
    } else if (bookAge >= 5 && bookAge < 10) {
        newNode.setStatus("Old");
    }

        if (head == null) {
            head = newNode;
        } else {
            NodeBook current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
     public  String getGenre(int ISBN){
     NodeBook  current = head;
     while(current!=null){
         if (current.getISBN() == ISBN){
             return current.getGenre();
         }
         current = current.getNext();
     }
     return null;
 }
    public void addBooks() {
    addBook("The Great Gatsby", "F. Scott Fitzgerald", 1001, "Fiction", 23452, 1, true, new Date(1925 - 1900, 0, 1), 1); // The Date constructor takes (year, month, day)
        addBook("1984", "George Orwell", 1002, "Fiction", 388423, 1, true, new Date(1949 - 1900, 0, 1), 2);
        addBook("Introduction to Algorithms", "Thomas H. Cormen", 1003, "Education", 10100, 3, true, new Date(2009 - 1900, 0, 1), 3);
        addBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 1004, "Non-Fiction", 10923, 2, true, new Date(2011 - 1900, 0, 1), 4);
        addBook("Educated", "Tara Westover", 1005, "Non-Fiction", 23221, 2, true, new Date(2018 - 1900, 0, 1), 5);
   
}
     public NodeBook getAtIndex(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Invalid index.");
    }
    NodeBook current = head;
    for (int i = 0; i < index; i++) {
        current = current.getNext();
    }
    return current;
}
    

  
    // Method to remove a book by ISBN
    public void removeBook(int ISBN) {
        if (head == null) return; // List is empty

        // Special case: the book to remove is the head
        if (head.getISBN() == ISBN) {
            head = head.next;
            size--;
            return;
        }

        NodeBook current = head;
        while (current.next != null && current.next.getISBN() != ISBN) {
            current = current.next;
        }

        // If the book is found, remove it
        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }
     public Object[][] getFilteredBooksByGenre(String genre) {
    ArrayList<Object[]> filteredBooks = new ArrayList<>();

    NodeBook current = head;

    // If "All" is selected, return all books
    if (genre.equals("All")) {
        return getBookData(); // Return all books
    }

    // Loop through the list and add books that match the genre
    while (current != null) {
        if (current.getGenre().equalsIgnoreCase(genre)) {
            filteredBooks.add(new Object[]{
                current.getTitle(),
                current.getAuthor(),
                current.getISBN(),
                current.getGenre(),
                current.getIsAvailable() ? "Available" : "Checked Out"
            });
        }
        current = current.getNext();
    }

    // Convert ArrayList to Object[][]
    Object[][] filteredArray = new Object[filteredBooks.size()][5];
    return filteredBooks.toArray(filteredArray);
}

   


 

    // Convert the list to a 2D array for displaying in a table
    public Object[][] getBookData() {
    // First, count the number of nodes in the linked list
    int nodeCount = 0;
    NodeBook current = head;
    while (current != null) {
        nodeCount++;
        current = current.next;
    }

    // Create the array based on the actual number of nodes
    Object[][] bookData = new Object[nodeCount][9];

    // Populate the array
    current = head; 
    int index = 0;
    while (current != null) {
        bookData[index][0] = current.getTitle();
        bookData[index][1] = current.getAuthor();
        bookData[index][2] = current.getISBN();
        bookData[index][3] = current.getGenre();
        bookData[index][4] = current.getIsAvailable() ? "Available" : "Not Available"; // Use descriptive text
        bookData[index][5] = current.getBookId();
        bookData[index][6] = current.getQuan();
        bookData[index][7] = current.getStatus();
         bookData[index][8] = current.shelft();

        current = current.next;
        index++;
    }

    return bookData;
}

    // Update the availability of a book by ISBN
    public static void updateAvailability(int ISBN, boolean isAvailable) {
        NodeBook current = LinkedlistBook.head;
        while (current != null) {
            if (current.getISBN() == ISBN) {
                current.setIsAvailable(isAvailable);
                System.out.println("Updated ISBN: " + ISBN + " | Available: " + current.getIsAvailable());
                return;
            }
            current = current.next;
        }
        System.out.println("Book with ISBN " + ISBN + " not found.");
    }

    
    public  NodeBook LinearSeach(int ISBN) {
        NodeBook current = head;
        while (current != null) {
            if (current.getISBN() == ISBN) {
                return current;
            }
            
            current = current.next;
        }
        return null;  // Book not found
    }
    public NodeBook linearSearchByTitle(String title) {
    NodeBook current = head;
    while (current != null) {
        if (current.getTitle().equalsIgnoreCase(title)) {  // Compare titles
            return current;  // Book found
        }
        
        current = current.next;
    }
    return null;  // Book not found
}
     public int generateUniqueBookID() {
    int lastID = 0;
    NodeBook current = head;  // Assuming head is the start of your linked list
    while (current != null) {
        lastID = Math.max(lastID, current.getBookId());
        current = current.next;
    }
    return lastID + 1;
}

    // Method to add a new book ID to the linked list
    private void add(int data) {
        NodeBook newNode = new NodeBook(data);
        if (head == null) {
            head = newNode;
            return;
        }

        NodeBook current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Method to check if the linked list contains a specific book ID
    private boolean contains(int data) {
        NodeBook current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Delete a book at a specific position
 

    // Method to convert linked list to array for other operations
   public NodeBook[] toArray() {
    // First, find the size of the LinkedList to create the array
    int size = 0;
    NodeBook current = head;  // head is the first book in the list

    // Traverse the linked list to calculate its size
    while (current != null) {
        size++;
        current = current.getNext();
    }

    // Create an array to hold the books
    NodeBook[] booksArray = new NodeBook[size];

    // Now, fill the array with data from the LinkedList
    current = head;  // Start again from the head
    int index = 0;
    while (current != null) {
        booksArray[index] = current;  // Copy the book node into the array
        index++;
        current = current.getNext();  // Move to the next node in the linked list
    }

    return booksArray;
}
    // method to sort the book in the table based on genre
  public static void quickSortByTitle(NodeBook[] booksArray, int low, int high) {
    if (low >= high) {
        return;
    }

    NodeBook pivot = booksArray[high]; // Use the last element as the pivot
    int leftPointer = low;
    int rightPointer = high;

    while (leftPointer < rightPointer) {
        // Move left pointer right as long as title is less than or equal to pivot title
        while (booksArray[leftPointer].getTitle().compareTo(pivot.getTitle()) <= 0 && leftPointer < rightPointer) {
            leftPointer++;
        }
         // Move right pointer left
        while (booksArray[rightPointer].getTitle().compareTo(pivot.getTitle()) >= 0 && leftPointer < rightPointer) {
            rightPointer--;
        }
        //swap left & right pointer if kailangan
        swap(booksArray, leftPointer, rightPointer);
    }
    //swap pivot sa correct pos
    swap(booksArray, leftPointer, high);

    // Recursively sort the partitions
    quickSortByTitle(booksArray, low, leftPointer - 1);
    quickSortByTitle(booksArray, leftPointer + 1, high);
}
        // Swap method for NodeBook[]
     private static void swap(NodeBook[] arr, int i, int j) {
         NodeBook temp = arr[i];
         arr[i] = arr[j];
         arr[j] = temp;
     }
   
    // method to sort the book in the table based on title
    public Object[][] sortByTitle() {
    // Convert to array
    NodeBook[] booksArray = toArray();

    // Sort the array using quicksort, sorting by genre
    quickSortByTitle(booksArray, 0, booksArray.length - 1);

    // Update the LinkedList (optional, if you want to keep it sorted)
    head = null;
    size = 0;
    for (NodeBook book : booksArray) {
        addBook(book.getTitle(), book.getAuthor(), book.getISBN(), book.getGenre(),book.getBookId(),book.getQuan(), book.getIsAvailable(), book.getYr(),book.shelft());
    }

    // Return sorted data for the table
    Object[][] sortedBooks = new Object[booksArray.length][5];
    for (int i = 0; i < booksArray.length; i++) {
        sortedBooks[i][0] = booksArray[i].getTitle();
        sortedBooks[i][1] = booksArray[i].getAuthor();
        sortedBooks[i][2] = booksArray[i].getISBN();
        sortedBooks[i][3] = booksArray[i].getGenre();
        sortedBooks[i][4] = booksArray[i].getIsAvailable();
    }

    return sortedBooks;
}
         

  public void add(NodeBook book) {
    if (head == null) {
        head = book;  // Set the first book as head if the list is empty
    } else {
        NodeBook current = head;
        // Traverse to the last node in the list
        while (current.getNext() != null) {
            current = current.getNext();
        }
        // Set the next node of the last node to the new book
        current.setNext(book);
    }
}
 

    
         public boolean updateBook(int isbn, String title, String author, String genre,int quan) {
    // Iterate through the linked list to find the book by ISBN and update its details
    NodeBook current = head;
    while (current != null) {
        if (current.getISBN() == isbn) {
            current.setTitle(title);
            current.setAuthor(author);
            current.setGenre(genre);
            current.getQuantity(quan);
            return true; // Book updated successfully
        }
        current = current.getNext();
    }
    return false; // Book not found
}
    
  
}
