package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Member;
public class MemberDAO {

    private Connection connection;

    public MemberDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addMember(Member member) {
        String sql
                = "INSERT INTO members(member_name,member_id) VALUES(?,?)";

        try {
            PreparedStatement statement
                    = connection.prepareStatement(sql);

            statement.setString(1, member.getMemberName());
            statement.setString(2, member.getMemberId());
            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

        }

        return false;
    }
    public boolean memberExists(String memberId) {

        String sql
                = "SELECT * FROM members WHERE member_id = ?";

        try {

            PreparedStatement statement
                    = connection.prepareStatement(sql);

            statement.setString(1, memberId);

            ResultSet result
                    = statement.executeQuery();

            return result.next();

        } catch (SQLException e) {

        }

        return false;
    }
        public void searchMember(String memberId) {

        String sql
                = "SELECT * FROM members WHERE member_id = ?";

        try {

            PreparedStatement statement
                    = connection.prepareStatement(sql);

            statement.setString(1, memberId);

            ResultSet result
                    = statement.executeQuery();

            if (result.next()) {

                System.out.println(
                        "Member ID: "
                        + result.getString("member_id")
                );

                System.out.println(
                        "Member Name  "
                        + result.getString("member_name")
                );

                
            } else {

                System.out.println(
                        "member not found"
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void displayMember() {

        String sql = "SELECT * FROM members";

        try {

            Statement statement
                    = connection.createStatement();

            ResultSet result
                    = statement.executeQuery(sql);

            while (result.next()) {

                System.out.println(
                        "Member ID: "
                        + result.getString("member_id")
                );
                System.out.println(
                        "Member Name: "
                        + result.getString("member_name")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean hasMembers() {
        String sql = "SELECT * FROM members";

        try {
            Statement statement
                    = connection.createStatement();

            ResultSet result
                    = statement.executeQuery(sql);

            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



}
