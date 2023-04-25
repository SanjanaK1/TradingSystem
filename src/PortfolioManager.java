//observer pattern
//singleton pattern


// Class implements singleton pattern.

import java.util.*;

public class PortfolioManager {

    private double approvalThreshold;
    private List<Customer> customerList = new ArrayList<>();
    private static PortfolioManager instance;

    private PortfolioManager() {

    }

    public static PortfolioManager getPortfolioManagerInstance() {
        if (instance == null) {
            instance = new PortfolioManager();
        }
        return instance;
    }

    public boolean approveCustomer(Customer c) {
        boolean isApproved = false;
        try {
            if (c.getMoney() >= approvalThreshold) {
                isApproved = true;
            }
            c.setApproved(isApproved);
            return isApproved;

        } catch (Exception e) {
            return isApproved;
        }
    }


}
