package com.BITSBids.BITSBids.model;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

    private static final String SPRING_API_BASE_URL = "http://localhost:8080";

    private final RestTemplate restTemplate;

    public SwingLogin() {
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

        restTemplate = new RestTemplate();

        frame.setVisible(true);
    }

    private void performLogin() {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        String loginUrl = SPRING_API_BASE_URL + "/login";
        String registerUrl = SPRING_API_BASE_URL;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        User user = new User();
        user.setUname(username);
        user.setPassword(password);

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<User> responseEntity;

        try {
            // Try to log in
            responseEntity = restTemplate.postForEntity(loginUrl, requestEntity, User.class);

            // If login is successful, open the specified URL
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                successLabel.setText("Login Successful");

                // Open the URL in a browser
                Desktop desktop = Desktop.getDesktop();
                URI oURL = new URI("http://127.0.0.1:5500/BidLoggedIn.html");
                desktop.browse(oURL);
            }
        } catch (Exception ex) {
            // If login fails, try to register
            try {
                responseEntity = restTemplate.postForEntity(registerUrl, requestEntity, User.class);
                successLabel.setText("Registration Successful");
            } catch (Exception registrationEx) {
                successLabel.setText("Login or Registration Failed");
            }
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

    private static class User {
        private String uname;
        private String ubatch;
        private Integer ucontact;
        private String uemail;
        private String password;

        public void setUname(String uname) {
            this.uname = uname;
        }

        public void setPassword(String password){
            this.password=password;
        }

    }
}
