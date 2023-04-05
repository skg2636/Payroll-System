/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Department;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;

/**
 *
 * @author KIIT
 */
public class AddDepartment extends JFrame {

    private final JLabel idLabel, nameLabel, managerLabel, locationLabel;
    private JTextField idTextField, nameTextField, managerTextField, locationTextField ;
    private JFrame addDepartmentFrame;
    private JDialog dialogBox;
    public AddDepartment() {
        addDepartmentFrame = new JFrame();
        addDepartmentFrame.setTitle("Add Department");
        addDepartmentFrame.setSize(400, 400);

        // Set the layout of the JFrame to null
        addDepartmentFrame.setLayout(null);
        addDepartmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addDepartmentFrame.setResizable(false);

        // Defining all the components
        idLabel = new JLabel("Department ID:");
        idTextField = new JTextField();
        nameLabel = new JLabel("Department Name:");
        nameTextField = new JTextField();
        managerLabel = new JLabel("Manager Name:");
        managerTextField = new JTextField();
        locationLabel = new JLabel("Location:");
        locationTextField = new JTextField();

        // Set the bounds of the labels and text fields
        idLabel.setBounds(50, 50, 150, 25);
        idTextField.setBounds(200, 50, 150, 25);
        nameLabel.setBounds(50, 80, 150, 25);
        nameTextField.setBounds(200, 80, 150, 25);
        managerLabel.setBounds(50, 110, 150, 25);
        managerTextField.setBounds(200, 110, 150, 25);
        locationLabel.setBounds(50, 140, 150, 25);
        locationTextField.setBounds(200, 140, 150, 25);

        // Add the labels and text fields to the JFrame
        addDepartmentFrame.add(idLabel);
        addDepartmentFrame.add(idTextField);
        addDepartmentFrame.add(nameLabel);
        addDepartmentFrame.add(nameTextField);
        addDepartmentFrame.add(managerLabel);
        addDepartmentFrame.add(managerTextField);
        addDepartmentFrame.add(locationLabel);
        addDepartmentFrame.add(locationTextField);

        // Create a new JButton object to submit the department details
        JButton submitButton = new JButton("Submit");
        JButton prevButton = new JButton("Back");
        // Set the bounds of the JButton object
        submitButton.setBounds(80, 180, 80, 25);
        prevButton.setBounds(200,180,80,25);
        // Add an ActionListener to the JButton object to handle the submission of department details
        submitButton.addActionListener(e -> addDepartmentDetailsToTable());
          prevButton.addActionListener(e -> {
            addDepartmentFrame.setVisible(false);
            addDepartmentFrame = new MainMenu().getMainMenuFrame();
            addDepartmentFrame.setVisible(true);
          });
        // Add the JButton object to the JFrame
        addDepartmentFrame.add(submitButton);
        addDepartmentFrame.add(prevButton);
        // Make the JFrame visible
        addDepartmentFrame.setVisible(true);
    }
    
    
    private void addDepartmentDetailsToTable()
    {
        
            // Retrieve the department details from the text fields
            String departmentId = idTextField.getText();
            String departmentName = nameTextField.getText();
            String managerName = managerTextField.getText();
            String location = locationTextField.getText();
            
            if(departmentId.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter the Department ID");
                return;
            }else if(departmentName.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter the Department Name");
                return;
            }else if(managerName.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter the Department ID");
                return;
            }else if(location.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter the Location");
                return;
            }else
            {
                showProgressDailog();
                System.out.println("Department ID: " + departmentId);
                System.out.println("Department Name: " + departmentName);
                System.out.println("Manager Name: " + managerName);
                System.out.println("Location: " + location);
                DBTask task = new DBTask();
                int result = task.addDepartmentRowToTable(departmentId, departmentName, managerName, location);
                hideProcessDailog();
                if(result == 1)
                {
                    JOptionPane.showMessageDialog(null, "Department added Succesfully");
                    return;
                }else if(result == 0)
                {
                    JOptionPane.showMessageDialog(null, "Error");
                    return;
                }else if(result == -1)
                {
                    JOptionPane.showMessageDialog(null, "Department ID already exist");
                    return;
                }
                
            }
            

            // Clear the text fields
            idTextField.setText("");
            nameTextField.setText("");
            managerTextField.setText("");
            locationTextField.setText("");
            
        
      
        
    }
    
    public JFrame getAddDepartmentFrame(){
        return addDepartmentFrame;
    }
    
    private void showProgressDailog()
    {
        dialogBox = new JDialog();
        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.add(new JLabel("Please wait while logging in..."),new GridBagConstraints());
        dialogBox.getContentPane().add(newPanel);
        dialogBox.setSize(200,120);
        dialogBox.setLocationRelativeTo(addDepartmentFrame);
        dialogBox.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialogBox.setModal(false);
        dialogBox.setVisible(true);
    }
    
    private void hideProcessDailog()
    {
        dialogBox.setVisible(false);
        dialogBox.dispose();
    }

}
