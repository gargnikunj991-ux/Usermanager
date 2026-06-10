package model;
public class Book {
    private String title;
    private String author;
    private String bookId;
    private boolean available;
    private String borrowedBy;
    private String issuedBy;

    public Book(String title, String author, String bookId) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.available = true;
        this.borrowedBy = null;
        this.issuedBy = null;
    }

   public  String getTitle(){
    return  title;
   }
   public void setTitle(String title){
    this.title = title;
   }
   public String getAuthor(){
    return author;
   }
   public void setAuthor(String author){
    this.author = author;
   }
   public String getBookId(){
    return bookId;
   }
   public void setBookId(String bookId){
    this.bookId =bookId;
   }
   public boolean getAvailable(){
    return  available;
   } 
   public void setAvailable( boolean available){
    this.available = available;
   }
   public String getBorrowedby(){
    return  borrowedBy;
   }
   public void setBorrowedby(String borrowedby){
      this.borrowedBy = borrowedby;
   }
   public String getIssuedby(){
    return issuedBy;
   }
   public void setIssuedby(String  issuedby){
    this.issuedBy = issuedby;
   }
   public String toString(){
    return "Title:"+title+" Author:"+author+" BookId:"+bookId+" Available:"+available+" BorrowedBy:"+borrowedBy+" IssuedBy:"+issuedBy;
   }
}
