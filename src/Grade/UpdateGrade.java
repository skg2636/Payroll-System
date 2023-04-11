/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grade;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;

/**
 *
 * @author KIIT
 */
public class UpdateGrade extends JFrame {
    DBTask task;

    private JLabel basicLabel, otherLabel, deductionLabel;
    
    private JLabel gradeIdLabel, gradeNameLabel, hraPercentLabelA, hraPercentLabelB, hraPercentLabelC,
            taPercentLabel, ltaPercentLabel, daPercentLabel, medicalAllowanceLabel, phonewifiAllowanceLabel,
            otherAllowanceLabel, profTaxLabel, pfLabel;
    
    private JTextField gradeIdField, gradeNameField;
    
    private JFormattedTextField hraPercentFieldA, hraPercentFieldB, hraPercentFieldC,
            taPercentField, ltaPercentField, daPercentField, medicalAllowanceField, phonewifiAllowanceField,
            otherAllowanceField, profTaxField, pfField;
    
    private JButton searchButton, updateButton, closeButton;
    
    private JFrame updateGardeFrame;
    private JPanel otherDetailsPanel, basicDetailsPanel, deductionDetailsPanel;
    private NumberFormat numberFormat;
    private NumberFormatter numberFormatter;
    private JFormattedTextField allField[] ;
    private Grade gradeFromDatabase,updatedGrade;
    
    public UpdateGrade(){
        task = new DBTask();
        updateGardeFrame = new JFrame();
        updateGardeFrame.setTitle("Add New Grade");
        updateGardeFrame.setSize(500, 650);
        updateGardeFrame.setLayout(new GridBagLayout());
        updateGardeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateGardeFrame.setResizable(false);
        
        otherDetailsPanel = new JPanel();
        otherDetailsPanel.setLayout(null);
        otherDetailsPanel.setPreferredSize(new Dimension(250, 200));
        otherLabel = new JLabel("Other Allowance Details");
        otherLabel.setFont(new Font("arial", Font.BOLD, 16));
        otherLabel.setBounds(150, 10, 250, 30);
        otherDetailsPanel.add(otherLabel);

        basicDetailsPanel = new JPanel();
        basicDetailsPanel.setLayout(null);
        basicDetailsPanel.setPreferredSize(new Dimension(250, 200));
        basicLabel = new JLabel("Basic Allowance Details");
        basicLabel.setFont(new Font("arial", Font.BOLD, 16));
        basicLabel.setBounds(150, 10, 250, 30);
        basicDetailsPanel.add(basicLabel);

        deductionDetailsPanel = new JPanel();
        deductionDetailsPanel.setLayout(null);
        deductionDetailsPanel.setPreferredSize(new Dimension(250, 200));
        deductionLabel = new JLabel("Deductions Details");
        deductionLabel.setFont(new Font("arial", Font.BOLD, 16));
        deductionLabel.setBounds(150, 10, 250, 30);
        deductionDetailsPanel.add(deductionLabel);
        
        gradeIdLabel = new JLabel("Grade ID:");
        gradeNameLabel = new JLabel("Grade Name:");
        hraPercentLabelA = new JLabel("HRA-T1 %:");
        hraPercentLabelB = new JLabel("HRA-T2 %:");
        hraPercentLabelC = new JLabel("HRA-T3 %:");
        daPercentLabel = new JLabel("DA %:");
        taPercentLabel = new JLabel("TA %:");
        ltaPercentLabel = new JLabel("LTA %:");
        medicalAllowanceLabel = new JLabel("Medical Allowance:");
        phonewifiAllowanceLabel = new JLabel("Phone and Wifi Allowance:");
        otherAllowanceLabel = new JLabel("Other/Bonus Allowance:");
        profTaxLabel = new JLabel("Professional Tax:");
        pfLabel = new JLabel("Provident fund %:");
        
        numberFormat = NumberFormat.getInstance();
        numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0);
        numberFormatter.setMaximum(Integer.MAX_VALUE);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setCommitsOnValidEdit(true);
        
