package restaurantfind;
import java.sql.*;
import java.util.Scanner;
public class guide {
	
	    // Database credentials
	    static final String DB_URL = "jdbc:mysql://localhost:3306/CityGuide1"; // Replace with your database name
	    static final String USER = "root"; // Replace with your MySQL username
	    static final String PASS = ""; // Replace with your MySQL password

	    public static void main (String[] args) {
	        // Ensure resources like Scanner and database connections are properly closed
	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	             Scanner scanner = new Scanner(System.in)) {

	            System.out.println("Welcome to the City Guide App!");
	            
	            // Get cuisine type input
	            System.out.print("Cuisine type (e.g., Japanese, Vegetarian): ");
	            String cuisine = scanner.nextLine().trim();

	            // Validate and get minimum rating input
	            double minRating = -1;
	            while (minRating < 0 || minRating > 5) {
	                System.out.print("Minimum rating (0.0 to 5.0): ");
	                if (scanner.hasNextDouble()) {
	                    minRating = scanner.nextDouble();
	                    if (minRating < 0 || minRating > 5) {
	                        System.out.println("Please enter a valid rating between 0.0 and 5.0.");
	                    }
	                } else {
	                    System.out.println("Please enter a numeric value for the rating.");
	                    scanner.next(); // Clear invalid input
	                }
	            }

	            System.out.println("Fetching recommendations...");
	            getRecommendations(conn, cuisine, minRating);

	        } catch (SQLException e) {
	            System.out.println("Database connection failed: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("An unexpected error occurred: " + e.getMessage());
	        }
	    }

	    private static void getRecommendations(Connection conn, String cuisine, double minRating) {
	        String query = "SELECT name, cuisine_type, address, rating FROM Restaurants WHERE cuisine_type = ? AND rating >= ?";

	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setString(1, cuisine);
	            pstmt.setDouble(2, minRating);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (!rs.isBeforeFirst()) { // Check if ResultSet is empty
	                    System.out.println("No restaurants found for your preferences.");
	                } else {
	                    System.out.println("\nRecommended Restaurants:");
	                    while (rs.next()) {
	                        System.out.println("Name: " + rs.getString("name"));
	                        System.out.println("Cuisine: " + rs.getString("cuisine_type"));
	                        System.out.println("Address: " + rs.getString("address"));
	                        System.out.println("Rating: " + rs.getDouble("rating"));
	                        System.out.println("--------------------------");
	                    }
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Error fetching recommendations: " + e.getMessage());
	        }
	    }
	}

	
		   