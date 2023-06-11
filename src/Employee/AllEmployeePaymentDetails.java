/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import Grade.Grade;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import payrollapplication.DBTask;
import payrollapplication.ExportList;
import payrollapplication.MainMenu;

/**
 *
 * @author KIIT
 */
public class AllEmployeePaymentDetails extends JFrame{
    
    private JFrame allEmployeePaymentDetailsFrame; 
    private DBTask task;
   
    private JButton showButton, closeButton,exportButton;
    private JCheckBox showTransferDetails;
    private JComboBox monthBox,yearBox;
    
    private String[] columns = new String[]{"Sl.no","Emp Id","Name","MM-YY","Amount","Transfer Details"};
    private String[] monthList,yearList;
    
    private JTable paymentTable;
    private DefaultTableModel model;
    private JPanel buttonsPanel,tablePanel;
    
    public AllEmployeePaymentDetails(){
        task = new DBTask();
        allEmployeePaymentDetailsFrame = new JFrame();
        
        allEmployeePaymentDetailsFrame.setSize(700, 650);
        allEmployeePaymentDetailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        allEmployeePaymentDetailsFrame.setTitle("All Employee Payment Details");
        
        
        
        monthList = new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

        yearList = new String[14];

        for (int i = 2010; i <= 2023; i++) {
            yearList[i - 2010] = Integer.toString(i);
        }
        
        monthBox = new JComboBox(monthList);
        yearBox = new JComboBox(yearList);
        showButton = new JButton("Show");
        closeButton = new JButton("Close");
        exportButton = new JButton("Export");
        showTransferDetails = new JCheckBox("Show Transfer Details");
        
        paymentTable = new JTable();
        model = new DefaultTableModel(columns,0);
        paymentTable.setModel(model);
        
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(monthBox);
        buttonsPanel.add(yearBox);
        buttonsPanel.add(showButton);
        buttonsPanel.add(closeButton);
        buttonsPanel.add(exportButton);
        buttonsPanel.add(showTransferDetails);
        
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(paymentTable),BorderLayout.CENTER);
        
        allEmployeePaymentDetailsFrame.getContentPane().setLayout(new BorderLayout());
        allEmployeePaymentDetailsFrame.getContentPane().add(buttonsPanel,BorderLayout.NORTH);
        allEmployeePaymentDetailsFrame.getContentPane().add(tablePanel,BorderLayout.CENTER);
        
        paymentTable.getColumnModel().getColumn(0).setPreferredWidth(3);
        paymentTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportList exportList = new ExportList(model, model.getRowCount(), columns);
                int result = exportList.writeFile();
                if(result == 1){
                    JOptionPane.showMessageDialog(null, "Exported Successfully");
                }else if(result == 0){
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again");
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allEmployeePaymentDetailsFrame.setVisible(false);
                allEmployeePaymentDetailsFrame = new MainMenu().getMainMenuFrame();
                allEmployeePaymentDetailsFrame.setVisible(true);
            }
        });
        
        
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String monthSelected = monthList[monthBox.getSelectedIndex()];
                String yearSelected = yearList[yearBox.getSelectedIndex()];
                if (checkPastDate(monthSelected, yearSelected)) {
                    showEmployeePayment(monthBox.getSelectedIndex()+1,yearSelected);
                } else {
                    JOptionPane.showMessageDialog(null, "Payment for Selected Month is not generated");
                }
            }
        });
        
        allEmployeePaymentDetailsFrame.setVisible(true);
        
    }
    private boolean checkPastDate(String mm, String yy) {
        Month month = Month.valueOf(mm.toUpperCase(Locale.ENGLISH));
        LocalDate localDate = LocalDate.now();

        LocalDate inputDate = LocalDate.of(Integer.parseInt(yy), month, 1);

        boolean isPastMonth = inputDate.isBefore(localDate.withDayOfMonth(1)) && !inputDate.isEqual(localDate.withDayOfMonth(1));

        return isPastMonth;
    }
    private void showEmployeePayment(int mm, String yy){
        HashMap<String,String> employeeList = task.fetechAllEmployeeList();
        model.setRowCount(0);
        int i =0;
        for(Map.Entry<String,String> entry : employeeList.entrySet()){ 
            model.addRow(new Object[]{ String.valueOf(++i),entry.getKey(),entry.getValue(),mm+"-"+yy,calculatePayment(entry.getKey(), mm, yy),"Transfer in Details" });
        }
        
        
    }
    
    
    private String calculatePayment(String empId, int mm, String yy){
        
        String gradeId = task.getGradeIdByEmployeeId(empId);
        Grade grade = task.searchGradeById(gradeId);
        String basic = task.getBasicByEmployeeId(empId);
        int cityType = task.getCityByEmployeeId(empId);
        String totalLeave = task.getLeaveDetailsByEmployeeId(empId, String.valueOf(mm), yy);
        if (totalLeave == null) {
            totalLeave = "0";
        }
        
        double basicValue = Double.parseDouble(basic);
        double hraValue = 0;
        if (cityType == 1) {
            hraValue = (Double.parseDouble(grade.getHraPercentA()) * basicValue) / 100;
        } else if (cityType == 2) {
            hraValue = (Double.parseDouble(grade.getHraPercentB()) * basicValue) / 100;
        } else if (cityType == 3) {
            hraValue = (Double.parseDouble(grade.getHraPercentC()) * basicValue) / 100;
        }
        
        double daValue = (Double.parseDouble(grade.getDaPercent()) * basicValue) / 100;
        double taValue = (Double.parseDouble(grade.getTaPercent()) * basicValue) / 100;
        double ltaValue = (Double.parseDouble(grade.getLtaPercent()) * basicValue) / 100;
        double medicalValue = Double.parseDouble(grade.getMedicalAllowance().replace(",", ""));
        double phoneValue = Double.parseDouble(grade.getPhonewifiAllowance().replace(",", ""));
        double otherValue = Double.parseDouble(grade.getOtherAllowance().replace(",", ""));
        
        double totalValue = basicValue + hraValue + daValue + ltaValue + taValue + medicalValue + phoneValue + otherValue;
        double ptaxValue = Double.parseDouble(grade.getProfTax().replace(",", ""));
        double pfundValue = (Double.parseDouble(grade.getPf()) * basicValue) / 100;
        double taxValue = EmployeePaymentDetails.calculateTax(totalValue * 12) / 12;
        
        taxValue = Math.round(taxValue * 100) / 100;
        int paidleaves = Integer.parseInt(task.getPaidLeavesAllowed(empId));
        int extraleave = 0;
        
        if (Integer.parseInt(totalLeave) > paidleaves) {
            extraleave = Integer.parseInt(totalLeave) - paidleaves;
        }
        
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(yy), mm);
        int numberofworkingday = yearMonth.lengthOfMonth();
        
        double leavededuction = (totalValue / numberofworkingday) * extraleave;
        leavededuction = Math.round(leavededuction * 100) / 100;
        
        double totaldeduction = ptaxValue + pfundValue + taxValue + leavededuction;
        
        return String.valueOf(totalValue - totaldeduction);
    }
    
    public JFrame getAllEmployeePaymentDetailsFrame(){
        return allEmployeePaymentDetailsFrame;
    }
    
    public static void main(String args[]){
        new AllEmployeePaymentDetails();
    }
    
}
