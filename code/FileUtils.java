import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;

public class FileUtils {
    private static final String FILE_PATH = "data/evaluations.txt";

    public static void saveEvaluations(List<Evaluation> evaluations) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Evaluation eval : evaluations) {
                writer.write(eval.getEmployeeId() + "," + eval.getName() + "," + eval.getRating() + "," + eval.getFeedback());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving evaluations: " + e.getMessage());
        }
    }

    public static List<Evaluation> loadEvaluations() {
        List<Evaluation> evaluations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    evaluations.add(new Evaluation(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading evaluations: " + e.getMessage());
        }
        return evaluations;
    }

    private static final String JSON_PATH = "data/evaluations.json";

    public static void exportToJson(List<Evaluation> evaluations) {
        try (Writer writer = new FileWriter(JSON_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(evaluations, writer);
            System.out.println("Exported to evaluations.json");
        } catch (IOException e) {
            System.err.println("Error exporting to JSON: " + e.getMessage());
        }
    }

    public static void importFromJson(List<Evaluation> evaluations) {
        try (Reader reader = new FileReader(JSON_PATH)) {
            Gson gson = new Gson();
            Evaluation[] imported = gson.fromJson(reader, Evaluation[].class);
            evaluations.addAll(List.of(imported));
            System.out.println("Imported from evaluations.json");
        } catch (IOException e) {
            System.err.println("Error importing from JSON: " + e.getMessage());
        }
    }

}