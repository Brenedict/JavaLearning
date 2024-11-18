import org.jfree.ui.RefineryUtilities;

public class Main {
    public static void main(String[] args) {
        Data jacobiDataSet = SystemEquations.calculateJacobi(0, 0, 0);

        Data gaussDataSet = SystemEquations.calculateGaussSeidel(0, 0, 0);

        Graph graph = new Graph(jacobiDataSet, gaussDataSet);

        printData(jacobiDataSet, "Jacobi");
        printData(gaussDataSet, "Gauss-Seidel");
    }

    public static void printData(Data data, String title) {
        System.out.println("\n==================================\n\n" + title + ":\n");
        for(int i = 0 ; i < data.getSize() ; i++) {
            System.out.printf("K = %d | E = %.15f | x = %.6f | y = %.6f | z = %.6f\n", (i+1), data.getError(i), data.getX(i), data.getY(i), data.getZ(i));
        }
    }
}

