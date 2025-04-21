import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvaluationManager {
    private List<Evaluation> evaluations;

    public EvaluationManager() {
        evaluations = FileUtils.loadEvaluations(); // Загружаем данные из файла при запуске
    }



    public void addEvaluation(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String employeeId = readNonEmptyInput(scanner, "Employee ID cannot be empty. Please try again.");

        // Проверка на уникальность Employee ID
        for (Evaluation eval : evaluations) {
            if (eval.getEmployeeId().equals(employeeId)) {
                System.out.println("Error: Employee ID already exists. Please use a unique ID.");
                return; // Прерываем выполнение метода
            }
        }

        System.out.print("Enter Employee Name: ");
        String name = readNonEmptyInput(scanner, "Name cannot be empty. Please try again.");

        double rating = readValidRating(scanner);

        System.out.print("Enter Feedback: ");
        String feedback = readNonEmptyInput(scanner, "Feedback cannot be empty. Please try again.");

        evaluations.add(new Evaluation(employeeId, name, rating, feedback));
        FileUtils.saveEvaluations(evaluations);
        System.out.println("Evaluation added successfully!");
    }

    public void viewEvaluations() {
        if (evaluations.isEmpty()) {
            System.out.println("No evaluations found.");
        } else {
            for (Evaluation eval : evaluations) {
                System.out.println(eval);
            }
        }
    }

    public void updateEvaluation(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        String employeeId = readNonEmptyInput(scanner, "Employee ID cannot be empty. Please try again.");

        Evaluation evalToUpdate = null;
        for (Evaluation eval : evaluations) {
            if (eval.getEmployeeId().equals(employeeId)) {
                evalToUpdate = eval;
                break;
            }
        }

        if (evalToUpdate == null) {
            System.out.println("Employee ID not found.");
            return;
        }

        double newRating = readValidRating(scanner);

        System.out.print("Enter new Feedback: ");
        String newFeedback = readNonEmptyInput(scanner, "Feedback cannot be empty. Please try again.");

        evalToUpdate.setRating(newRating);
        evalToUpdate.setFeedback(newFeedback);
        FileUtils.saveEvaluations(evaluations);
        System.out.println("Evaluation updated successfully!");
    }

    public void deleteEvaluation(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        String employeeId = readNonEmptyInput(scanner, "Employee ID cannot be empty. Please try again.");

        Evaluation evalToRemove = null;
        for (Evaluation eval : evaluations) {
            if (eval.getEmployeeId().equals(employeeId)) {
                evalToRemove = eval;
                break;
            }
        }

        if (evalToRemove != null) {
            evaluations.remove(evalToRemove);
            FileUtils.saveEvaluations(evaluations);
            System.out.println("Evaluation deleted successfully!");
        } else {
            System.out.println("Employee ID not found.");
        }
    }


    public void generateReport() {
        if (evaluations.isEmpty()) {
            System.out.println("No evaluations to generate a report.");
        } else {
            double totalRating = 0;
            for (Evaluation eval : evaluations) {
                totalRating += eval.getRating();
            }
            double averageRating = totalRating / evaluations.size();
            System.out.println("Total Evaluations: " + evaluations.size());
            System.out.println("Average Rating: " + String.format("%.2f", averageRating));
        }
    }

    // Вспомогательные методы

    private String readNonEmptyInput(Scanner scanner, String errorMessage) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println(errorMessage);
        }
        return input;
    }

    private double readValidRating(Scanner scanner) {
        double rating = 0;
        while (true) {
            System.out.print("Enter Performance Rating (1-5): ");
            String input = scanner.nextLine().trim();
            try {
                rating = Double.parseDouble(input);
                if (rating >= 1 && rating <= 5) {
                    break;
                } else {
                    System.out.println("Error: Rating must be between 1 and 5. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Rating must be a number between 1 and 5. Please try again.");
            }
        }
        return rating;
    }
}