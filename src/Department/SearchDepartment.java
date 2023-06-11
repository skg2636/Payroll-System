package Department;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;


public class SearchDepartment extends JFrame {
    
    private DBTask task;
    private JLabel idLabel, nameLabel, managerLabel, locationLabel, managerResultField, locationResultField;
    private JTextField idTextField, nameTextField;
    private JFrame searchDepartmentFrame;
    private JButton searchButton, closeButton;

    public SearchDepartment() {
        
        task = new DBTask();
        searchDepartmentFrame = new JFrame();
        searchDepartmentFrame.setTitle("Search Department");
        searchDepartmentFrame.setSize(400, 400);

        searchDepartmentFrame.setLayout(null);
        searchDepartmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchDepartmentFrame.setResizable(false);

        idLabel = new JLabel("Department ID:");
        idTextField = new JTextField();
        nameLabel = new JLabel("Department Name:");
        nameTextField = new JTextField();
        managerLabel = new JLabel("Manager Name:");
        managerResultField = new JLabel("");
        locationLabel = new JLabel("Location:");
        locationResultField = new JLabel();

        idLabel.setBounds(50, 50, 150, 25);
        idTextField.setBounds(200, 50, 150, 25);
        nameLabel.setBounds(50, 80, 150, 25);
        nameTextField.setBounds(200, 80, 150, 25);
        managerLabel.setBounds(50, 110, 150, 25);
        managerResultField.setBounds(200, 110, 150, 25);
        locationLabel.setBounds(50, 140, 150, 25);
        locationResultField.setBounds(200, 140, 150, 25);

        searchDepartmentFrame.add(idLabel);
        searchDepartmentFrame.add(idTextField);
        searchDepartmentFrame.add(nameLabel);
        searchDepartmentFrame.add(nameTextField);
        searchDepartmentFrame.add(managerLabel);
        searchDepartmentFrame.add(managerResultField);
        searchDepartmentFrame.add(locationLabel);
        searchDepartmentFrame.add(locationResultField);

        searchButton = new JButton("Search");
        closeButton = new JButton("Close");

        searchButton.setBounds(80, 180, 80, 25);
        closeButton.setBounds(200, 180, 80, 25);
        searchDepartmentFrame.add(searchButton);
        searchDepartmentFrame.add(closeButton);

        closeButton.addActionListener(e -> {
            searchDepartmentFrame.setVisible(false);
            searchDepartmentFrame = new MainMenu().getMainMenuFrame();
            searchDepartmentFrame.setVisible(true);
        });

        searchButton.addActionListener(e -> {
            String dept_id = idTextField.getText();
            String dept_name = nameTextField.getText();
            searchDepartment(dept_id, dept_name);
        });

    }

    public JFrame getSearchDepartmentFrame() {
        return searchDepartmentFrame;
    }
    
    public void setDepartmentDetails(Department department)
    {
        idTextField.setText(department.getDept_id());
        nameTextField.setText(department.getDept_name());
        managerResultField.setText(department.getManager());
        locationResultField.setText(department.getLocation());
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
                }else{
                    setDepartmentDetails(department);
                }
            }else
            {
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
