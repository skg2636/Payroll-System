/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author KIIT
 */
public class LoginAdmin extends JFrame implements ActionListener {

    JButton loginButton, exitButton;
    JPanel loginPanel;
    JLabel usernameLabel, passwordLabel;
    final JTextField usernameField;
    final JPasswordField passwordField;
    JFrame loginFrame;
    JDialog dialogBox;

    public LoginAdmin() {

        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setPreferredSize(new Dimension(400, 250));
        loginPanel.setBackground(Color.GRAY);

        usernameLabel = new JLabel();
        usernameLabel.setText("Admin Username");
        usernameLabel.setBounds(10, 27, 100, 28);
        loginPanel.add(usernameLabel);

        usernameField = new JTextField(15);
        usernameField.setBounds(130, 27, 193, 28);
        loginPanel.add(usernameField);

        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(10, 75, 70, 28);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 75, 193, 28);
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(60, 120, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(170, 120, 90, 25);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.BLACK);
        exitButton.addActionListener(this);
        loginPanel.add(exitButton);

        loginFrame = new JFrame();
        loginFrame.setLayout(new GridBagLayout());
        loginFrame.setTitle("ADMIN LOGIN");
        loginFrame.setLocation(new Point(500, 300));
        loginFrame.add(loginPanel, new GridBagConstraints());
        loginFrame.setVisible(true);
        loginFrame.setSize(new Dimension(600, 500));
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);

    }
    private void showProgressDailog()
    {
        dialogBox = new JDialog();
        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.add(new JLabel("Please wait while logging in..."),new GridBagConstraints());
        dialogBox.getContentPane().add(newPanel);
        dialogBox.setSize(200,120);
        dialogBox.setLocationRelativeTo(loginFrame);
        dialogBox.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialogBox.setModal(false);
        dialogBox.setVisible(true);
    }
    
    private void hideProcessDailog()
    {
        dialogBox.setVisible(false);
        dialogBox.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Username cannot be left empty.");
                return;
            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "Password cannot be left empty.");
                return;
            }
            
            
            showProgressDailog();
            DBTask dbTask = new DBTask();
            int result = dbTask.authenticateAdminLogin(username, password);
            if(result == 1)
            {
                dbTask.writeSuccessfulAdminLoginTime(username);
            }
            hideProcessDailog();
            if (result == 1) {
                System.out.println("Login Sucessful");
                JOptionPane.showMessageDialog(null, "Login Successful");
                new MainMenu();
                
            } else {
                System.out.println("Login not Sucessful");
                JOptionPane.showMessageDialog(null, "Login Failed! Wrong Credentials");
            }
        } else if (e.getSource() == exitButton) {
            setVisible(false);
            System.exit(0);
        }

    }

}
