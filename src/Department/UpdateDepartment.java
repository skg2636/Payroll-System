
package Department;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;


public class UpdateDepartment {
    
    private DBTask task;
    private JLabel idLabel, nameLabel, managerLabel, locationLabel;
    private JTextField idTextField, nameTextField, managerResultField, locationResultField;
    private JFrame updateDepartmentFrame;
    private JButton searchButton, closeButton, updateButton, deleteButton;

    public UpdateDepartment() {
        
        task = new DBTask();
        
        updateDepartmentFrame = new JFrame();
        updateDepartmentFrame.setTitle("Update Department");
        updateDepartmentFrame.setSize(400, 400);

        updateDepartmentFrame.setLayout(null);
        updateDepartmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateDepartmentFrame.setResizable(false);

        idLabel = new JLabel("Department ID:");
        idTextField = new JTextField();
        nameLabel = new JLabel("Department Name:");
        nameTextField = new JTextField();
        managerLabel = new JLabel("Manager Name:");
        managerResultField = new JTextField();
        locationLabel = new JLabel("Location:");
        locationResultField = new JTextField();

        managerResultField.setEditable(false);
        locationResultField.setEditable(false);

        idLabel.setBounds(50, 50, 150, 25);
        idTextField.setBounds(200, 50, 150, 25);
        nameLabel.setBounds(50, 80, 150, 25);
        nameTextField.setBounds(200, 80, 150, 25);
        managerLabel.setBounds(50, 110, 150, 25);
        managerResultField.setBounds(200, 110, 150, 25);
        locationLabel.setBounds(50, 140, 150, 25);
        locationResultField.setBounds(200, 140, 150, 25);

        updateDepartmentFrame.add(idLabel);
        updateDepartmentFrame.add(idTextField);
        updateDepartmentFrame.add(nameLabel);
        updateDepartmentFrame.add(nameTextField);
        updateDepartmentFrame.add(managerLabel);
        updateDepartmentFrame.add(managerResultField);
        updateDepartmentFrame.add(locationLabel);
        updateDepartmentFrame.add(locationResultField);

        searchButton = new JButton("Search");
        closeButton = new JButton("Close");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);

        searchButton.setBounds(50, 180, 100, 25);
        closeButton.setBounds(200, 180, 100, 25);
        updateButton.setBounds(50, 230, 100, 25);
        deleteButton.setBounds(200, 230, 100, 25);
        updateDepartmentFrame.add(searchButton);
        updateDepartmentFrame.add(closeButton);
        updateDepartmentFrame.add(updateButton);
        updateDepartmentFrame.add(deleteButton);

        closeButton.addActionListener(e -> {
            updateDepartmentFrame.setVisible(false);
            updateDepartmentFrame = new MainMenu().getMainMenuFrame();
            updateDepartmentFrame.setVisible(true);
        });

        searchButton.addActionListener(e -> {
            String dept_id = idTextField.getText();
            String dept_name = nameTextField.getText();
            searchDepartment(dept_id, dept_name);
        });
        
        deleteButton.addActionListener(e -> {
            String dept_id = idTextField.getText();
            String dept_name = nameTextField.getText();
            String manager = managerResultField.getText();
            String location = locationResultField.getText();
            
            
            int user_final_input = JOptionPane.showConfirmDialog(null, 
                         "Are you sure you wish to delete with id = " + dept_id,null, JOptionPane.YES_NO_OPTION);
            
            if(user_final_input == JOptionPane.NO_OPTION){
                return;
            }

            int res = task.deleteFromDepartmentTable(dept_id, dept_name, manager, location);
            if (res == 1) {
                JOptionPane.showMessageDialog(null, "Department Deleted");
                updateDepartmentFrame.setVisible(false);
                updateDepartmentFrame = new MainMenu().getMainMenuFrame();
                updateDepartmentFrame.setVisible(true);
               
            } else {
                JOptionPane.showMessageDialog(null, "Something Error,Please try again");
                return;
            }
        });

        updateButton.addActionListener(e -> {
            String dept_id = idTextField.getText();
            String dept_name = nameTextField.getText();
            String manager = managerResultField.getText();
            String location = locationResultField.getText();
            
            int user_final_input = JOptionPane.showConfirmDialog(null, 
                         "Are you sure you wish to update with id = " + dept_id,null, JOptionPane.YES_NO_OPTION);
            
            if(user_final_input == JOptionPane.NO_OPTION){
                return;
            }
            
            
          
            int res = task.updateDepartmentTable(dept_id, dept_name, manager, location);
            if (res == 1) {
                JOptionPane.showMessageDialog(null, "Update Done");
                updateDepartmentFrame.setVisible(false);
                updateDepartmentFrame = new MainMenu().getMainMenuFrame();
                updateDepartmentFrame.setVisible(true);
               
            } else {
                JOptionPane.showMessageDialog(null, "Something Error,Please try again");
                return;
            }

        });

    }

    public JFrame getUpdateDepartmentFrame() {
        return updateDepartmentFrame;
    }

    public void setDepartmentDetails(Department department) {
        idTextField.setText(department.getDept_id());
        nameTextField.setText(department.getDept_name());
        managerResultField.setText(department.getManager());
        locationResultField.setText(department.getLocation());
        updateButton.setEnabled(true);
        deleteButton.setEnabled(true);
        managerResultField.setEditable(true);
        locationResultField.setEditable(true);
    }

    private void searchDepartment(String id, String name) {
 

        if (id.equals("") && name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter the Department ID or Department Name");
            return;
        } else if (!id.equals("") && !name.equals("")) {
            Department department = task.searchDepartmentById(id);
            if (department == null) {
                department = task.searchDepartmentByName(name);

                if (department == null) {
                    JOptionPane.showMessageDialog(null, "No Department with this " + name + "name or with this " + id + " exists.");
                    return;
                } else {
                    setDepartmentDetails(department);
                }
            } else {
                setDepartmentDetails(department);
            }
        } else if (!id.equals("") && name.equals("")) {
            Department department = task.searchDepartmentById(id);
            if (department == null) {
                JOptionPane.showMessageDialog(null, "No Department with this " + id + " id exists.");
                return;
            }
            setDepartmentDetails(department);
        } else if (id.equals("") && !name.equals("")) {
            Department department = task.searchDepartmentByName(name);
            if (department == null) {
                JOptionPane.showMessageDialog(null, "No Department with this " + name + " name exists.");
                return;
            }
            setDepartmentDetails(department);
        }
    }

}
