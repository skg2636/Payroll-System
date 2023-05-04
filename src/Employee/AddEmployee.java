/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;

/**
 *
 * @author KIIT
 */
public class AddEmployee extends JFrame implements KeyListener {

    private HashMap<String, String> generalDetailsMap = new HashMap<>();
    private HashMap<String, String> contactDetailsMap = new HashMap<>();
    private HashMap<String, String> bankDetailsMap = new HashMap<>();
    private HashMap<String, String> employeeDetailsMap = new HashMap<>();
    private HashMap<String, String> loginDetailsMap = new HashMap<>();
    private HashMap<String,String> deptMap;
    private HashMap<String,String> gradeMap;

    private JFrame addEmployeeFrame;
    private JPanel generalPanel, contactPanel, bankPanel, employeePanel, loginPanel;
    private JTabbedPane tabbedPane;
    private DBTask task;
    private static String VALIDATION_SUCCESS = "Validation Successful";

    // General Label and Fields
    private JLabel fnameLabel, lnameLabel, empIdLabel, genderLabel, nationalityLabel, dobLabel, panLabel,
            aadharLabel, statusLabel;
    private JTextField fnameField, lnameField, empIdField, panField, aadharField;
    private JComboBox dateField, monthField, yearField, nationalityField, statusField;
    private JRadioButton male, female, transgender;
    private ButtonGroup genderButtonGroup;
    private String dates[], months[], year[], status[], nationality[];

    // Contact Label and Fields
    private JLabel addressLine1Label, addressLine2Label, localityLabel, landmarkLabel,
            pinLabel, cityLabel, stateLabel, countryLabel, phoneLabel, emailLabel;

    private JTextField addressLine1Field, addressLine2Field, localityField, pinField, cityField,
            landmarkField, stateField, countryField, phoneField, emailField;

    // Bank Label and Fields
    private JLabel accountNumberLabel, accountNumber2Label, ifscLabel, bankNameLabel, 
            registeredNameLabel, accountTypeLabel, bankAdressLabel;

    private JTextField accountNumberField, ifscField, bankNameField, registeredNameField,
            bankAdressField;

    private JPasswordField accountNumber2Field;
    private JComboBox accountTypeField;
    private String accountType[];
    
    
    // Employee Details Field
    
    private JLabel designationLabel,departmentLabel,gradeLabel,basicsLabel,joiningDateLabel,leavesLabel;
    private JTextField designationField,basicsField,leavesField;
    
    private JComboBox dateBox,monthBox,yearBox,departmentBox,gradeBox;
    private String year2[];
    
    // Login Details
    
    private JLabel loginUserNameLabel,loginPasswordLabel;
    private JTextField loginUserNameField,loginPasswordField;
    private JCheckBox loginCheckBox;

