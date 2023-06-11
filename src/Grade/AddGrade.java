
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


public class AddGrade {

    private DBTask task;
    private JLabel basicLabel, otherLabel, deductionLabel;

    private JLabel gradeIdLabel, gradeNameLabel, hraPercentLabelA, hraPercentLabelB, hraPercentLabelC,
            taPercentLabel, ltaPercentLabel, daPercentLabel, medicalAllowanceLabel, phonewifiAllowanceLabel,
            otherAllowanceLabel, profTaxLabel, pfLabel;

    private JTextField gradeIdField, gradeNameField;

    private JFormattedTextField hraPercentFieldA, hraPercentFieldB, hraPercentFieldC,
            taPercentField, ltaPercentField, daPercentField, medicalAllowanceField, phonewifiAllowanceField,
            otherAllowanceField, profTaxField, pfField;
    private JButton submitButton, closeButton, clearButton;
    private JFrame addGardeFrame;
    private JPanel otherDetailsPanel, basicDetailsPanel, deductionDetailsPanel;
    private NumberFormat numberFormat;
    private NumberFormatter numberFormatter;

    public AddGrade() {
        task = new DBTask();
        addGardeFrame = new JFrame();
        addGardeFrame.setTitle("Add New Grade");
        addGardeFrame.setSize(500, 650);
        addGardeFrame.setLayout(new GridBagLayout());
        addGardeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addGardeFrame.setResizable(false);

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

        JFormattedTextField allField[] = {hraPercentFieldA, hraPercentFieldB, hraPercentFieldC,
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

        }

        submitButton = new JButton("Submit");
        submitButton.setBounds(60, 130, 80, 25);
        deductionDetailsPanel.add(submitButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(160, 130, 80, 25);
        deductionDetailsPanel.add(clearButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(260, 130, 80, 25);
        deductionDetailsPanel.add(closeButton);

        submitButton.addActionListener(e -> {
            addGradeDetails();
        });

        closeButton.addActionListener(e -> {
            addGardeFrame.setVisible(false);
            addGardeFrame = new MainMenu().getMainMenuFrame();
            addGardeFrame.setVisible(true);
        });
        clearButton.addActionListener(e -> {
            for (int i = 0; i < 11; i++) {
                allField[i].setText("0");
            }
        });

        Container contentPane = addGardeFrame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(basicDetailsPanel);
        contentPane.add(otherDetailsPanel);
        contentPane.add(deductionDetailsPanel);
        addGardeFrame.setVisible(true);

    }

    public JFrame getAddGradeFrame() {
        return addGardeFrame;
    }

    public void addGradeDetails() {

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

        int result = task.addGradeDetails(grade_id, grade_name);
        if (result == DBTask.PRIMARY_KEY_ERROR) {
            JOptionPane.showMessageDialog(null, "Grade Id already exists");
            return;
        } else if (result == DBTask.DB_ERROR) {
            JOptionPane.showMessageDialog(null, "Error! Please try again after sometime");
            return;
        } else if (result == DBTask.SUCCESS) {
            int res1 = task.uploadBasicGradeDetails(grade_id, hra1, hra2, hra3, da, lta, ta);
            int res2 = task.uploadOtherGradeDetails(grade_id, medical, phone, other);
            int res3 = task.uploadDeductionGradeDetails(grade_id, ptax, pfund);

            if (res1 != DBTask.SUCCESS || res2 != DBTask.SUCCESS || res3 != DBTask.SUCCESS) {
                task.performGradeDeleteOnError(grade_id);
                JOptionPane.showMessageDialog(null, "Error! Please try again after sometime");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Grade Added Sucessfully");
                addGardeFrame.setVisible(false);
                addGardeFrame = new MainMenu().getMainMenuFrame();
                addGardeFrame.setVisible(true);
                return;

            }

        }

    }

}
