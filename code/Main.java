import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EvaluationManager manager = new EvaluationManager();

        while (true) {
            System.out.println("\n--- Employee Performance Evaluation System ---");
            System.out.println("1. Add Evaluation");
            System.out.println("2. View Evaluations");
            System.out.println("3. Update Evaluation");
            System.out.println("4. Delete Evaluation");
            System.out.println("5. Generate Report");
            System.out.println("6. Export to JSON");
            System.out.println("7. Import from JSON");
            System.out.println("8. Exit");

            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manager.addEvaluation(scanner);
                    break;
                case 2:
                    manager.viewEvaluations();
                    break;
                case 3:
                    manager.updateEvaluation(scanner);
                    break;
                case 4:
                    manager.deleteEvaluation(scanner);
                    break;
                case 5:
                    manager.generateReport();
                    break;
                case 6:
                    manager.exportToJson();
                    break;
                case 7:
                    manager.importFromJson();
                    break;
                case 8:
                    System.out.println("Exiting...");

                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}