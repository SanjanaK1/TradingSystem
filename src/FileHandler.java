import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

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
                String[] stockList;
                if (!parts[1].equals("[]")) {
                    stockList = parts[1].substring(1, parts[1].length() - 1).split(";");
                } else {
                    stockList = new String[0];
                }
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
        assert stockList != null;
        if (stockList.length > 0) {
            for (int i = 0; i < stockList.length; i++) {
                String[] elementOfStock = stockList[i].split(",");
                String name = elementOfStock[0].substring(1); // skips over the open parenthesis
                int quantity = Integer.parseInt(elementOfStock[1]);
                Date date = Date.parseDate(elementOfStock[2]);
                double boughtPrice = Double.parseDouble(elementOfStock[3].substring(0, elementOfStock[3].indexOf(")"))); // skips over close parenthesis
                Stock s = new Stock(name, StockMarket.getStockByName(name).getCurrentPrice(), boughtPrice, date, null, StockMarket.getStockByName(name).getQuantity());
                p.addStock(s, quantity);
            }
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
                int amountOfStock = Integer.parseInt(tokens[2]);
                Stock stock = new Stock(name,price, -1, null, null, amountOfStock);
                StockMarket.addStock(stock, amountOfStock);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeToUsers(String username, String password) throws IOException {
        FileWriter fileWriter = new FileWriter("src/TXT Files/users.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("\n"+username+"\t"+password);
        bufferedWriter.close();
    }

    public static void writeToCustomers(String username, Portfolio portfolio, double money, boolean derivativeAccount) throws IOException {
        FileWriter fileWriter = new FileWriter("src/TXT Files/customers.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String isEligibleForDerivative = "N";
        if (derivativeAccount) {
            isEligibleForDerivative = "Y";
        }
        bufferedWriter.write("\n"+username+"\t"+portfolio.displayStockList()+"\t"+money+"\t"+isEligibleForDerivative);
        bufferedWriter.close();
    }

    public static void updateCustomer(Customer c) {
        //todo
        String fileName = "src/TXT Files/customers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                String name = tokens[0];
                if (c.getName().equals(name)) {
                    String isEligibleForDerivative = "N";
                    if (c.isEligibleForDerivative()){
                        isEligibleForDerivative = "Y";
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStockMarketQuantity(Stock s, int quantity) {
        //todo
        // read from stocks.txt to update stockMarket quantity.
        String fileName = "src/TXT Files/stocks.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                String name = tokens[0];
                double price = Double.parseDouble(tokens[1]);
                int amountOfStock = Integer.parseInt(tokens[2]);
                if (s.getName().equals(name)) {
                    fileName.replace(line, name + "\t" + s.getCurrentPrice()+ "\t" + quantity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String[]> getStockFile() {
        String fileName = "src/TXT Files/stocks.txt";
        List<String[]> stockList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                stockList.add(tokens);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stockList;
    }

    public static boolean setStockFile(List<String[]> stockList) {
        String fileName = "src/TXT Files/stocks.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Stocks  Price   Count\n");
            for (String[] stock : stockList) {
                for (String word : stock) {
                    writer.write(word);
                    writer.write("     ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}