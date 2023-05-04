public class DisplayFacade {

    private static Customer c;
    public static int numAttributesToDisplay = 6;
    public static String[] tableHeaders = new String[]{"Stock Name", "Current Value ($)", "Number Owned", "Total Value ($)", "Bought Value ($)", "Delta ($)"};

    public static String[] getTableHeader(){
        return tableHeaders;
    }
    public static String[][] getTableData(){
        return new String[][]{{"Stock Example", "2$", "4", "8$"},{"B1", "B2"}};
    }

    public static String getUserName(){
        return "TODO";
    }

    public static String getTotal() {
        return "0$";
    }

    public static String getLiquid() {
        return "0$";
    }

    //Doesn't have to be STRING. Could be int of a stock id, or however stocks are internally represented.
    public static String[] getOwnedStockNames(){return null;}
    public static int[] getOwnedStockAmounts(){return null;}
}