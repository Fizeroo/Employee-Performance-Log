public class Evaluation {
    private String employeeId;
    private String name;
    private double rating; // Performance rating (1-5)
    private String feedback;

    public Evaluation(String employeeId, String name, double rating, String feedback) {
        this.employeeId = employeeId;
        this.name = name;
        this.rating = rating;
        this.feedback = feedback;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name +
                ", Rating: " + rating + ", Feedback: " + feedback;
    }
}