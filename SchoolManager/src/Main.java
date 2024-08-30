
public class Main {
    public static void main(String[] args) throws Exception {
        databaseUtil database = new databaseUtil();
        database.connect();
        database.insertRow();
    }
}