        gradeIdField = new JTextField();
        gradeNameField = new JTextField();
        hraPercentFieldA = new JFormattedTextField(numberFormatter);
        hraPercentFieldB = new JFormattedTextField(numberFormatter);
        hraPercentFieldC = new JFormattedTextField(numberFormatter);
        daPercentField = new JFormattedTextField(numberFormatter);
        taPercentField = new JFormattedTextField(numberFormatter);
        ltaPercentField = new JFormattedTextField(numberFormatter);
        medicalAllowanceField = new JFormattedTextField(numberFormatter);
        phonewifiAllowanceField = new JFormattedTextField(numberFormatter);
        otherAllowanceField = new JFormattedTextField(numberFormatter);
        profTaxField = new JFormattedTextField(numberFormatter);
        pfField = new JFormattedTextField(numberFormatter);
        
        allField = new JFormattedTextField[]{hraPercentFieldA, hraPercentFieldB, hraPercentFieldC,
            taPercentField, ltaPercentField, daPercentField, medicalAllowanceField, phonewifiAllowanceField,
            otherAllowanceField, profTaxField, pfField};
        
        JLabel allLabel[] = {hraPercentLabelA, hraPercentLabelB, hraPercentLabelC,
            taPercentLabel, ltaPercentLabel, daPercentLabel, medicalAllowanceLabel, phonewifiAllowanceLabel,
            otherAllowanceLabel, profTaxLabel, pfLabel};
        
        gradeIdLabel.setBounds(40, 50, 70, 25);
        gradeIdField.setBounds(130, 50, 90, 25);
        gradeNameLabel.setBounds(240, 50, 70, 25);
        gradeNameField.setBounds(330, 50, 90, 25);

        hraPercentLabelA.setBounds(40, 90, 70, 25);
        hraPercentFieldA.setBounds(130, 90, 90, 25);
        hraPercentLabelB.setBounds(240, 90, 70, 25);
        hraPercentFieldB.setBounds(330, 90, 90, 25);

        hraPercentLabelC.setBounds(40, 130, 70, 25);
        hraPercentFieldC.setBounds(130, 130, 90, 25);
        taPercentLabel.setBounds(240, 130, 70, 25);
        taPercentField.setBounds(330, 130, 90, 25);

        ltaPercentLabel.setBounds(40, 170, 70, 25);
        ltaPercentField.setBounds(130, 170, 90, 25);
        daPercentLabel.setBounds(240, 170, 70, 25);
        daPercentField.setBounds(330, 170, 90, 25);

        medicalAllowanceLabel.setBounds(40, 50, 160, 25);
        medicalAllowanceField.setBounds(300, 50, 90, 25);
        phonewifiAllowanceLabel.setBounds(40, 90, 160, 25);
        phonewifiAllowanceField.setBounds(300, 90, 90, 25);
        otherAllowanceLabel.setBounds(40, 130, 160, 25);
        otherAllowanceField.setBounds(300, 130, 90, 25);

        profTaxLabel.setBounds(40, 50, 160, 25);
        profTaxField.setBounds(300, 50, 90, 25);
        pfLabel.setBounds(40, 90, 160, 25);
        pfField.setBounds(300, 90, 90, 25);

        basicDetailsPanel.add(gradeIdLabel);
        basicDetailsPanel.add(gradeIdField);
        basicDetailsPanel.add(gradeNameLabel);
        basicDetailsPanel.add(gradeNameField);
        gradeNameField.setVisible(false);
        
        for (int i = 0; i < 11; i++) {
            if (i >= 9) {
                deductionDetailsPanel.add(allLabel[i]);
                deductionDetailsPanel.add(allField[i]);
            } else if (i >= 6) {
                otherDetailsPanel.add(allLabel[i]);
                otherDetailsPanel.add(allField[i]);
            } else {
                basicDetailsPanel.add(allLabel[i]);
                basicDetailsPanel.add(allField[i]);
            }
            
           allField[i].setVisible(false);
        }
        
