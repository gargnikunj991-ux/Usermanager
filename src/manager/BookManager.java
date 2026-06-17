package manager;

import database.BookDAO;
import database.DatabaseConnection;
import java.sql.Connection;
import java.util.Scanner;
import model.Book;

interface BookOperation {

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
    private UserManager userManager;
    private MemberManager memberManager;

    public BookManager(UserManager userManager, MemberManager memberManager) {
        this.userManager = userManager;
        this.memberManager = memberManager;

        Connection connection
                = DatabaseConnection.getConnection();

        dao = new BookDAO(connection);

    }
    private BookDAO dao;

    public void addBook() {
        userManager.getCurrentUser();
        if (userManager.getCurrentUser() == null) {
            System.out.println("Please login first");
            return;
        }
        System.out.println("Enter the title of book");
        String title = sc.nextLine();
        while (title.isEmpty()) {
            System.out.println("The title is book is worng ");
            System.out.println("Renter the title");
            title = sc.nextLine();
        }
        System.out.println("Enter the name of author");
        String author = sc.nextLine();
        while (author.isEmpty()) {
            System.out.println("The name of auhtor of book is wrong");
            System.out.println("Enter the name again");
            author = sc.nextLine();
        }
        System.out.println("Enter the BookId of the book ");
        String bookId = sc.nextLine();
        while (bookId.isEmpty()) {
            System.out.println("BookId is wrong ");
            bookId = sc.nextLine();
        }
        if (dao.bookExists(bookId)) {
            System.out.println("BookId is already exists");
            return;
        }
        Book book = new Book(title, author, bookId);
        if (dao.addBook(book)) {
            System.out.println("Book is succesfully added ");
        } else {
            System.out.println("Book could not be added ");
        }

    }

    public void serachBook() {
        System.out.println("Enter the bookId to serach");
        String bookId = sc.nextLine();
        dao.searchBook(bookId);
    }

    public void displayBook() {

        if (!dao.hasBooks()) {
            System.out.println("There is no Book in system");
            return;
        }

        dao.displayBook();
    }

    public void borrowBook() {
        userManager.getCurrentUser();
        if (userManager.getCurrentUser() == null) {
            System.out.println("Please login first");
            return;
        }
        System.out.println("Enter memberId");
        String memberId = sc.nextLine();
        if (!memberManager.memberExists(memberId)) {
            System.out.println("Member does not exist");
            return;
        }

        System.out.println("Enter Book ID:");
        String bookId = sc.nextLine();

        dao.borrowBook(
                bookId,
                memberId,
                userManager.getCurrentUser().getName()
        );
    }

    public void returnBook() {
        userManager.getCurrentUser();
        if (userManager.getCurrentUser() == null) {
            System.out.println("Please login first");
            return;
        }
        System.out.println("Enter the BookId");
        String bookId = sc.nextLine();

        dao.returnBook(bookId);
    }

    public void displayBorrowBook() {
        if (!dao.hasBooks()) {
            System.out.println("There is no Book in system");
            return;
        }

        dao.displayBorrowBook();
    }

    public void displayAvailableBook() {
        if (!dao.hasBooks()) {
            System.out.println("There is no Book in system");
            return;
        }

        dao.displayAvailableBook();
    }

    public void displayBorrowRecords() {
        dao.displayBorrowRecords();
    }
}
