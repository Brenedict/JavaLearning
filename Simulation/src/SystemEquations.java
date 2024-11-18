public class SystemEquations {
    public static Data calculateJacobi(double num1, double num2, double num3) {
        Data result = new Data();
        result = iterate(result, num1, num2, num3, true);
        return result;
    }

    public static Data calculateGaussSeidel(double num1, double num2, double num3) {
        Data result = new Data();
        result = iterate(result, num1, num2, num3, false);
        return result;
    }

    private static Data iterate(Data data, double x, double y, double z, boolean isJacobi) {
        double new_x, new_y, new_z;

        if(isJacobi) {
            new_x = (-1/5.0) + (2/5.0*y) - (3/5.0*z);
            new_y = (2/9.0) + (3/9.0*x) - (1/9.0*z);
            new_z = (-3/7.0) + (2/7.0*x) - (1/7.0*y);
        } else {
            new_x = (-1 / 5.0) + (2 / 5.0 * y) - (3 / 5.0 * z);
            new_y = (2 / 9.0) + (3 / 9.0 * new_x) - (1 / 9.0 * z);
            new_z = (-3 / 7.0) + (2 / 7.0 * new_x) - (1 / 7.0 * new_y);
        }

        double error_x = Math.abs((new_x - x)/new_x);
        double error_y = Math.abs((new_y - y)/new_y);
        double error_z = Math.abs((new_z - z)/new_z);

        double error = (error_x + error_y + error_z)/3;

        data.addData(new_x, new_y, new_z, error);

        if(error > 0.000000000000001) {
            return iterate(data, new_x, new_y, new_z, isJacobi);
        }
        else {
            return data;
        }
    }
}
