package manager;
import model.Book;
import java.util.HashMap;
import java.util.Scanner;

import database.BookDAO;
import database.DatabaseConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

interface BookStorage {

    void saveBooks(HashMap<String, Book> books);

    void loadBooks(HashMap<String, Book> books);
}
class BookFileStorage implements BookStorage {

    public void saveBooks(HashMap<String, Book> books) {
        try {
            FileWriter writer = new FileWriter("books.txt");
            for (Book book : books.values()) {
                writer.write(book.getTitle() + "," +book.getAuthor() + "," + book.getBookId() + "," +book.getAvailable()+","+book.getBorrowedby()+","+book.getIssuedby());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File cannot update");
        }

    }

    public void loadBooks(HashMap<String, Book> Books) {
        try {
            File file = new File("books.txt");

            if (!file.exists()) {

                return;
            }
            BufferedReader reader
                    = new BufferedReader(new FileReader("books.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                 String title = data[0].trim();
                 String author = data[1].trim();
                 String bookId = data[2].trim();

                 boolean available = Boolean.parseBoolean(data[3].trim());

                 String borrowedBy = data[4].trim();
                 String issuedBy = data[5].trim();

                 Book book = new Book(title, author, bookId);

                 book.setAvailable(available);
                 book.setBorrowedby(borrowedBy);
                 book.setIssuedby(issuedBy);
 
                 Books.put(bookId, book);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("error");
        }

    }

}

interface BookOperation{
    void addBook();
    void serachBook();
    void displayBook();
    void borrowBook();
    void returnBook();
    void displayBorrowBook();
    void displayAvailableBook();
}

public class BookManager implements BookOperation {
    Scanner sc = new Scanner(System.in);
    HashMap<String , Book> books = new HashMap<>();
    private UserManager userManager;
    private MemberManager memberManager;
    public BookManager(UserManager userManager,MemberManager memberManager){
      this.userManager = userManager;
      this.memberManager = memberManager;

              Connection connection =
        DatabaseConnection.getConnection();

     dao = new BookDAO(connection);

    }
    private BookDAO dao;

    BookStorage storage = new BookFileStorage();
    public void loadData(){
      storage.loadBooks(books);
    }     

   public void addBook(){
     userManager.getCurrentUser();
    if(userManager.getCurrentUser() == null){
      System.out.println("Please login first");
      return;
    }
      System.out.println("Enter the title of book");
      String title = sc.nextLine();
      while(title.isEmpty()){
        System.out.println("The title is book is worng ");
        System.out.println("Renter the title");
        title = sc.nextLine();
      }
      System.out.println("Enter the name of author");
      String author = sc.nextLine();
      while(author.isEmpty()){
        System.out.println("The name of auhtor of book is wrong");
        System.out.println("Enter the name again");
        author = sc.nextLine();
      }
      System.out.println("Enter the BookId of the book ");
      String bookId= sc.nextLine();
      while(bookId.isEmpty()){
        System.out.println("BookId is wrong ");
        bookId = sc.nextLine();
      }
      while(books.containsKey(bookId)){
        System.out.println("BookId already exist ");
        bookId = sc.nextLine();
      }
         Book book = new Book(title, author, bookId);
          dao.addBook(book);
         System.out.println("Book is succesfully added ");        
   }
  
   public void serachBook(){
       System.out.println("Enter the bookId to serach");
       String bookId = sc.nextLine();
       dao.searchBook(bookId);
   }
   public void displayBook() {

    if(!dao.hasBooks()) {
        System.out.println("There is no Book in system");
        return;
    }

    dao.displayBook();
}
   public void borrowBook(){
    userManager.getCurrentUser();
    if(userManager.getCurrentUser() == null){
      System.out.println("Please login first");
      return;
    }
    System.out.println("Enter memberId");
    String memberId= sc.nextLine();
   if (!memberManager.memberExists(memberId)) {
    System.out.println("Member does not exist");
    return;
    }
   System.out.println("Enter BookId");
   String bookId = sc.nextLine();
   if (!books.containsKey(bookId)) {
     System.out.println("Book does not exist");
     return ;
   }
   Book book = books.get(bookId);
   if (!book.getAvailable()) {
    System.out.println("Book is already Borrowed");
    return;
   }
   book.setAvailable(false);
   book.setBorrowedby(memberId);
   book.setIssuedby(userManager.getCurrentUser().getName());

   storage.saveBooks(books);
   System.out.println("Book is succesfully borrowed");
   }
   public void returnBook(){
     userManager.getCurrentUser();
    if(userManager.getCurrentUser() == null){
      System.out.println("Please login first");
      return;
    }
    System.out.println("Enter the BookId");
    String bookId = sc.nextLine();
    if(!books.containsKey(bookId)){
      System.out.println("Books does not exist");
      return;
    }
     Book book = books.get(bookId);
   if(book.getAvailable()){
      System.out.println(" book was never borrowed");
      return;   
   } 
   book.setAvailable(true);
   book.setBorrowedby(null);
   book.setIssuedby(null);
   storage.saveBooks(books);
   System.out.println("Book is succesfully return");
   }
   public void displayBorrowBook(){
    for(Book book : books.values()){
      if(!book.getAvailable()){
          System.out.println("-------------------------");
          System.out.println("Title:"+book.getTitle());
          System.out.println("Author:"+book.getAuthor());
          System.out.println("BookId:"+book.getBookId());
          System.out.println("BorrowedBy:"+book.getBorrowedby());
          System.out.println("IssuedBy:"+book.getIssuedby());
          System.out.println("-------------------------");
      }
    }
   }
   public void displayAvailableBook(){
    for(Book book : books.values()){
      if(book.getAvailable()){
          System.out.println("-------------------------");
          System.out.println("Title:"+book.getTitle());
          System.out.println("Author:"+book.getAuthor());
          System.out.println("BookId:"+book.getBookId());
          System.out.println("-------------------------");
      }
    }
   }
}
