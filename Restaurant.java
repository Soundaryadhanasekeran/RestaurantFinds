public class Restaurant {

    private String name;
    private String location;
    private String cuisine;
    private double rating;
    private String address;

    public Restaurant(String name, String location, String cuisine, double rating, String address) {
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.rating = rating;
        this.address = address;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getCuisine() { return cuisine; }
    public double getRating() { return rating; }
    public String getAddress() { return address; }
}
