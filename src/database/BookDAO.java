package database;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Book;

import java.sql.Connection;

public class BookDAO {

    private Connection connection;

    public BookDAO(Connection connection){
        this.connection = connection;
    }
    public void addBook(Book book){

    String sql =
    "INSERT INTO books(book_id,title,author,available) VALUES(?,?,?,?)";

    try{

        PreparedStatement statement =
        connection.prepareStatement(sql);

        statement.setString(1, book.getBookId());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getAuthor());
        statement.setBoolean(4, book.getAvailable());

        statement.executeUpdate();

        System.out.println("Book Added");

    }catch(SQLException e){
        e.printStackTrace();
    }
}
}
