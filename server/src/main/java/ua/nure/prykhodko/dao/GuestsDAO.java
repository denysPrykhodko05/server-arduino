package ua.nure.prykhodko.dao;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.nure.prykhodko.entity.Guests;

public class GuestsDAO {

    public List<Guests> findAll() {

        List<Guests> guests = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arduino", "root", "root");
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                "select user_guests.id, user_guests.name, user_guests.surname, user.room, user_guests.visit_time from user_guests left join user on user_guests.visit_room = user.id");

            while (resultSet.next()) {
                guests.add(parsetoGuest(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return guests;
    }

    public boolean deleteById(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arduino", "root", "root");
            PreparedStatement stmt = dbConnection.prepareStatement("DELETE FROM user_guests WHERE id = ?");
            stmt.setInt(1, id);

            if (stmt.executeUpdate() > INTEGER_ZERO) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addGuest(Guests guests) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arduino", "root", "root");
            PreparedStatement preparedStatement = dbConnection
                .prepareStatement("INSERT INTO user_guests (name, surname, visit_room, visit_time) VALUES (?, ?, ?,? )");

            preparedStatement.setString(1,guests.getName());
            preparedStatement.setString(2,guests.getSurname());
            preparedStatement.setString(3,guests.getOwner());
            preparedStatement.setTimestamp(4,guests.getVisitTime());

            if (preparedStatement.executeUpdate() > INTEGER_ZERO) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Guests parsetoGuest(ResultSet resultSet) throws SQLException {
        Guests guests = new Guests();
        guests.setId(resultSet.getInt("id"));
        guests.setName(resultSet.getString("name"));
        guests.setSurname(resultSet.getString("surname"));
        guests.setRoom(resultSet.getInt("room"));
        guests.setVisitTime(resultSet.getTimestamp("visit_time"));
        return guests;
    }
}