        searchButton = new JButton("Search");
        searchButton.setBounds(60, 130, 80, 25);
        deductionDetailsPanel.add(searchButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(160, 130, 80, 25);
        deductionDetailsPanel.add(closeButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(260, 130, 80, 25);
        deductionDetailsPanel.add(updateButton);
        updateButton.setEnabled(false);
        
        closeButton.addActionListener(e -> {
            updateGardeFrame.setVisible(false);
            updateGardeFrame = new MainMenu().getMainMenuFrame();
            updateGardeFrame.setVisible(true);
        });
        
        searchButton.addActionListener(e -> {
            String grade_id = gradeIdField.getText();
            searchGrade(grade_id);
        });
        
        updateButton.addActionListener(e -> {
           updateGradeDetails();
        });
        
        Container contentPane = updateGardeFrame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(basicDetailsPanel);
        contentPane.add(otherDetailsPanel);
        contentPane.add(deductionDetailsPanel);
        updateGardeFrame.setVisible(true);

        
    }
    
    private void updateGradeDetails(){
        String grade_id = gradeIdField.getText();
        String grade_name = gradeNameField.getText();
        
        if (grade_id.equals("") || grade_name.equals("")) {
            JOptionPane.showMessageDialog(null, "Grade ID and Name cannot be empty");
            return;
        }
        
        String hra1 = hraPercentFieldA.getText();
        String hra2 = hraPercentFieldB.getText();
        String hra3 = hraPercentFieldC.getText();
        
        if (hra1.equals("") || hra2.equals("") || hra3.equals("")) {
            JOptionPane.showMessageDialog(null, "Any HRA cannot be empty");
            return;
        }
        
        String ta = taPercentField.getText();
        String da = daPercentField.getText();
        String lta = ltaPercentField.getText();
        
        if (ta.equals("") || da.equals("") || lta.equals("")) {
            JOptionPane.showMessageDialog(null, "TA,DA,LTA cannot be empty");
            return;
        }
        
        String medical = medicalAllowanceField.getText();
        if (medical.equals("")) {
            medical = "0";
        }

        String phone = phonewifiAllowanceField.getText();
        if (phone.equals("")) {
            phone = "0";
        }

        String other = otherAllowanceField.getText();
        if (other.equals("")) {
            other = "0";
        }
        
        String ptax = profTaxField.getText();
        if (ptax.equals("")) {
            JOptionPane.showMessageDialog(null, "Professional Tax cannot be empty");
            return;
        }

        String pfund = pfField.getText();
        if (pfund.equals("")) {
            JOptionPane.showMessageDialog(null, "Provident Fund cannot be empty");
            return;
        }
        
        
        
        updatedGrade = new Grade(grade_id, grade_name, hra1, hra2, hra3, ta, lta, da, medical, phone, other, ptax, pfund);
        
        if(!updatedGrade.checkSimliarGradeId(gradeFromDatabase)){
            JOptionPane.showMessageDialog(null, "Grade ID cannot be changed");
            return;
        }
        if(gradeFromDatabase.equals(updatedGrade)){
            JOptionPane.showMessageDialog(null, "No Changes done in Grade");
            return;
        }
        
        int updateResult = task.updateGrade(updatedGrade);
        if(updateResult == task.SUCCESS){
            JOptionPane.showMessageDialog(null, "Changes done");
            updateGardeFrame.setVisible(false);
            updateGardeFrame = new MainMenu().getMainMenuFrame();
            updateGardeFrame.setVisible(true);
            return;
        }else if(updateResult == task.DB_ERROR){
            JOptionPane.showMessageDialog(null, "Update failed! Please try again");
            return;
        }
        
        
        
    }
    
    private void searchGrade(String grade_id){
        if (grade_id.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Grade Id");
            return;
        }
        gradeFromDatabase = task.searchGradeById(grade_id);
        if (gradeFromDatabase != null) {
            setGradeDetails(gradeFromDatabase);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "No Grade with Grade Id = " + grade_id + " exists");
            return;
        }
        
        
    }
    
    
    private void setGradeDetails(Grade grade) {
        gradeNameField.setText(grade.getGradeName());
        hraPercentFieldA.setText(grade.getHraPercentA());
        hraPercentFieldB.setText(grade.getHraPercentB());
        hraPercentFieldC.setText(grade.getHraPercentC());
        taPercentField.setText(grade.getTaPercent());
        ltaPercentField.setText(grade.getLtaPercent());
        daPercentField.setText(grade.getDaPercent());
        medicalAllowanceField.setText(grade.getMedicalAllowance());
        phonewifiAllowanceField.setText(grade.getPhonewifiAllowance());
        otherAllowanceField.setText(grade.getOtherAllowance());
        profTaxField.setText(grade.getProfTax());
        pfField.setText(grade.getPf());
        
        gradeNameField.setVisible(true);
        for(int i=0;i<allField.length;i++){
            allField[i].setVisible(true);
        }
        updateButton.setEnabled(true);
    }
    
    public JFrame getUpdateGradeFrame(){
        return updateGardeFrame;
    }
    
}
