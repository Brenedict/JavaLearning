import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.file.attribute.UserPrincipal;
import java.sql.SQLException;
import java.util.Arrays;

public class Login implements ActionListener {
    private JLabel title = new JLabel("LOGIN");
    private JLabel userIDLabel = new JLabel("Username:");
    private JTextField userIDField = new JTextField();
    private JLabel userPasswordLabel = new JLabel("Password:");
    private JTextField userPasswordField = new JTextField();
    private JButton loginButton = new JButton("enter");

    Login() {
        final int WIDTH = 550;
        final int HEIGHT = 350;

        Color dark_gray = new Color(25, 25, 25);
        Color dark_gray2 = new Color(64, 62, 63);
        Color white = new Color(255, 255, 255);

        JFrame loginFrame = new JFrame("Logs");

        //  Window Preparation
        loginFrame.setSize(WIDTH, HEIGHT);
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);

        loginFrame.getContentPane().setBackground(new Color(25, 25, 25));

        title.setBounds(125, 20, 400, 50);
        title.setFont(new Font("Serif", Font.BOLD, 45));
        title.setForeground(white);

        userIDLabel.setBounds(125, 80, 75, 50);
        userIDLabel.setForeground(white);
        userIDLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN,11));
        userIDField.setBounds(125, 120, 300, 35);
        userIDField.setBackground(dark_gray2);
        userIDField.setBorder(new LineBorder(dark_gray,1));
        userIDField.setForeground(white);

        System.out.println(Arrays.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()));

        userPasswordLabel.setBounds(125, 160, 75, 50);
        userPasswordLabel.setForeground(white);
        userPasswordLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN,11));
        userPasswordField.setBounds(125, 200, 300, 35);
        userPasswordField.setBackground(dark_gray2);
        userPasswordField.setBorder(new LineBorder(dark_gray,1));
        userPasswordField.setForeground(white);

        loginButton.setBounds(300, 250, 65, 30);
        loginButton.setForeground(white);
        loginButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN,11));
        loginButton.setBackground(dark_gray);
        loginButton.setBorder(new LineBorder(dark_gray2,1));

        loginButton.addActionListener(this);

        loginFrame.add(title);
        loginFrame.add(userIDLabel);
        loginFrame.add(userIDField);
        loginFrame.add(userPasswordLabel);
        loginFrame.add(userPasswordField);
        loginFrame.add(loginButton);

        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userID = userIDField.getText();
        String userPassword = userPasswordField.getText();

        try {
            Database database = new Database(userID, userPassword);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void main(String[] args) {
        Login loginPage = new Login();
    }
}

