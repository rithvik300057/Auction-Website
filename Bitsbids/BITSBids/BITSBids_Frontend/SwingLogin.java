import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.net.URI;
import java.util.regex.*;

public class SwingLogin {

    JLabel label = new JLabel("User: ");
    JTextField userTextField = new JTextField();
    JLabel passwordLabel = new JLabel("Password: ");
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton clearButton = new JButton("Clear");
    JLabel successLabel = new JLabel();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    SwingLogin() {
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        userTextField.setBounds(100, 20, 165, 25);
        panel.add(userTextField);

        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        clearButton.setBounds(100, 80, 80, 25);
        panel.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        successLabel.setBounds(10, 110, 300, 25);
        panel.add(successLabel);

        frame.setVisible(true);
    }

    private void performLogin() {
        String user = userTextField.getText();
        String password = new String(passwordField.getPassword());

        String regexPattern = "^f2022\\d{4}@hyderabad\\.bits-pilani\\.ac\\.in$";

        if (Pattern.matches(regexPattern, user) && "1234".equals(password)) {
            successLabel.setText("Login Successful");

            try {
                Desktop desktop = Desktop.getDesktop();
                URI oURL = new URI("http://127.0.0.1:5500/BidLoggedIn.html");
                desktop.browse(oURL);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            successLabel.setText("Login Failed");
        }
    }

    private void clearFields() {
        userTextField.setText("");
        passwordField.setText("");
        successLabel.setText("");
    }

    public static void main(String[] args) {
        new SwingLogin();
    }
}
