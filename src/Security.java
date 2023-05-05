
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Security 
{
    public static void main(String args[]) throws IOException{
        createAccount("eli", "000");
    }

    public static boolean login(String username, String password) throws IOException {
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\17322\\HighSchool\\TradingSystem\\src\\TXT Files\\Users.txt"));
            br.readLine();
            for (String line = br.readLine(); line != null; line = br.readLine())
            {
                //System.out.println(line);
                String[] parts = line.split("\\s+");
                String usernameTxt = parts[0];
                String passwordTxt = parts[1];
                // do stuff to file here
                if(usernameTxt.equals(username))
                {
                    if(passwordTxt.equals(password))
                    {
                        System.out.println("Successfully logged in, opening your client window");
                        return true;
                    }
                    else{
                        System.out.println("Wrong password");
                        return false;
                    }
                } 
                
                
            }
            
            System.out.println("This user does not exist, please create an account first");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public static boolean createAccount(String username, String password) throws IOException {
        //Read in usernames and passwords from text file
        if (login(username, password) == true) {
            System.out.println("user exists already");
            return false;
        }
        else{
            FileWriter fileWriter = new FileWriter("C:\\Users\\17322\\HighSchool\\TradingSystem\\src\\TXT Files\\Users.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(username+"\t"+password+"\n");
            bufferedWriter.close();

            return true;
        }
    }

    private static void setCustomer(Customer c) {
        DisplayFacade.setCustomer(c);
    }
}