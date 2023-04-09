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
public class SearchGrade extends JFrame {

    DBTask task;

    private JLabel basicLabel, otherLabel, deductionLabel;

    private JLabel gradeIdLabel, gradeNameLabel, hraPercentLabelA, hraPercentLabelB, hraPercentLabelC,
            taPercentLabel, ltaPercentLabel, daPercentLabel, medicalAllowanceLabel, phonewifiAllowanceLabel,
            otherAllowanceLabel, profTaxLabel, pfLabel,
            gradeNameField, hraPercentFieldA, hraPercentFieldB, hraPercentFieldC,
            taPercentField, ltaPercentField, daPercentField, medicalAllowanceField, phonewifiAllowanceField,
            otherAllowanceField, profTaxField, pfField;

    private JTextField gradeIdField;

    private JButton searchButton, closeButton;
    private JFrame searchGardeFrame;
    private JPanel otherDetailsPanel, basicDetailsPanel, deductionDetailsPanel;

    public SearchGrade() {
        task = new DBTask();
        searchGardeFrame = new JFrame();
        searchGardeFrame.setTitle("Add New Grade");
        searchGardeFrame.setSize(500, 650);
        searchGardeFrame.setLayout(new GridBagLayout());
        searchGardeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchGardeFrame.setResizable(false);

        basicDetailsPanel = new JPanel();
        basicDetailsPanel.setLayout(null);
        basicDetailsPanel.setPreferredSize(new Dimension(250, 200));
        basicLabel = new JLabel("Basic Allowance Details");
        basicLabel.setFont(new Font("arial", Font.BOLD, 16));
        basicLabel.setBounds(150, 10, 250, 30);
        basicDetailsPanel.add(basicLabel);

        otherDetailsPanel = new JPanel();
        otherDetailsPanel.setLayout(null);
        otherDetailsPanel.setPreferredSize(new Dimension(250, 200));
        otherLabel = new JLabel("Other Allowance Details");
        otherLabel.setFont(new Font("arial", Font.BOLD, 16));
        otherLabel.setBounds(150, 10, 250, 30);
        otherDetailsPanel.add(otherLabel);

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

        gradeNameField = new JLabel("");
        hraPercentFieldA = new JLabel("");
        hraPercentFieldB = new JLabel("");
        hraPercentFieldC = new JLabel("");
        daPercentField = new JLabel("");
        taPercentField = new JLabel("");
        ltaPercentField = new JLabel("");
        medicalAllowanceField = new JLabel("");
        phonewifiAllowanceField = new JLabel("");
        otherAllowanceField = new JLabel("");
        profTaxField = new JLabel("");
        pfField = new JLabel("");

        gradeIdField = new JTextField();

        JLabel allLabel[] = {gradeIdLabel, gradeNameLabel, hraPercentLabelA, hraPercentLabelB, hraPercentLabelC,
            taPercentLabel, ltaPercentLabel, daPercentLabel, medicalAllowanceLabel, phonewifiAllowanceLabel,
            otherAllowanceLabel, profTaxLabel, pfLabel,
            gradeNameField, hraPercentFieldA, hraPercentFieldB, hraPercentFieldC,
            taPercentField, ltaPercentField, daPercentField, medicalAllowanceField, phonewifiAllowanceField,
            otherAllowanceField, profTaxField, pfField};

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
        basicDetailsPanel.add(hraPercentLabelA);
        basicDetailsPanel.add(hraPercentFieldA);
        basicDetailsPanel.add(hraPercentLabelB);
        basicDetailsPanel.add(hraPercentFieldB);
        basicDetailsPanel.add(hraPercentLabelC);
        basicDetailsPanel.add(hraPercentFieldC);
        basicDetailsPanel.add(taPercentLabel);
        basicDetailsPanel.add(taPercentField);
        basicDetailsPanel.add(ltaPercentLabel);
        basicDetailsPanel.add(ltaPercentField);
        basicDetailsPanel.add(daPercentLabel);
        basicDetailsPanel.add(daPercentField);

        otherDetailsPanel.add(medicalAllowanceLabel);
        otherDetailsPanel.add(medicalAllowanceField);
        otherDetailsPanel.add(phonewifiAllowanceLabel);
        otherDetailsPanel.add(phonewifiAllowanceField);
        otherDetailsPanel.add(otherAllowanceLabel);
        otherDetailsPanel.add(otherAllowanceField);

        deductionDetailsPanel.add(profTaxLabel);
        deductionDetailsPanel.add(profTaxField);
        deductionDetailsPanel.add(pfLabel);
        deductionDetailsPanel.add(pfField);

        searchButton = new JButton("Search");
        searchButton.setBounds(80, 130, 80, 25);
        deductionDetailsPanel.add(searchButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(280, 130, 80, 25);
        deductionDetailsPanel.add(closeButton);

        closeButton.addActionListener(e -> {
            searchGardeFrame.setVisible(false);
            searchGardeFrame = new MainMenu().getMainMenuFrame();
            searchGardeFrame.setVisible(true);
        });

        searchButton.addActionListener(e -> {
            String grade_id = gradeIdField.getText();
            searchGrade(grade_id);
        });

        Container contentPane = searchGardeFrame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(basicDetailsPanel);
        contentPane.add(otherDetailsPanel);
        contentPane.add(deductionDetailsPanel);
        searchGardeFrame.setVisible(true);

    }

    private void searchGrade(String grade_id) {
        if (grade_id.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Grade Id");
            return;
        }

        Grade grade = task.searchGradeById(grade_id);
        if (grade != null) {
            setGradeDetails(grade);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "No Grade with Grade Id = " + grade_id + " exists");
            return;
        }

    }

    public void setGradeDetails(Grade grade) {
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

    }

    public JFrame getSearchGradeFrame() {
        return searchGardeFrame;
    }

}
