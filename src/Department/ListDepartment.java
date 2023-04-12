
package Department;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;
import payrollapplication.ExportList;

public class ListDepartment extends JFrame{
    private JFrame listDepartmentFrame;
    private JLabel filterLabel;
    private JTextField filterTextField;
    private JButton filterButton,exportButton,prevButton;
    private JTable departmentTable;
    private DefaultTableModel model;
    private JPanel filterPanel;
    
    
    private String[] column_names = {"Department ID","Department Name","Manager","Location"};;
    private ArrayList<Department> list_department = new ArrayList<>();;
    
    
    public ListDepartment(){
        listDepartmentFrame = new JFrame();
        
        filterLabel = new JLabel("Filter: ");
        filterTextField = new JTextField();
        filterTextField.setText("Filter Word");
        filterButton = new JButton("Search");
        exportButton = new JButton("Export");
        prevButton = new JButton("Close");
        departmentTable = new JTable();
        model = new DefaultTableModel(column_names, 0);
        departmentTable.setModel(model);
        
        DBTask task = new DBTask();
        
        list_department = task.getListofDepartmentFromTable();
        
        for(Department dep : list_department){
           
            model.addRow(new Object[]{dep.getDept_id(),dep.getDept_name(),dep.getManager(),dep.getLocation() });
        }
        
        filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(filterLabel);
        filterPanel.add(filterTextField);
        filterPanel.add(filterButton);
        filterPanel.add(exportButton);
        filterPanel.add(prevButton);
        
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(departmentTable), BorderLayout.CENTER);
        
        
        listDepartmentFrame.getContentPane().setLayout(new BorderLayout());
        listDepartmentFrame.getContentPane().add(filterPanel, BorderLayout.NORTH);
        listDepartmentFrame.getContentPane().add(tablePanel,BorderLayout.CENTER);
        
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filterText = filterTextField.getText();
                performFilteration(filterText);
            }

            
        });
        
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exporting Feature");
                ExportList exportList = new ExportList(model, model.getRowCount(),column_names);
                int result = exportList.writeFile();
                if(result == 1){
                    JOptionPane.showMessageDialog(null, "Exported Successfully");
                }else if(result == 0){
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again");
                }
            }     
        });
        
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listDepartmentFrame.setVisible(false);
                listDepartmentFrame = new MainMenu().getMainMenuFrame();
                listDepartmentFrame.setVisible(true);
            }
        });
          
        listDepartmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listDepartmentFrame.setTitle("All Department List");
        listDepartmentFrame.setSize(600, 600);
        listDepartmentFrame.setLocationRelativeTo(null);
        listDepartmentFrame.setVisible(true);
        
    }
    private void performFilteration(String filterTExt){
        model.setRowCount(0);
        String text_to_search = filterTExt.toLowerCase();
        for(Department dep : list_department){
            if(dep.getDept_id().toLowerCase().contains(text_to_search) || 
               dep.getDept_name().toLowerCase().contains(text_to_search) ||
                    dep.getManager().toLowerCase().contains(text_to_search) || 
                    dep.getLocation().toLowerCase().contains(text_to_search))
            {
                model.addRow(new Object[]{dep.getDept_id(), dep.getDept_name(),dep.getManager(),dep.getLocation()});
            }
        }
        
        
    }
    
    public JFrame getListDepartmentFrame(){
        return listDepartmentFrame;
    }
    
}
