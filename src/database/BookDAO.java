package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Book;

public class BookDAO {

    private Connection connection;

    public BookDAO(Connection connection){
        this.connection = connection;
    }
  public boolean addBook(Book book){
    String sql =
    "INSERT INTO books(book_id,title,author,available) VALUES(?,?,?,?)";

    try {
        PreparedStatement statement =
        connection.prepareStatement(sql);

        statement.setString(1, book.getBookId());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getAuthor());
        statement.setBoolean(4, book.getAvailable());

        statement.executeUpdate();

        return true;

    } catch (SQLException e) {
    
    }

    return false;
}
public boolean hasBooks() {
    String sql = "SELECT * FROM books";

    try {
        Statement statement =
            connection.createStatement();

        ResultSet result =
            statement.executeQuery(sql);

        return result.next();

    } catch(SQLException e) {
        e.printStackTrace();
    }

    return false;
}
public void searchBook(String bookId){

    String sql =
    "SELECT * FROM books WHERE book_id = ?";

    try{

        PreparedStatement statement =
        connection.prepareStatement(sql);

        statement.setString(1, bookId);

        ResultSet result =
        statement.executeQuery();

        if(result.next()){

    System.out.println(
    "Book ID: " +
    result.getString("book_id")
);

           System.out.println(
    "Title: " +
    result.getString("title")
);

System.out.println(
    "Author: " +
    result.getString("author")
);

System.out.println(
    "Available: " +
    result.getBoolean("available")
);
        }else{

            System.out.println(
                "Book not found"
            );

        }

    }catch(SQLException e){
        e.printStackTrace();
    }
}
public void displayBook(){

    String sql = "SELECT * FROM books";

    try{

        Statement statement =
        connection.createStatement();

        ResultSet result =
        statement.executeQuery(sql);

        while(result.next()){

            System.out.println(
    "Book ID: " +
    result.getString("book_id")
);
    System.out.println(
    "Title: " +
    result.getString("title")
);

System.out.println(
    "Author: " +
    result.getString("author")
);

      System.out.println(
     "Available: " +
     result.getBoolean("available")
  );
}

    }catch(SQLException e){
        e.printStackTrace();
    }
}
public void borrowBook(
    String bookId,
    String memberId,
    String issuedBy
){
    try{

        String checkSql =
        "SELECT available FROM books WHERE book_id = ?";

        PreparedStatement checkStatement =
        connection.prepareStatement(checkSql);

        checkStatement.setString(1, bookId);

        ResultSet result =
        checkStatement.executeQuery();

        if(!result.next()){
            System.out.println("Book not found");
            return;
        }

        boolean available =
        result.getBoolean("available");

        if(!available){
            System.out.println("Book already borrowed");
            return;
        }

        String updateSql =
        "UPDATE books SET available = false WHERE book_id = ?";

        PreparedStatement updateStatement =
        connection.prepareStatement(updateSql);

        updateStatement.setString(1, bookId);

        updateStatement.executeUpdate();

        System.out.println("Book borrowed successfully");
        String recordSql =
"INSERT INTO borrow_records(book_id, member_id, issued_by, borrow_date) VALUES(?,?,?,NOW())";

       PreparedStatement recordStatement =
connection.prepareStatement(recordSql);

recordStatement.setString(1, bookId);
recordStatement.setString(2, memberId);
recordStatement.setString(3, issuedBy);

recordStatement.executeUpdate();

    }catch(SQLException e){
        e.printStackTrace();
    }
}
public boolean bookExists(String bookId){

    String sql =
    "SELECT * FROM books WHERE book_id = ?";

    try{

        PreparedStatement statement =
        connection.prepareStatement(sql);

        statement.setString(1, bookId);

        ResultSet result =
        statement.executeQuery();

        return result.next();

    }catch(SQLException e){

    }

    return false;
}
public void returnBook(String bookId){

    try{

        String checkSql =
        "SELECT available FROM books WHERE book_id = ?";

        PreparedStatement checkStatement =
        connection.prepareStatement(checkSql);

        checkStatement.setString(1, bookId);

        ResultSet result =
        checkStatement.executeQuery();

        if(!result.next()){
            System.out.println("Book not found");
            return;
        }

        boolean available =
        result.getBoolean("available");

        if(available){
            System.out.println("Book was never borrowed");
            return;
        }

        String updateBookSql =
        "UPDATE books SET available = true WHERE book_id = ?";

        PreparedStatement updateBook =
        connection.prepareStatement(updateBookSql);

        updateBook.setString(1, bookId);

        updateBook.executeUpdate();

        String updateRecordSql =
        "UPDATE borrow_records " +
        "SET return_date = NOW() " +
        "WHERE book_id = ? AND return_date IS NULL";

        PreparedStatement updateRecord =
        connection.prepareStatement(updateRecordSql);

        updateRecord.setString(1, bookId);

        updateRecord.executeUpdate();

        System.out.println("Book returned successfully");

    }catch(SQLException e){
        e.printStackTrace();
    }
}public void displayBorrowBook(){

    String sql = "SELECT * FROM books WHERE available = false";
    try{

        Statement statement =
        connection.createStatement();

        ResultSet result =
        statement.executeQuery(sql);

        while(result.next()){
System.out.println(
    "Book ID: " +
    result.getString("book_id")
);
            System.out.println(
    "Title: " +
    result.getString("title")
);

System.out.println(
    "Author: " +
    result.getString("author")
);

System.out.println(
    "Available: " +
    result.getBoolean("available")
);

            System.out.println("----------------");
        }

    }catch(SQLException e){
        e.printStackTrace();
    }
} 
public void displayAvailableBook(){

    String sql = "SELECT * FROM books WHERE available = true";

    try{

        Statement statement =
        connection.createStatement();

        ResultSet result =
        statement.executeQuery(sql);

        while(result.next()){

            System.out.println(
    "Book ID: " +
    result.getString("book_id")
);

System.out.println(
    "Title: " +
    result.getString("title")
);

System.out.println(
    "Author: " +
    result.getString("author")
);

System.out.println(
    "Available: " +
    result.getBoolean("available")
);}
    }catch(SQLException e){
        e.printStackTrace();
    }
}
public void displayBorrowRecords(){

    String sql =
    "SELECT * FROM borrow_records";

    try{

        Statement statement =
        connection.createStatement();

        ResultSet result =
        statement.executeQuery(sql);

        while(result.next()){

            System.out.println(
                "Record ID: " +
                result.getInt("id")
            );

            System.out.println(
                "Book ID: " +
                result.getString("book_id")
            );

            System.out.println(
                "Member ID: " +
                result.getString("member_id")
            );

            System.out.println(
                "Issued By: " +
                result.getString("issued_by")
            );

            System.out.println(
                "Borrow Date: " +
                result.getTimestamp("borrow_date")
            );

            System.out.println(
                "Return Date: " +
                result.getTimestamp("return_date")
            );

            System.out.println("----------------");
        }

    }catch(SQLException e){
        e.printStackTrace();
    }
} 

}