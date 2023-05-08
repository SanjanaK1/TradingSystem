import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Security 
{
    private static Customer c;
    private static BasePortfolio basePortfolio = new BasePortfolio( new HashMap<Stock, Integer>());

    private static final String MANAGERNAME = "admin", MANAGERPASSWORD = "admin";

    public static boolean login(String username, String password) throws IOException {
        Map<String, String> customerInformation = FileHandler.loadUsers();
        assert customerInformation != null;
        if (customerInformation.get(username) != null) {
            if (customerInformation.get(username).equals(password)) {
                System.out.println("Successfully logged in, opening your client window");
                List<Customer> listOfCustomers = FileHandler.loadCustomers();
                assert listOfCustomers != null;
                for(Customer cu : listOfCustomers)
                {
                    if(cu.getName().equals(username)){
                        c = cu;
                        setCustomer();
                    }
                }
                return true;
            } else {
                JOptionPane.showMessageDialog(null,"Wrong password");
                return false;
            }

        }
        if (username.equals(MANAGERNAME) && password.equals(MANAGERPASSWORD)) {
            //open manager window
            GUI.managerWindow();
            return true;
        }
        JOptionPane.showMessageDialog(null,"This user does not exist, please create an account first");
        return false;
    }

    public static boolean createAccount(String username, String password) throws IOException {
        //Read in usernames and passwords from text file
        if (login(username, password) || doesUserExist(username)) {
            JOptionPane.showMessageDialog(null,"User already exists");
            return false;
        }
        else{
            FileHandler.writeToUsers(username, password);
            c = new Customer(username, false,2500.0, basePortfolio, false);
            FileHandler.writeToCustomers(username, c.getPortfolio(), c.getMoney(), c.isEligibleForDerivative());
            setCustomer();
            return true;
        }
    }

    public static boolean doesUserExist(String name) throws IOException {
        Map<String, String> customerInformation = FileHandler.loadUsers();
        assert customerInformation != null;
        if (customerInformation.get(name) != null) {
            return true;
        }
        return false;
    }

    private static void setCustomer() {
        DisplayAdaptor.setCustomer(c);
    }
}