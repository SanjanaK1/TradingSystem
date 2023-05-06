import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {

    public static void loadAllFiles(PortfolioManager pm) throws IOException {
        loadStocksToStockMarket();
        pm.setCustomerList(loadCustomers());
        loadUsers();
    }

    public static List<Customer> loadCustomers() {
        List<Customer> customerList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/TXT Files/customers.txt"));
            br.readLine(); // skips the column titles
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                //System.out.println(line);
                String[] parts = line.split("\\s+");
                String name = parts[0];
                String[] stockList = parts[1].split(";");
                double money = Double.parseDouble(parts[2]);
                boolean isEligibleForDerivative = parts[3].equals("Y");
                Portfolio p = new BasePortfolio(new HashMap<>());
                processStockList(p, stockList);
                PortfolioManager pf = PortfolioManager.getPortfolioManagerInstance();
                Customer c = new Customer(name, false, money, p, isEligibleForDerivative);
                pf.approveCustomer(c);
                customerList.add(c);
            }
            return customerList;
        } catch  (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public static void processStockList(Portfolio p, String[] stockList) {
        Map<Stock, Integer> stockMap = new HashMap<>();
        assert stockList != null;
        for (int i = 0; i < stockList.length; i++) {
            String[] elementOfStock = stockList[i].split(",");
            String name = elementOfStock[0].substring(1); // skips over the open parenthesis
            int quantity = Integer.parseInt(elementOfStock[1]);
            Date date = Date.parseDate(elementOfStock[2]);
            double boughtPrice = Double.parseDouble(elementOfStock[3]);
            Stock s = new Stock(name, StockMarket.getStockByName(name).getCurrentPrice(), boughtPrice, date, null);
            p.addStock(s, quantity);
        }
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

    public static void loadStocksToStockMarket() {
        //read from stocks.txt
        String fileName = "src/TXT Files/stocks.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                String name = tokens[0];
                double price = Double.parseDouble(tokens[1]);
                Stock stock = new Stock(name,price, -1, null, null);
                int amountOfStock = Integer.parseInt(tokens[2]);
                StockMarket.addStock(stock, amountOfStock);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeToUsers(String username, String password) throws IOException {
        FileWriter fileWriter = new FileWriter("src/TXT Files/users.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(username+"\t"+password+"\n");
        bufferedWriter.close();
    }
}