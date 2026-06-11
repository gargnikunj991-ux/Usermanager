package database;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import model.Book;

public class BookDAO {

    private Connection connection;

    public BookDAO(Connection connection){
        this.connection = connection;
    }
  public void addBook(Book book){
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
System.out.println("Book Added");
} catch (SQLException e) {
    e.printStackTrace();
}
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
                result.getString("book_id")
            );

            System.out.println(
                result.getString("title")
            );

            System.out.println(
                result.getString("author")
            );

            System.out.println(
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
                result.getString("book_id")
            );

            System.out.println(
                result.getString("title")
            );

            System.out.println(
                result.getString("author")
            );

            System.out.println(
                result.getBoolean("available")
            );

            System.out.println("----------------");
        }

    }catch(SQLException e){
        e.printStackTrace();
    }
} 

}