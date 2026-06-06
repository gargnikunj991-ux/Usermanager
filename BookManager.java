
import java.util.HashMap;
import java.util.Scanner;
interface BookOperation{
    void addBook();
    void serachBook();
    void displayBook();
    void borrowBook();
    void returnBook();
    void displayBorrowBook();
}

public class BookManager implements BookOperation {
    Scanner sc = new Scanner(System.in);
    HashMap<String , Book> books = new HashMap<>();
    private UserManager userManager;
    private MemberManager memberManager;
    public BookManager(UserManager userManager,MemberManager memberManager){
      this.userManager = userManager;
      this.memberManager = memberManager;
    }
    

   public void addBook(){
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
          books.put(bookId, book);
         System.out.println("Book is succesfully added ");
   }
   public void serachBook(){
       System.out.println("Enter the title of the book");
       String title  = sc.nextLine();
       boolean found = false;
       for(Book book : books.values()){
        if (book.getTitle().equals(title)) {
          found = true;
          System.out.println(book.getTitle());
          System.out.println("BY:"+book.getAuthor());
        }
       }
       if (!found) {
        System.out.println("book not found ");
       }
   }
   public void displayBook(){
       if (books.isEmpty()) {
        System.out.println("There is no Book in system ");
       }else{
        for(Book book:books.values()){

          System.out.println("-------------------------");
          System.out.println("Title:"+book.getTitle());
          System.out.println("Author:"+book.getAuthor());
          System.out.println("BookId:"+book.getBookId());
          System.out.println("Available:"+book.getAvailable());
          System.out.println("BorrowedBy:"+book.getBorrowedby());
          System.out.println("IssuedBy:"+book.getIssuedby());
          System.out.println("-------------------------");
        }
       }
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

   System.out.println("Book is succesfully borrowed");
   }
   public void returnBook(){
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
}
