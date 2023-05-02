
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Security 
{

    /*public static void main(String args[]) throws IOException{
        //createAccount("mark", "12");
        //createAccount("ally", "000");
        login("ally", "222");
    } */
    

    public static boolean login(String username, String password) throws IOException {
        //Read in usernames and passwords from text file
        // go through usernames in the entire file
        //Users.txt
        
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
                        //TODO once user has successfully logged in open their client window
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

        //TODO currently this rewrites the entire file which we need to fix
        FileWriter fileWriter = new FileWriter("C:\\Users\\17322\\HighSchool\\TradingSystem\\src\\TXT Files\\Users.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(username+"\t"+password+"\n");
        bufferedWriter.close();

        //If username matches existing account, return false

        

        return true;
    }


}