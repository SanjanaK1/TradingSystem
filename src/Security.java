import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Security 
{
    private static Customer c;
    private static BasePortfolio basePortfolio = new BasePortfolio( new HashMap<Stock, Integer>());
    /* public static void main(String args[]) throws IOException{
            createAccount("eli", "000");
        }*/

    public static boolean login(String username, String password) throws IOException {
        Map<String, String> customerInformation = FileHandler.loadUsers();
        assert customerInformation != null;
        if (customerInformation.get(username) != null) {
            if (customerInformation.get(username) .equals(password)) {
                System.out.println("Successfully logged in, opening your client window");

                return true;
            } else {
                System.out.println("Wrong password");
                return false;
            }

        }
        System.out.println("This user does not exist, please create an account first");
        return false;

    }

    public static boolean createAccount(String username, String password) throws IOException {
        //Read in usernames and passwords from text file
        if (login(username, password)) {
            System.out.println("user exists already");
            return false;
        }
        else{
            FileHandler.writeToUsers(username, password);
            c = new Customer(username, false,2500.0, basePortfolio);
            setCustomer();
            return true;
        }
    }

    private static void setCustomer() {
        DisplayFacade.setCustomer(c);
    }
}