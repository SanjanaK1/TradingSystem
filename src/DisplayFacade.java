public class DisplayFacade {

    public static String[] getTableHeader(){
        return new String[]{"Stock Name", "Price ($)", "Number Owned", "Total Value ($)"};
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
}
