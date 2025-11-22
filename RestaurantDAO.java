import java.sql.*;
import java.util.*;

public class RestaurantDAO {

    public List<Restaurant> search(String location, String cuisine, double rating) {
        List<Restaurant> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT name, location, cuisine, rating, address " +
                         "FROM restaurants WHERE location LIKE ? AND cuisine LIKE ? AND rating >= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + location + "%");
            ps.setString(2, "%" + cuisine + "%");
            ps.setDouble(3, rating);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Restaurant(
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("cuisine"),
                        rs.getDouble("rating"),
                        rs.getString("address")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
