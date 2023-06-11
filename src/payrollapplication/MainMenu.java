
package payrollapplication;

import Department.AddDepartment;
import Department.ListDepartment;
import Department.SearchDepartment;
import Department.UpdateDepartment;
import Employee.AddEmployee;
import Employee.EmployeePaymentDetails;
import Grade.AddGrade;
import Grade.DeleteGrade;
import Grade.ListGrade;
import Grade.SearchGrade;
import Grade.UpdateGrade;
import Leave.EmployeeLeaveList;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MainMenu extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu fileMenu, employeeMenu, departmentMenu, gradeMenu, helpMenu, otherTaskMenu;
    JMenuItem sampleItem, listEmployee, addEmployee, removeEmployee, searchEmployee, updateEmployee,employeePaymentDetails,
            listDepartment, addDepartment, searchDepartment, updateDepartment,
            listGrade, addGrade, removeGrade, searchGrade, updateGrade,
            help, leaveRequest, otherTaskItem2, otherTaskItem3, otherTaskItem4;

    JFrame mainMenuFrame;

    public MainMenu() {
        mainMenuFrame = new JFrame("Company/Organisation Name");
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        sampleItem = new JMenuItem("Sample");
        fileMenu.add(sampleItem);
        menuBar.add(fileMenu);

        employeeMenu = new JMenu("Employee");
        listEmployee = new JMenuItem("List of Employee");
        addEmployee = new JMenuItem("Add Employee");
        removeEmployee = new JMenuItem("Delete Employee");
        searchEmployee = new JMenuItem("Search Employee");
        updateEmployee = new JMenuItem("Update Employee");
        employeePaymentDetails = new JMenuItem("Employee Payment Details");
        employeeMenu.add(listEmployee);
        employeeMenu.add(addEmployee);
        employeeMenu.add(removeEmployee);
        employeeMenu.add(searchEmployee);
        employeeMenu.add(updateEmployee);
        employeeMenu.add(employeePaymentDetails);
        menuBar.add(employeeMenu);

        departmentMenu = new JMenu("Department");
        listDepartment = new JMenuItem("List of Department");
        addDepartment = new JMenuItem("Add Department");
        searchDepartment = new JMenuItem("Search Department");
        updateDepartment = new JMenuItem("Update/Delete Department");
        departmentMenu.add(listDepartment);
        departmentMenu.add(addDepartment);
        departmentMenu.add(searchDepartment);
        departmentMenu.add(updateDepartment);
        menuBar.add(departmentMenu);

        gradeMenu = new JMenu("Grade");
        listGrade = new JMenuItem("List of Grades");
        addGrade = new JMenuItem("Add Grade");
        removeGrade = new JMenuItem("Delete Grade");
        searchGrade = new JMenuItem("Search Grade");
        updateGrade = new JMenuItem("Update Grade");
        gradeMenu.add(listGrade);
        gradeMenu.add(addGrade);
        gradeMenu.add(removeGrade);
        gradeMenu.add(searchGrade);
        gradeMenu.add(updateGrade);
        menuBar.add(gradeMenu);

        helpMenu = new JMenu("Help");
        help = new JMenuItem("Need Help ?");
        helpMenu.add(help);
        menuBar.add(helpMenu);

        otherTaskMenu = new JMenu("Other Task");
        leaveRequest = new JMenuItem("Leave Request");
        otherTaskItem2 = new JMenuItem("Task Item 2");
        otherTaskItem3 = new JMenuItem("Task Item 3");
        otherTaskItem4 = new JMenuItem("Task Item 4");
        otherTaskMenu.add(leaveRequest);
        otherTaskMenu.add(otherTaskItem2);
        otherTaskMenu.add(otherTaskItem3);
        otherTaskMenu.add(otherTaskItem4);
        menuBar.add(otherTaskMenu);

        mainMenuFrame.setJMenuBar(menuBar);
        mainMenuFrame.setSize(600, 600);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setResizable(false);
        String imagePath = "C:\\Users\\KIIT\\Documents\\NetBeansProjects\\PayrollApplication\\src\\payrollapplication\\Images\\backgroundImage.jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(imageIcon) {
            // Override the paintComponent method to make the image fit to the JFrame size
            @Override
            public void paintComponent(Graphics g) {
                Dimension dimension = getSize();
                g.drawImage(imageIcon.getImage(), 0, 0, dimension.width, dimension.height, null);
            }
        };
        
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        mainMenuFrame.setLayout(null);
        mainMenuFrame.add(backgroundLabel);
        mainMenuFrame.setContentPane(backgroundLabel);
        mainMenuFrame.setVisible(true);

        addDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new AddDepartment().getAddDepartmentFrame();
                mainMenuFrame.setVisible(true);
            }
        });

        listDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new ListDepartment().getListDepartmentFrame();
                mainMenuFrame.setVisible(true);
            }
        });

        searchDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new SearchDepartment().getSearchDepartmentFrame();
                mainMenuFrame.setVisible(true);
            }
        });

        updateDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new UpdateDepartment().getUpdateDepartmentFrame();
                mainMenuFrame.setVisible(true);
            }
        });

        addGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new AddGrade().getAddGradeFrame();
                mainMenuFrame.setVisible(true);
            }
        });

        searchGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new SearchGrade().getSearchGradeFrame();
                mainMenuFrame.setVisible(true);
            }
        });
        
        updateGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new UpdateGrade().getUpdateGradeFrame();
                mainMenuFrame.setVisible(true);
            }
        });
        
        removeGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new DeleteGrade().getDeleteGradeFrame();
                mainMenuFrame.setVisible(true);
            }
        });
        
        listGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new ListGrade().getListGradeFrame();
                mainMenuFrame.setVisible(true);
            }
        });
        addEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new AddEmployee().getAddEmployeeFrame();
                mainMenuFrame.setVisible(true);
            }
        });
        
        leaveRequest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new EmployeeLeaveList().getEmployeeLeaveListFrame();
                mainMenuFrame.setVisible(true);
            }
        });
        
        employeePaymentDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mainMenuFrame.setVisible(false);
                mainMenuFrame = new EmployeePaymentDetails().getEmployeePaymentDetailsFrame();
                mainMenuFrame.setVisible(true);
            }
        });
        
    }

    public static void main(String args[]) {
        new MainMenu();
    }

    public JFrame getMainMenuFrame() {
        return mainMenuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
