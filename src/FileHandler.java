import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {

    public static void readAllFiles() throws IOException {
        loadStocks();
        loadCustomers();
        loadUsers();

    }

    public static void loadCustomers() {
    }

    public static Map<String, String> loadUsers() throws IOException {
        Map<String, String> usersLoginInformation = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/TXT Files/users.txt"));
            br.readLine();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                //System.out.println(line);
                String[] parts = line.split("\\s+");
                String usernameTxt = parts[0];
                String passwordTxt = parts[1];
                usersLoginInformation.put(usernameTxt, passwordTxt);
            }
            return usersLoginInformation;
        } catch  (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void loadStocks() {

    }

    public static void writeToUsers(String username, String password) throws IOException {
        FileWriter fileWriter = new FileWriter("src/TXT Files/users.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(username+"\t"+password+"\n");
        bufferedWriter.close();
    }
}