    public AddEmployee() {
        
        task = new DBTask();

        addEmployeeFrame = new JFrame("Add New Employee");
        addEmployeeFrame.setSize(500, 650);
        addEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addEmployeeFrame.setResizable(false);

        generalPanel = new JPanel();
        generalPanel.setLayout(null);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("General", generalPanel);

        contactPanel = new JPanel();
        contactPanel.setLayout(null);
        tabbedPane.addTab("Contact Details", contactPanel);

        bankPanel = new JPanel();
        bankPanel.setLayout(null);
        tabbedPane.addTab("Bank Details", bankPanel);
        
        employeePanel = new JPanel();
        employeePanel.setLayout(null);
        tabbedPane.addTab("HR Details", employeePanel);
        
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        tabbedPane.addTab("Login Details", loginPanel);

        empIdLabel = new JLabel("Employee ID:");
        fnameLabel = new JLabel("First Name:");
        lnameLabel = new JLabel("Last Name:");
        genderLabel = new JLabel("Gender:");
        dobLabel = new JLabel("Date of Birth:");
        panLabel = new JLabel("PAN:");
        aadharLabel = new JLabel("Aadhar No.:");
        statusLabel = new JLabel("Employee Status:");
        nationalityLabel = new JLabel("Nationality:");

        empIdField = new JTextField();
        fnameField = new JTextField();
        lnameField = new JTextField();
        panField = new JTextField(10);
        aadharField = new JTextField(12);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        transgender = new JRadioButton("Transgender");

        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(male);
        genderButtonGroup.add(female);
        genderButtonGroup.add(transgender);

        dates = new String[31];
        for (int i = 1; i <= 31; i++) {
            dates[i - 1] = Integer.toString(i);
        }

        months = new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

        year = new String[56];
        for (int i = 1950; i <= 2005; i++) {
            year[i - 1950] = Integer.toString(i);
        }
        
        year2 = new String[16];
        for (int i = 2010; i <= 2025; i++) {
            year2[i - 2010] = Integer.toString(i);
        }

        status = new String[]{"Active", "Not Active"};

        nationality = new String[]{"India", "Nepal", "Bhutan", "Other"};
        
        accountType = new String[]{"Savings","Current"};

        dateField = new JComboBox(dates);
        monthField = new JComboBox(months);
        yearField = new JComboBox(year);
        statusField = new JComboBox(status);
        nationalityField = new JComboBox(nationality);

        empIdLabel.setBounds(50, 50, 100, 25);
        empIdField.setBounds(200, 50, 150, 25);
        generalPanel.add(empIdLabel);
        generalPanel.add(empIdField);
        fnameLabel.setBounds(50, 90, 100, 25);
        fnameField.setBounds(200, 90, 150, 25);
        generalPanel.add(fnameLabel);
        generalPanel.add(fnameField);
        lnameLabel.setBounds(50, 130, 100, 25);
        lnameField.setBounds(200, 130, 150, 25);
        generalPanel.add(lnameLabel);
        generalPanel.add(lnameField);
        genderLabel.setBounds(50, 180, 100, 25);
        male.setBounds(200, 180, 55, 25);
        female.setBounds(255, 180, 70, 25);
        transgender.setBounds(325, 180, 100, 25);
        generalPanel.add(genderLabel);
        generalPanel.add(male);
        generalPanel.add(female);
        generalPanel.add(transgender);
        dobLabel.setBounds(50, 230, 100, 25);
        dateField.setBounds(200, 230, 40, 25);
        monthField.setBounds(250, 230, 80, 25);
        yearField.setBounds(350, 230, 60, 25);
        generalPanel.add(dobLabel);
        generalPanel.add(dateField);
        generalPanel.add(monthField);
        generalPanel.add(yearField);
        panLabel.setBounds(50, 280, 100, 25);
        panField.setBounds(200, 280, 150, 25);
        generalPanel.add(panLabel);
        generalPanel.add(panField);
        aadharLabel.setBounds(50, 330, 100, 25);
        aadharField.setBounds(200, 330, 150, 25);
        generalPanel.add(aadharLabel);
        generalPanel.add(aadharField);
        statusLabel.setBounds(50, 380, 100, 25);
        statusField.setBounds(200, 380, 150, 25);
        generalPanel.add(statusLabel);
        generalPanel.add(statusField);
        nationalityLabel.setBounds(50, 430, 100, 25);
        nationalityField.setBounds(200, 430, 150, 25);
        generalPanel.add(nationalityLabel);
        generalPanel.add(nationalityField);

        addressLine1Label = new JLabel("Address Line 1:");
        addressLine1Label.setBounds(50, 50, 100, 25);
        contactPanel.add(addressLine1Label);
        addressLine1Field = new JTextField();
        addressLine1Field.setBounds(200, 50, 150, 25);
        contactPanel.add(addressLine1Field);

        addressLine2Label = new JLabel("Address Line 2:");
        addressLine2Label.setBounds(50, 90, 100, 25);
        contactPanel.add(addressLine2Label);
        addressLine2Field = new JTextField();
        addressLine2Field.setBounds(200, 90, 150, 25);
        contactPanel.add(addressLine2Field);

        localityLabel = new JLabel("Locality/Area:");
        localityLabel.setBounds(50, 130, 100, 25);
        contactPanel.add(localityLabel);
        localityField = new JTextField();
        localityField.setBounds(200, 130, 150, 25);
        contactPanel.add(localityField);

        landmarkLabel = new JLabel("Landmark:");
        landmarkLabel.setBounds(50, 170, 100, 25);
        contactPanel.add(landmarkLabel);
        landmarkField = new JTextField();
        landmarkField.setBounds(200, 170, 150, 25);
        contactPanel.add(landmarkField);

        pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(50, 210, 100, 25);
        contactPanel.add(pinLabel);
        pinField = new JTextField();
        pinField.setBounds(200, 210, 150, 25);
        contactPanel.add(pinField);

        cityLabel = new JLabel("City:");
        cityLabel.setBounds(50, 250, 100, 25);
        contactPanel.add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(200, 250, 150, 25);
        contactPanel.add(cityField);

        stateLabel = new JLabel("State:");
        stateLabel.setBounds(50, 290, 100, 25);
        contactPanel.add(stateLabel);
        stateField = new JTextField();
        stateField.setBounds(200, 290, 150, 25);
        contactPanel.add(stateField);

        countryLabel = new JLabel("Country:");
        countryLabel.setBounds(50, 330, 100, 25);
        contactPanel.add(countryLabel);
        countryField = new JTextField();
        countryField.setBounds(200, 330, 150, 25);
        contactPanel.add(countryField);

        emailLabel = new JLabel("Email Id:");
        emailLabel.setBounds(50, 370, 100, 25);
        contactPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(200, 370, 150, 25);
        contactPanel.add(emailField);

        phoneLabel = new JLabel("Mobile No:");
        phoneLabel.setBounds(50, 410, 100, 25);
        contactPanel.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(200, 410, 150, 25);
        contactPanel.add(phoneField);

        accountNumberLabel = new JLabel("Bank A/C No.:");
        accountNumberLabel.setBounds(50, 50, 100, 25);
        bankPanel.add(accountNumberLabel);
        accountNumberField = new JTextField();
        accountNumberField.setBounds(200, 50, 150, 25);
        bankPanel.add(accountNumberField);

        accountNumber2Label = new JLabel("Re-Enter Bank A/C No.:");
        accountNumber2Label.setBounds(50, 90, 130, 25);
        bankPanel.add(accountNumber2Label);
        accountNumber2Field = new JPasswordField();
        accountNumber2Field.setBounds(200, 90, 150, 25);
        bankPanel.add(accountNumber2Field);
        
        bankNameLabel = new JLabel("Bank Name:");
        bankNameLabel.setBounds(50, 130, 100, 25);
        bankPanel.add(bankNameLabel);
        bankNameField = new JTextField();
        bankNameField.setBounds(200, 130, 150, 25);
        bankPanel.add(bankNameField);
        
        ifscLabel = new JLabel("Bank IFSC:");
        ifscLabel.setBounds(50, 170, 100, 25);
        bankPanel.add(ifscLabel);
        ifscField = new JTextField();
        ifscField.setBounds(200, 170, 150, 25);
        bankPanel.add(ifscField);
        
        bankAdressLabel = new JLabel("Branch Name:");
        bankAdressLabel.setBounds(50, 210, 100, 25);
        bankPanel.add(bankAdressLabel);
        bankAdressField = new JTextField();
        bankAdressField.setBounds(200, 210, 150, 25);
        bankPanel.add(bankAdressField);
        
        accountTypeLabel = new JLabel("Bank A/C Type:");
        accountTypeLabel.setBounds(50, 250, 100, 25);
        bankPanel.add(accountTypeLabel);
        accountTypeField = new JComboBox(accountType);
        accountTypeField.setBounds(200, 250, 150, 25);
        bankPanel.add(accountTypeField);
        
        registeredNameLabel = new JLabel("Registered Name:");
        registeredNameLabel.setBounds(50, 290, 120, 25);
        bankPanel.add(registeredNameLabel);
        registeredNameField = new JTextField();
        registeredNameField.setBounds(200, 290, 150, 25);
        bankPanel.add(registeredNameField);
        
        
        designationLabel = new JLabel("Designation:");
        designationLabel.setBounds(50, 50, 120, 25);
        employeePanel.add(designationLabel);
        designationField = new JTextField();
        designationField.setBounds(200, 50, 150, 25);
        employeePanel.add(designationField);
        
        deptMap = new HashMap<>();
        deptMap = task.getDepartmentListFromDB();
        departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 90, 120, 25);
        employeePanel.add(departmentLabel);
        departmentBox = new JComboBox(deptMap.keySet().toArray());
        departmentBox.setBounds(200, 90, 150, 25);
        employeePanel.add(departmentBox);
        
