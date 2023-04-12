
package Grade;

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


public class ListGrade extends JFrame{
    
    private JFrame listGradeFrame;
    private JLabel filterLabel;
    private JTextField filterTextField;
    private JButton filterButton,exportButton,prevButton;
    private JTable gradeTable;
    private DefaultTableModel model;
    private JPanel filterPanel;
    private DBTask task;
    private String[] column_names = {"Grade ID","Grade Name","HRA-1","HRA-2","HRA-3",
        "DA","TA","LTA","Medical Allowance","Phone Allowance","Special Allowance",
        "Prof. Tax","P Fund"};
    private ArrayList<Grade> list_grade = new ArrayList<>();;
    
    
    public ListGrade(){
        listGradeFrame = new JFrame();
        
        filterLabel = new JLabel("Filter: ");
        filterTextField = new JTextField();
        filterTextField.setText("Filter Word");
        filterButton = new JButton("Search");
        exportButton = new JButton("Export");
        prevButton = new JButton("Close");
        gradeTable = new JTable();
        model = new DefaultTableModel(column_names, 0);
        gradeTable.setModel(model);
        
        task = new DBTask();
        
        list_grade = task.getListofGradeFromTable();
        
        for(Grade grade : list_grade){
            model.addRow(new Object[]{grade.getGradeId(), grade.getGradeName(),
                    grade.getHraPercentA(),grade.getHraPercentB(),grade.getHraPercentC(),
                    grade.getDaPercent(),grade.getTaPercent(),grade.getLtaPercent(),
                    grade.getMedicalAllowance(),grade.getPhonewifiAllowance(),grade.getOtherAllowance(),
                    grade.getProfTax(),grade.getPf()});
        }
        
        filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(filterLabel);
        filterPanel.add(filterTextField);
        filterPanel.add(filterButton);
        filterPanel.add(exportButton);
        filterPanel.add(prevButton);
        
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(gradeTable), BorderLayout.CENTER);
        tablePanel.setSize(1000, 400);
        
        listGradeFrame.getContentPane().setLayout(new BorderLayout());
        listGradeFrame.getContentPane().add(filterPanel, BorderLayout.NORTH);
        listGradeFrame.getContentPane().add(tablePanel,BorderLayout.CENTER);
        
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
                listGradeFrame.setVisible(false);
                listGradeFrame = new MainMenu().getMainMenuFrame();
                listGradeFrame.setVisible(true);
            }
        });
        
        listGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listGradeFrame.setTitle("All Grade List");
        listGradeFrame.setSize(900, 400);
        listGradeFrame.setLocationRelativeTo(null);
        listGradeFrame.setVisible(true);
        
        
        
        
    }
    
    private void performFilteration(String text){
        model.setRowCount(0);
        String filter_text = text.toLowerCase();
        
        for(Grade grade : list_grade){
            if(grade.getGradeId().toLowerCase().contains(filter_text) || 
               grade.getGradeName().toLowerCase().contains(filter_text) || 
               grade.getHraPercentA().toLowerCase().contains(filter_text) || 
               grade.getHraPercentB().toLowerCase().contains(filter_text) || 
               grade.getHraPercentC().toLowerCase().contains(filter_text) || 
               grade.getDaPercent().toLowerCase().contains(filter_text) || 
               grade.getTaPercent().toLowerCase().contains(filter_text) || 
               grade.getLtaPercent().toLowerCase().contains(filter_text) || 
               grade.getMedicalAllowance().toLowerCase().contains(filter_text) || 
               grade.getPhonewifiAllowance().toLowerCase().contains(filter_text) || 
               grade.getOtherAllowance().toLowerCase().contains(filter_text) || 
               grade.getProfTax().toLowerCase().contains(filter_text) || 
               grade.getPf().toLowerCase().contains(filter_text)){
                
               model.addRow(new Object[]{grade.getGradeId(), grade.getGradeName(),
                    grade.getHraPercentA(),grade.getHraPercentB(),grade.getHraPercentC(),
                    grade.getDaPercent(),grade.getTaPercent(),grade.getLtaPercent(),
                    grade.getMedicalAllowance(),grade.getPhonewifiAllowance(),grade.getOtherAllowance(),
                    grade.getProfTax(),grade.getPf()});
                
            }
        }
        
        
    }
    
    public JFrame getListGradeFrame(){
        return listGradeFrame;
    }
    
    
}
