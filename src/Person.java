/*
    Person Class: Class that maintains common attributes between PortfolioManager and
 */
public class Person {

    private String name;

    public Person(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name object of Person cannot be null.");
        }
        this.name = name;
    }
}