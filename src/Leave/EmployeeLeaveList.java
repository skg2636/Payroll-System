/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leave;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import payrollapplication.DBTask;
import payrollapplication.MainMenu;

/**
 *
 * @author KIIT
 */
public class EmployeeLeaveList extends JFrame {

    private JFrame employeeLeaveListFrame;
    private JTable leaveRequestTable;
    private DefaultTableModel tableModel;
    private DBTask task;

    private String[] columns = {"Sl.no", "ID", "Name", "Day Count", "Dates", "Reason", "Approval"};

    public EmployeeLeaveList() {
        employeeLeaveListFrame = new JFrame();

        leaveRequestTable = new JTable() {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        tableModel = new DefaultTableModel(columns, 0);
        leaveRequestTable.setModel(tableModel);

        task = new DBTask();

        ArrayList<EmployeeLeave> employeeLeaves = task.fetchAllLeaveRequest();

        int count = 0;
        for (EmployeeLeave emp : employeeLeaves) {
            tableModel.addRow(new Object[]{++count, emp.getEmpid(),
                emp.getEmpname(), emp.getNo_of_days(), emp.getLeaveDates(), emp.getReason(), "Pending for Approval"});
        }

        leaveRequestTable.setFocusable(false);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(leaveRequestTable), BorderLayout.CENTER);
        

        leaveRequestTable.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable) me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    int column = target.getSelectedColumn();

                    if (column == 6) {
                        if (leaveRequestTable.getValueAt(row, 6) != "Pending for Approval") {
                            return;
                        }
                        String dates = (String) leaveRequestTable.getValueAt(row, 4);
                        String stringDate = dates.split(",")[0];
                        String month = stringDate.split("-")[1];
                        String year = stringDate.split("-")[2];
                        String leave_id = employeeLeaves.get(row).getLeaveid();

                        int result = JOptionPane.showConfirmDialog(null,
                                "Would you like to approve this leave", "Leave Approval",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);

                        if (result == JOptionPane.YES_OPTION) {
                            String name = (String) leaveRequestTable.getValueAt(row, 2);
                            JOptionPane.showMessageDialog(null, "Leave Approved for " + name);
                            tableModel.setValueAt("Approved", row, 6);
                            task.leaveLogResultUpdate(employeeLeaves.get(row).getEmpid(),
                                    month, year, "Approved", "Approved",
                                    leave_id, employeeLeaves.get(row).getNo_of_days());
                        } else if (result == JOptionPane.NO_OPTION) {
                            Object rejectionComment = JOptionPane.showInputDialog(null,
                                    "Enter the Reason for Rejection");
                            task.leaveLogResultUpdate(employeeLeaves.get(row).getEmpid(),
                                    month, year, "Rejected", (String) rejectionComment,
                                    leave_id, employeeLeaves.get(row).getNo_of_days());
                            tableModel.setValueAt("Rejected", row, 6);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, leaveRequestTable.getValueAt(row, column));
                    }

                }
            }
        });
        leaveRequestTable.getColumnModel().getColumn(0).setPreferredWidth(3);
        leaveRequestTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        leaveRequestTable.getColumnModel().getColumn(2).setPreferredWidth(15);
        leaveRequestTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        
        
        JPanel closeButtonPanel = new JPanel(new FlowLayout());
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(100,200,100,25);
        closeButtonPanel.add(closeButton);
        
        
        
        employeeLeaveListFrame.getContentPane().setLayout(new BorderLayout());
        employeeLeaveListFrame.getContentPane().add(tablePanel, BorderLayout.NORTH);
        employeeLeaveListFrame.getContentPane().add(closeButtonPanel,BorderLayout.SOUTH);
        
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                employeeLeaveListFrame.setVisible(false);
                employeeLeaveListFrame = new MainMenu().getMainMenuFrame();
                employeeLeaveListFrame.setVisible(true);
            }
        });
        
        employeeLeaveListFrame.setResizable(false);
        employeeLeaveListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeLeaveListFrame.setTitle("Employee Leave Request");
        employeeLeaveListFrame.setSize(700, 400);
        employeeLeaveListFrame.setLocationRelativeTo(null);
        employeeLeaveListFrame.setVisible(true);

    }

    public JFrame getEmployeeLeaveListFrame() {
        return employeeLeaveListFrame;
    }

}
