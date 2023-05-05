import java.util.*;

public class PortfolioManager extends Person{

    private double approvalThreshold;
    private List<Customer> customerList = new ArrayList<>();
    private static PortfolioManager instance;

    private PortfolioManager(String name, double approvalThreshold, List<Customer> customerList) {
        super(name);
        setApprovalThreshold(approvalThreshold);
        setCustomerList(customerList);
    }

    public static PortfolioManager getPortfolioManagerInstance() {
        if (instance == null) {
            instance = new PortfolioManager("", 2000, null); //TODO: GET NAME FROM FOLDER.
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


    public void setApprovalThreshold(double approvalThreshold) {
        this.approvalThreshold = approvalThreshold;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        if (customerList != null) {
            this.customerList = customerList;
        }
    }
}
