/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;
import BACKENDUSER.NodeHistory;
import java.util.Date;

/**
 *
 * @author ivan
 */
public class NodeTransac {
       private String UserId;
     private int ISBN;
     private Date dueDate;
     private Date borrwDate;
     private double price;
     private String transacId;
     private String status;
     private double amt;
      private NodeHistory book; 
     NodeTransac next;
     
   public  NodeTransac(String UserId, int isbn,String transacId , Date dueDate, Date borrowDate){
         this.ISBN = isbn;
         this.UserId = UserId;
         this.borrwDate = borrowDate;
         this.transacId = transacId;
         this.dueDate = dueDate;
        next = null;
     }
   
    public  NodeTransac(String transacId, int isbn,String userId , Date dueDate, Date borrowDate,double amt , String status){
         this.ISBN = isbn;
         this.UserId = userId;
         this.borrwDate = borrowDate;
         this.transacId = transacId;
         this.dueDate = dueDate;
         this.price = amt;
         this.status = status;
        next = null;
     }
      public  NodeTransac(String transacId, int isbn,String userId , Date dueDate, Date borrowDate,double amt , String status,NodeHistory book){
         this.ISBN = isbn;
         this.UserId = userId;
         this.book = book;
         this.borrwDate = borrowDate;
         this.transacId = transacId;
         this.dueDate = dueDate;
         this.price = amt;
         this.status = status;
        next = null;
     }
       public  NodeTransac(double amt , String status){
         this.price = amt;
         this.status = status;
        next = null;
     }
     public String getUserId(){
         return UserId;
     }
     public int code(){
         return ISBN;
     }
     public String getID(){
         return transacId;
     }
     public Date getDueDate(){
         return dueDate;
     }
     public Date borrowDate(){
         return borrwDate;
     }

     public void setUserID(String id){
         this.UserId = id;
     }
     public void setTransacId(String ID){
         this.transacId = ID;
     }
     public void setISBN(int isbn){
         this.ISBN = isbn;
     }
     public void setdueDate(Date dueDate){
         this.dueDate = dueDate;
     }
     public void setBrrw(Date brrwDate){
         this.borrwDate = brrwDate;
     }
     public void setPrice(double price){
         this.price = price;
     }
     public double CalculateTotalPrice(){
//         int days = borrwDate - dueDate;
//         price *=days;
         return price;
     }
      public NodeTransac getNext() {
        return next;
    }
      public void setstatus(String status){
          this.status = status;
      }
          public String getstatus(){
          return status;
      }
      public double getPrice(){
          return price;
      }
       public void updateStatus(Date currentDate) {
        if (currentDate.after(dueDate)) {
            this.status = "Overdue";
            this.price = book.calculateOverdueFine(currentDate);
        } else {
            this.status = "Returned";
            this.price = 0;
        }
    }
     
     
}