        gradeMap = new HashMap<>();
        gradeMap = task.getGradeListFromDB();
        gradeLabel = new JLabel("Grade:");
        gradeLabel.setBounds(50, 130, 120, 25);
        employeePanel.add(gradeLabel);
        gradeBox = new JComboBox(gradeMap.keySet().toArray());
        gradeBox.setBounds(200, 130, 150, 25);
        employeePanel.add(gradeBox);

        basicsLabel = new JLabel("Basic Pay:");
        basicsLabel.setBounds(50, 170, 120, 25);
        employeePanel.add(basicsLabel);
        basicsField = new JTextField();
        basicsField.setBounds(200, 170, 150, 25);
        employeePanel.add(basicsField);
        
        joiningDateLabel = new JLabel("Joining Date:");
        joiningDateLabel.setBounds(50, 210, 120, 25);
        employeePanel.add(joiningDateLabel);
        dateBox = new JComboBox(dates);
        dateBox.setBounds(200, 210, 40, 25);
        employeePanel.add(dateBox);
        monthBox = new JComboBox(months);
        monthBox.setBounds(250, 210, 80, 25);
        employeePanel.add(monthBox);
        yearBox = new JComboBox(year2);
        yearBox.setBounds(350, 210, 60, 25);
        employeePanel.add(yearBox);
        
