package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Security 
{



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
                        UserWindow window = new UserWindow();
                        window.openClientWindow(); //once user has successfully logged in open their client window
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

    public static boolean createAccount(String username, String password) {
        //Read in usernames and passwords from text file

        //If username matches existing account, return false

        //Add username and password to text files

        //Create corresponding client window

        return true;
    }


}