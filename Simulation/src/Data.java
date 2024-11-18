import java.util.ArrayList;

public class Data {
    private ArrayList<Double> xValues = new ArrayList<>();
    private ArrayList<Double> yValues = new ArrayList<>();
    private ArrayList<Double> zValues = new ArrayList<>();
    private ArrayList<Double> errorValues = new ArrayList<>();

    public void addData(double x, double y, double z, double error) {
        xValues.add(x);
        yValues.add(y);
        zValues.add(z);
        errorValues.add(error);
    }

    public int getSize() {
        return errorValues.size();
    }

    public double getX(int index) {
        return xValues.get(index);
    }
    public double getY(int index) {
        return yValues.get(index);
    }
    public double getZ(int index) {
        return xValues.get(index);
    }
    public double getError(int index) {
        return errorValues.get(index);
    }
}
