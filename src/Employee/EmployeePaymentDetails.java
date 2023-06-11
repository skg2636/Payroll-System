
package Employee;

import Grade.Grade;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;

public class EmployeePaymentDetails extends JFrame {

    private JFrame employeePaymentDetailFrame;
    private DBTask task;
    private HashMap<String, String> deptMap, employeeMap;
    private JLabel monthLabel, yearLabel, departmentLabel, nameLabel, earningLabel, deductionLabel;
    private JComboBox monthBox, yearBox, departmentBox, nameBox;
    private String[] yearList, monthList, departmentList, nameList;
    private JButton showButton;
    private JLabel basicLabel, hraLabel, daLabel, taLabel, ltaLabel, totalLabel, medicalLabel, phoneLabel, otherLabel, ptaxLabel, pfundLabel, taxLabel, totalDeductionLabel, extraLeaveDeductionLabel;
    private JLabel basicField, hraField, daField, taField, ltaField, totalField, medicalField, phoneField, otherField, ptaxField, pfundField, taxField, totalDeductionField, extraLeaveDeductionField;
    private JLabel netPayment, netPaymentInWord;
    private static final String[] units = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
        "eighteen", "nineteen"
    };

    private static final String[] tens = {
        "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public EmployeePaymentDetails() {

        task = new DBTask();

        employeePaymentDetailFrame = new JFrame();
        employeePaymentDetailFrame.setSize(500, 650);

        monthLabel = new JLabel("Select the month: ");
        monthLabel.setBounds(60, 20, 150, 25);
        employeePaymentDetailFrame.add(monthLabel);

        yearLabel = new JLabel("Select the year: ");
        yearLabel.setBounds(60, 50, 150, 25);
        employeePaymentDetailFrame.add(yearLabel);

        departmentLabel = new JLabel("Select Department: ");
        departmentLabel.setBounds(60, 80, 150, 25);
        employeePaymentDetailFrame.add(departmentLabel);

        nameLabel = new JLabel("Select the employee: ");
        nameLabel.setBounds(60, 110, 150, 25);
        employeePaymentDetailFrame.add(nameLabel);

        monthList = new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

        yearList = new String[14];

        for (int i = 2010; i <= 2023; i++) {
            yearList[i - 2010] = Integer.toString(i);
        }

        monthBox = new JComboBox(monthList);
        monthBox.setBounds(250, 20, 150, 25);
        employeePaymentDetailFrame.add(monthBox);

        yearBox = new JComboBox(yearList);
        yearBox.setBounds(250, 50, 150, 25);
        employeePaymentDetailFrame.add(yearBox);

        deptMap = new HashMap<>();
        deptMap = task.getDepartmentListFromDB();
        departmentBox = new JComboBox(deptMap.keySet().toArray());
        departmentBox.setBounds(250, 80, 150, 25);
        employeePaymentDetailFrame.add(departmentBox);

        employeeMap = task.fetchAllEmployeeByDepartment("D303");
        nameBox = new JComboBox();

        for (Map.Entry<String, String> entry : employeeMap.entrySet()) {
            nameBox.addItem(entry.getKey());
        }

        nameBox.setBounds(250, 110, 150, 25);
        employeePaymentDetailFrame.add(nameBox);

        showButton = new JButton("Search");
        showButton.setBounds(160, 150, 150, 25);
        employeePaymentDetailFrame.add(showButton);

        JSeparator sep1 = new JSeparator(SwingConstants.HORIZONTAL);
        sep1.setBounds(0, 180, 500, 10);
        employeePaymentDetailFrame.add(sep1);

        JSeparator sep2 = new JSeparator(SwingConstants.VERTICAL);
        sep2.setBounds(250, 180, 10, 315);
        employeePaymentDetailFrame.add(sep2);

        earningLabel = new JLabel("Earnings");
        earningLabel.setBounds(95, 185, 150, 25);
        employeePaymentDetailFrame.add(earningLabel);

        basicLabel = new JLabel("Basic Salary:");
        basicLabel.setBounds(30, 220, 150, 25);
        employeePaymentDetailFrame.add(basicLabel);
        basicLabel.setFont(new Font("Arial", Font.BOLD, 12));

        basicField = new JLabel();
        basicField.setBounds(190, 220, 100, 25);
        employeePaymentDetailFrame.add(basicField);
        basicField.setFont(new Font("Arial", Font.BOLD, 12));

        hraLabel = new JLabel("House Rent Allowance:");
        hraLabel.setBounds(30, 250, 150, 25);
        employeePaymentDetailFrame.add(hraLabel);
        hraLabel.setFont(new Font("Arial", Font.BOLD, 12));

        hraField = new JLabel();
        hraField.setBounds(190, 250, 100, 25);
        employeePaymentDetailFrame.add(hraField);
        hraField.setFont(new Font("Arial", Font.BOLD, 12));

        daLabel = new JLabel("Dearness Allowance:");
        daLabel.setBounds(30, 280, 150, 25);
        employeePaymentDetailFrame.add(daLabel);
        daLabel.setFont(new Font("Arial", Font.BOLD, 12));

        daField = new JLabel();
        daField.setBounds(190, 280, 100, 25);
        employeePaymentDetailFrame.add(daField);
        daField.setFont(new Font("Arial", Font.BOLD, 12));

        taLabel = new JLabel("Travel Allowance:");
        taLabel.setBounds(30, 310, 150, 25);
        employeePaymentDetailFrame.add(taLabel);
        taLabel.setFont(new Font("Arial", Font.BOLD, 12));

        taField = new JLabel();
        taField.setBounds(190, 310, 100, 25);
        employeePaymentDetailFrame.add(taField);
        taField.setFont(new Font("Arial", Font.BOLD, 12));

        ltaLabel = new JLabel("Leave Travel Allowance:");
        ltaLabel.setBounds(30, 340, 150, 25);
        employeePaymentDetailFrame.add(ltaLabel);
        ltaLabel.setFont(new Font("Arial", Font.BOLD, 12));

        ltaField = new JLabel();
        ltaField.setBounds(190, 340, 100, 25);
        employeePaymentDetailFrame.add(ltaField);
        ltaField.setFont(new Font("Arial", Font.BOLD, 12));

        medicalLabel = new JLabel("Medical Allowance:");
        medicalLabel.setBounds(30, 370, 150, 25);
        employeePaymentDetailFrame.add(medicalLabel);
        medicalLabel.setFont(new Font("Arial", Font.BOLD, 12));

        medicalField = new JLabel();
        medicalField.setBounds(190, 370, 100, 25);
        employeePaymentDetailFrame.add(medicalField);
        medicalField.setFont(new Font("Arial", Font.BOLD, 12));

        phoneLabel = new JLabel("Phone-Wifi Allowance:");
        phoneLabel.setBounds(30, 400, 150, 25);
        employeePaymentDetailFrame.add(phoneLabel);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 12));

        phoneField = new JLabel();
        phoneField.setBounds(190, 400, 100, 25);
        employeePaymentDetailFrame.add(phoneField);
        phoneField.setFont(new Font("Arial", Font.BOLD, 12));

        otherLabel = new JLabel("Other Allowance:");
        otherLabel.setBounds(30, 430, 150, 25);
        employeePaymentDetailFrame.add(otherLabel);
        otherLabel.setFont(new Font("Arial", Font.BOLD, 12));

        otherField = new JLabel();
        otherField.setBounds(190, 430, 100, 25);
        employeePaymentDetailFrame.add(otherField);
        otherField.setFont(new Font("Arial", Font.BOLD, 12));

        JSeparator sep4 = new JSeparator(SwingConstants.HORIZONTAL);
        sep4.setBounds(0, 460, 500, 10);
        employeePaymentDetailFrame.add(sep4);

        totalLabel = new JLabel("Total Earning:");
        totalLabel.setBounds(30, 465, 150, 25);
        employeePaymentDetailFrame.add(totalLabel);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 12));

        totalField = new JLabel();
        totalField.setBounds(190, 465, 100, 25);
        employeePaymentDetailFrame.add(totalField);
        totalField.setFont(new Font("Arial", Font.BOLD, 12));

        ptaxLabel = new JLabel("Professional Tax:");
        ptaxLabel.setBounds(260, 220, 150, 25);
        employeePaymentDetailFrame.add(ptaxLabel);
        ptaxLabel.setFont(new Font("Arial", Font.BOLD, 12));

        ptaxField = new JLabel();
        ptaxField.setBounds(430, 220, 100, 25);
        employeePaymentDetailFrame.add(ptaxField);
        ptaxField.setFont(new Font("Arial", Font.BOLD, 12));

        pfundLabel = new JLabel("Provident Fund:");
        pfundLabel.setBounds(260, 250, 150, 25);
        employeePaymentDetailFrame.add(pfundLabel);
        pfundLabel.setFont(new Font("Arial", Font.BOLD, 12));

        pfundField = new JLabel();
        pfundField.setBounds(430, 250, 100, 25);
        employeePaymentDetailFrame.add(pfundField);
        pfundField.setFont(new Font("Arial", Font.BOLD, 12));

        taxLabel = new JLabel("Tax Deduction:");
        taxLabel.setBounds(260, 280, 150, 25);
        employeePaymentDetailFrame.add(taxLabel);
        taxLabel.setFont(new Font("Arial", Font.BOLD, 12));

        taxField = new JLabel();
        taxField.setBounds(430, 280, 100, 25);
        employeePaymentDetailFrame.add(taxField);
        taxField.setFont(new Font("Arial", Font.BOLD, 12));

        extraLeaveDeductionLabel = new JLabel("Leave Deduction:");
        extraLeaveDeductionLabel.setBounds(260, 310, 150, 25);
        employeePaymentDetailFrame.add(extraLeaveDeductionLabel);
        extraLeaveDeductionLabel.setFont(new Font("Arial", Font.BOLD, 12));

        extraLeaveDeductionField = new JLabel();
        extraLeaveDeductionField.setBounds(430, 310, 100, 25);
        employeePaymentDetailFrame.add(extraLeaveDeductionField);
        extraLeaveDeductionField.setFont(new Font("Arial", Font.BOLD, 12));

        totalDeductionLabel = new JLabel("Total Deduction:");
        totalDeductionLabel.setBounds(260, 465, 150, 25);
        employeePaymentDetailFrame.add(totalDeductionLabel);
        totalDeductionLabel.setFont(new Font("Arial", Font.BOLD, 12));

        totalDeductionField = new JLabel();
        totalDeductionField.setBounds(430, 465, 100, 25);
        employeePaymentDetailFrame.add(totalDeductionField);
        totalDeductionField.setFont(new Font("Arial", Font.BOLD, 12));

        netPayment = new JLabel();
        netPayment.setBounds(30, 505, 400, 25);
        employeePaymentDetailFrame.add(netPayment);

        netPaymentInWord = new JLabel();
        netPaymentInWord.setBounds(30, 535, 400, 25);
        employeePaymentDetailFrame.add(netPaymentInWord);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(160, 570, 150, 25);
        employeePaymentDetailFrame.add(closeButton);

        JSeparator sep5 = new JSeparator(SwingConstants.HORIZONTAL);
        sep5.setBounds(0, 495, 500, 10);
        employeePaymentDetailFrame.add(sep5);

        deductionLabel = new JLabel("Deductions");
        deductionLabel.setBounds(340, 185, 150, 25);
        employeePaymentDetailFrame.add(deductionLabel);

        JSeparator sep3 = new JSeparator(SwingConstants.HORIZONTAL);
        sep3.setBounds(0, 210, 500, 10);
        employeePaymentDetailFrame.add(sep3);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePaymentDetailFrame.setVisible(false);
                employeePaymentDetailFrame = new MainMenu().getMainMenuFrame();
                employeePaymentDetailFrame.setVisible(true);

            }
        });
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String monthSelected = monthList[monthBox.getSelectedIndex()];
                String yearSelected = yearList[yearBox.getSelectedIndex()];
                String selectedEmployee = employeeMap.get(nameBox.getSelectedItem());

                if (checkPastDate(monthSelected, yearSelected)) {
                    if (selectedEmployee == null) {
                        JOptionPane.showMessageDialog(null, "Employee Not Selected");
                        return;
                    } else {
                        calculateAndPopulateAllField(selectedEmployee, monthBox.getSelectedIndex() + 1, yearSelected);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Payment for Selected Month is not generated");
                }

            }
        });

        employeePaymentDetailFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeePaymentDetailFrame.setTitle("Employee Payment Details");
        employeePaymentDetailFrame.setLocationRelativeTo(null);
        employeePaymentDetailFrame.setResizable(false);

        departmentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                String selectedItem = (String) source.getSelectedItem();
                String departmentID = deptMap.get(selectedItem);
                employeeMap = task.fetchAllEmployeeByDepartment(departmentID);
                nameBox.removeAllItems();
                for (Map.Entry<String, String> entry : employeeMap.entrySet()) {
                    nameBox.addItem(entry.getKey());
                }

            }

        });

        employeePaymentDetailFrame.setVisible(true);

    }

    private void calculateAndPopulateAllField(String empId, int mm, String yy) {
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

        basicField.setText(String.valueOf(basicValue));
        hraField.setText(String.valueOf(hraValue));
        daField.setText(String.valueOf(daValue));
        taField.setText(String.valueOf(taValue));
        ltaField.setText(String.valueOf(ltaValue));
        medicalField.setText(String.valueOf(medicalValue));
        phoneField.setText(String.valueOf(phoneValue));
        otherField.setText(String.valueOf(otherValue));
        double totalValue = basicValue + hraValue + daValue + ltaValue + taValue + medicalValue + phoneValue + otherValue;

        double ptaxValue = Double.parseDouble(grade.getProfTax().replace(",", ""));
        double pfundValue = (Double.parseDouble(grade.getPf()) * basicValue) / 100;
        double taxValue = calculateTax(totalValue * 12) / 12;

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

        ptaxField.setText(String.valueOf(ptaxValue));
        pfundField.setText(String.valueOf(pfundValue));
        taxField.setText(String.valueOf(taxValue));
        extraLeaveDeductionField.setText(String.valueOf(leavededuction));

        double totaldeduction = ptaxValue + pfundValue + taxValue + leavededuction;

        totalDeductionField.setText(String.valueOf(totaldeduction));

        netPayment.setText("Net Payment -- Rs. " + String.valueOf(totalValue - totaldeduction) + "/-");

        netPaymentInWord.setText(convertAmountToWords(totalValue - totaldeduction));
        totalField.setText(String.valueOf(totalValue));

    }

    private static String convertAmountToWords(double amount) {
        long wholePart = (long) amount;
        int fractionPart = (int) (Math.round((amount - wholePart) * 100));

        String amountInWords = convertNumberToWords(wholePart) + " rupees";
        if (fractionPart > 0) {
            amountInWords += " and " + convertNumberToWords(fractionPart) + " paise";
        }

        return amountInWords;
    }

    private static String convertNumberToWords(long number) {
        if (number < 20) {
            return units[(int) number];
        }

        if (number < 100) {
            return tens[(int) (number / 10)] + " " + units[(int) (number % 10)];
        }

        if (number < 1000) {
            return units[(int) (number / 100)] + " hundred " + convertNumberToWords(number % 100);
        }

        if (number < 100000) {
            return convertNumberToWords(number / 1000) + " thousand " + convertNumberToWords(number % 1000);
        }

        if (number < 10000000) {
            return convertNumberToWords(number / 100000) + " lakh " + convertNumberToWords(number % 100000);
        }

        return convertNumberToWords(number / 10000000) + " crore " + convertNumberToWords(number % 10000000);
    }

    private boolean checkPastDate(String mm, String yy) {
        Month month = Month.valueOf(mm.toUpperCase(Locale.ENGLISH));
        LocalDate localDate = LocalDate.now();

        LocalDate inputDate = LocalDate.of(Integer.parseInt(yy), month, 1);

        boolean isPastMonth = inputDate.isBefore(localDate.withDayOfMonth(1)) && !inputDate.isEqual(localDate.withDayOfMonth(1));

        return isPastMonth;
    }

    public static double calculateTax(double income) {
        double tax = 0;

        if (income <= 250000) {
            tax = 0;
        } else if (income <= 500000) {
            tax = (income - 250000) * 0.05;
        } else if (income <= 1000000) {
            tax = 250000 * 0.05 + (income - 500000) * 0.20;
        } else {
            tax = 250000 * 0.05 + 500000 * 0.20 + (income - 1000000) * 0.30;
        }

        return tax;

    }

    public JFrame getEmployeePaymentDetailsFrame() {
        return employeePaymentDetailFrame;
    }

}
