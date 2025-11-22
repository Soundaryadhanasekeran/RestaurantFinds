import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainUI extends JFrame {

    public MainUI() {

        setTitle("City Guide - Restaurant Finder");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel (Input form)
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        JTextField locationField = new JTextField();
        JTextField cuisineField = new JTextField();
        JTextField ratingField = new JTextField();

        inputPanel.add(new JLabel("Location:"));
        inputPanel.add(new JLabel("Cuisine:"));
        inputPanel.add(new JLabel("Minimum Rating:"));

        inputPanel.add(locationField);
        inputPanel.add(cuisineField);
        inputPanel.add(ratingField);

        add(inputPanel, BorderLayout.NORTH);

        // Table for results
        String[] columns = {"Name", "Location", "Cuisine", "Rating", "Address"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Search button
        JButton searchBtn = new JButton("Search Restaurants");
        add(searchBtn, BorderLayout.SOUTH);

        searchBtn.addActionListener(e -> {
            String loc = locationField.getText();
            String cus = cuisineField.getText();
            double rat = ratingField.getText().isEmpty() ? 0 : Double.parseDouble(ratingField.getText());

            RestaurantDAO dao = new RestaurantDAO();
            List<Restaurant> results = dao.search(loc, cus, rat);

            model.setRowCount(0);

            for (Restaurant r : results) {
                model.addRow(new Object[]{
                        r.getName(),
                        r.getLocation(),
                        r.getCuisine(),
                        r.getRating(),
                        r.getAddress()
                });
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