        leavesLabel = new JLabel("Monthly Leaves:");
        leavesLabel.setBounds(50, 250, 120, 25);
        employeePanel.add(leavesLabel);
        leavesField = new JTextField();
        leavesField.setBounds(200, 250, 150, 25);
        employeePanel.add(leavesField);
        
        loginUserNameLabel = new JLabel("Username:");
        loginUserNameLabel.setBounds(50, 50, 120, 25);
        loginPanel.add(loginUserNameLabel);
        loginUserNameField = new JTextField();
        loginUserNameField.setBounds(200, 50, 150, 25);
        loginPanel.add(loginUserNameField);
        
        loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setBounds(50, 90, 120, 25);
        loginPanel.add(loginPasswordLabel);
        loginPasswordField = new JTextField();
        loginPasswordField.setBounds(200, 90, 150, 25);
        loginPanel.add(loginPasswordField);
        
        loginCheckBox = new JCheckBox("Allow Employee to Login", false);
        loginCheckBox.setBounds(100,130,180,25);
        loginPanel.add(loginCheckBox);
        
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 170, 100, 25);
        loginPanel.add(submitButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(150, 200, 100, 25);
        loginPanel.add(cancelButton);
        
        submitButton.addActionListener(e -> { 
            
            // First Do the Field Validation and then insert the row to db;
            String validationResult = validateAllField();
             
            if(validationResult.equals(VALIDATION_SUCCESS)){
                addEmployeeDataToDB();
            }else{
                 JOptionPane.showMessageDialog(null, validationResult);
            }
        });
        
        cancelButton.addActionListener(e -> { 
            addEmployeeFrame.setVisible(false);
            addEmployeeFrame = new MainMenu().getMainMenuFrame();
            addEmployeeFrame.setVisible(true);
           
        });
        
        
        

        pinField.addKeyListener(this);
        accountNumberField.addKeyListener(this);
        basicsField.addKeyListener(this);
        leavesField.addKeyListener(this);
        addEmployeeFrame.add(tabbedPane);
        addEmployeeFrame.setVisible(true);
        

    }
    
    private void addEmployeeDataToDB(){
        // general details
        generalDetailsMap.put("ID", empIdField.getText());
        generalDetailsMap.put("FIRST_NAME",fnameField.getText());
        generalDetailsMap.put("LAST_NAME",lnameField.getText());
        if(male.isSelected()){
            generalDetailsMap.put("GENDER","Male");
        }else if(female.isSelected()){
            generalDetailsMap.put("GENDER","Female");
        }else{
            generalDetailsMap.put("GENDER","Transgender");
        }
        String dateofbirth = dates[dateField.getSelectedIndex()] + "-" +
                months[monthField.getSelectedIndex()] + "-" +
                year[yearField.getSelectedIndex()];
        generalDetailsMap.put("DOB",dateofbirth);
        generalDetailsMap.put("PAN",panField.getText());
        generalDetailsMap.put("AADHAR",aadharField.getText());
        generalDetailsMap.put("STATUS",status[statusField.getSelectedIndex()]);
        generalDetailsMap.put("NATIONALITY",nationality[nationalityField.getSelectedIndex()]);
        
        // contact details
        contactDetailsMap.put("LINE_1", addressLine1Field.getText());
        contactDetailsMap.put("LINE_2", addressLine2Field.getText());
        contactDetailsMap.put("LOCALITY", localityField.getText());
        contactDetailsMap.put("LANDMARK", landmarkField.getText());
        contactDetailsMap.put("PIN", pinField.getText());
        contactDetailsMap.put("CITY", cityField.getText());
        contactDetailsMap.put("STATE", stateField.getText());
        contactDetailsMap.put("COUNTRY", countryField.getText());
        contactDetailsMap.put("EMAIL", emailField.getText());
        contactDetailsMap.put("PHONE", phoneField.getText());
        
        // Bank Details
        
        bankDetailsMap.put("AC_NO",accountNumberField.getText());
        bankDetailsMap.put("BANK_NAME",bankNameField.getText());
        bankDetailsMap.put("IFSC",ifscField.getText());
        bankDetailsMap.put("BRANCH",bankAdressField.getText());
        bankDetailsMap.put("TYPE",accountType[accountTypeField.getSelectedIndex()]);
        bankDetailsMap.put("REGISTERED_NAME",registeredNameField.getText());
        
        // HR Details
        
        employeeDetailsMap.put("DESIGNATION", designationField.getText());
        ArrayList<String> ls = new ArrayList<>();
        for(HashMap.Entry<String,String> entry : deptMap.entrySet()){
            ls.add(entry.getKey());
        }
        employeeDetailsMap.put("DEPARTMENT",deptMap.get(ls.get(departmentBox.getSelectedIndex())));
        ls.clear();
        for(HashMap.Entry<String,String> entry : gradeMap.entrySet()){
            ls.add(entry.getKey());
        }
        employeeDetailsMap.put("GRADE",gradeMap.get(ls.get(gradeBox.getSelectedIndex())));
        employeeDetailsMap.put("BASICS",basicsField.getText());
        String dateofjoining = dates[dateBox.getSelectedIndex()] + "-" +
                months[monthBox.getSelectedIndex()] + "-" +
                year2[yearBox.getSelectedIndex()];
        employeeDetailsMap.put("DOJ",dateofjoining);
        employeeDetailsMap.put("LEAVES",leavesField.getText());
        employeeDetailsMap.put("USERNAME",loginUserNameField.getText());
        employeeDetailsMap.put("PASSWORD",loginPasswordField.getText());
        if(loginCheckBox.isSelected()){
            employeeDetailsMap.put("LOGIN_ALLOWED","ALLOWED");
        }else{
            employeeDetailsMap.put("LOGIN_ALLOWED","NOT ALLOWED");
        }
        
        int uploadResult = task.uploadEmployeeDetails(generalDetailsMap,contactDetailsMap,bankDetailsMap,employeeDetailsMap);
        if(uploadResult  == DBTask.PRIMARY_KEY_ERROR){
             JOptionPane.showMessageDialog(null, "Employee Already Exists");
             return;
        }else if(uploadResult == DBTask.SUCCESS){
            JOptionPane.showMessageDialog(null, "Employee Added Sucessfully");
             return;
        }else if(uploadResult == DBTask.DB_ERROR){
            JOptionPane.showMessageDialog(null, "Database Error Please Try Again");
             return;
        }
        
    }
    private String validateAllField(){
        
        if(empIdField.getText().equals("")){
            return "Employee ID is empty";
        }else if(fnameField.getText().equals("")){
            return "First Name of the Employee Cannot be empty";
        }else if(lnameField.getText().equals("")){
            return "Last Name of the Employee Cannot be empty";
        }else if(!(male.isSelected() || female.isSelected() || transgender.isSelected())){
            return "Select Gender";
        }else if(panField.getText().equals("")){
            return "PAN cannot left empty";
        }else if(!validatePANNumber(panField.getText())){
            return "Invalid PAN Number";
        }else if(aadharField.getText().equals("")){
            return "Aadhar cannot left empty";
        }else if(!validateAadharNumber(aadharField.getText())){
            return "Invalid Aadhar Number";
        }else if(addressLine1Field.getText().equals("")){
            return "Address Line 1 is empty";
        }else if(addressLine2Field.getText().equals("")){
            return "Address Line 2 is empty";
        }else if(localityField.getText().equals("")){
            return "Locality is empty";
        }else if(pinField.getText().equals("")){
            return "PIN Cannot be left empty";
        }else if(stateField.getText().equals("")){
            return "State Cannot be left empty";
        }else if(countryField.getText().equals("")){
            return "Country Cannot be left empty";
        }else if(emailField.getText().equals("")){
            return "Email Cannot be left empty";
        }else if(phoneField.getText().equals("")){
            return "Phone number Cannot be left empty";
        }else if(!validateEmailId(emailField.getText())){
            return "Invalid Email";
        }else if(!validatePhoneNumber(phoneField.getText())){
            return "Invalid Phone";
        }else if(accountNumberField.getText().equals("")){
            return "Bank Account Number is Empty";
        }else if(!accountNumberField.getText().equals(accountNumber2Field.getText())){
            return "Account Number Not Matching";
        }else if(bankAdressField.getText().equals("")){
            return "Branch Name is empty";
        }else if(bankNameField.getText().equals("")){
            return "Bank Name Cannot be empty";
        }else if(ifscField.getText().equals("")){
            return "IFSC Cannot be empty";
        }else if(!validateIFSC(ifscField.getText())){
            return "IFSC is not valid";
        }else if(registeredNameField.getText().equals("")){
            return "Bank Registered Name is empty";
        }else if(designationField.getText().equals("")){
            return "Designation is empty";
        }else if(basicsField.getText().equals("")){
            return "Basic Pay Field Cannot Empty";
        }else if(leavesField.getText().equals("")){
            return "Leaves Field Cannot be left Empty";
        }else if(Integer.parseInt(leavesField.getText()) > 8){
            return "Leaves Cannot be greater than 8";
        }else if(loginUserNameField.getText().equals("")){
            return "Username cannot be left empty";
        }else if(loginPasswordField.getText().equals("")){
            return "Password Cannot be left empty";
        }else if(cityField.getText().equals("")){
            return "City Cannot be left empty";
        }  
        return VALIDATION_SUCCESS;
    }
    
    private boolean validateIFSC(String ifsc){
        String regex = "^[A-Z]{4}0[A-Z0-9]{6}$";
        Pattern p = Pattern.compile(regex);
        if(ifsc == null){
            return false;
        }
        Matcher m = p.matcher(ifsc);
        return m.matches();
    }
    
    private boolean validateEmailId(String email){
         String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; 
         Pattern p = Pattern.compile(regex);
         if(email == null){
             return false;
         }
         
         Matcher m = p.matcher(email);
         return m.matches();
    }
    
    private boolean validatePhoneNumber(String phone){
        String regex = "[0-9]{10}";
        Pattern p = Pattern.compile(regex);
        if(phone == null){
            return false;
        }
        Matcher m = p.matcher(phone);
        return m.matches();
    }
    private boolean validatePANNumber(String panno){
        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        Pattern p = Pattern.compile(regex);
        if (panno == null)
        {
            return false;
        }

        Matcher m = p.matcher(panno);   
        return m.matches();
    }
    
    private boolean validateAadharNumber(String number){
        String regex = "[0-9]{12}";
        Pattern p = Pattern.compile(regex);
        if(number == null){
            return false;
        }
        
        Matcher m = p.matcher(number);
        return m.matches();
    }
    
    

    public JFrame getAddEmployeeFrame() {
        return addEmployeeFrame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            return;
        }

        if (e.getSource() == accountNumberField) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                accountNumberField.setText("");
            }

        }
        if (e.getSource() == basicsField) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                basicsField.setText("");
            }

        }
        
        if (e.getSource() == leavesField) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                leavesField.setText("");
            }

        }

        if (e.getSource() == pinField) {

            if (pinField.getText().length() > 6) {
                String pin = pinField.getText();
                pinField.setText(pin.substring(0, 6));
            }

            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    pinField.setText("");
                }
            }

        }

    }

    private static void getPINCodeDetails(String pincode) {
        try {
            String apiUrl = "https://api.postalpincode.in/pincode/" + pincode;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            StringBuilder builder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

            String response = builder.toString();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println(response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
