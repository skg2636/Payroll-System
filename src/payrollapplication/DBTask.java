/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollapplication;

import Department.Department;
import Grade.Grade;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIIT
 */
public class DBTask {

    Statement statement;
    Connection connection;
    public static int PRIMARY_KEY_ERROR = -1, SUCCESS = 1, DB_ERROR = 0;

    public DBTask() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll", "root", "Sandeep123");
            statement = connection.createStatement();
            System.out.println("Connection created");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    protected void finalize() throws SQLException {
        connection.close();
        System.out.println("Connection closed");
    }

    // ADMIN MODULE FUNCTIONS
    public int authenticateAdminLogin(String username, String password) {
        try {
            String query = "select * from admin_users where username = '"
                    + username + "' and password = '" + password + "';";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return 1;
            }

            return 0;

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }

    }

    public void writeSuccessfulAdminLoginTime(String username) {
        try {
            String query = "insert into login_record values('" + username + "', now());";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // ADMIN MODULE FUNCTION
    // DEPARTMENT MODULE FUNCTIONS
    public ArrayList<Department> getListofDepartmentFromTable() {
        try {
            ArrayList<Department> resultList = new ArrayList<>();
            String query = "select * from department;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String dep_id = resultSet.getString(1);
                String dep_name = resultSet.getString(2);
                String manager_name = resultSet.getString(3);
                String location = resultSet.getString(4);
                Department dep = new Department(dep_id, dep_name, manager_name, location);
                resultList.add(dep);
            }

            return resultList;
        } catch (SQLException ex) {
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return null;
        }

    }

    public int updateDepartmentTable(String id, String name, String manager, String location) {
        try {
            String query = "update department set dept_name = '" + name + "', manager_name = '" + manager + "',location = '" + location + "' where dept_id = '" + id + "';";
            statement.executeUpdate(query);

            System.out.println(query);
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public Department searchDepartmentById(String dept_id) {
        try {
            Department department;

            String query = "select * from department where dept_id = '" + dept_id + "';";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String manager = resultSet.getString(3);
                String location = resultSet.getString(4);
                department = new Department(id, name, manager, location);

                return department;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int deleteFromDepartmentTable(String dept_id, String dept_name,
            String manager, String location) {

        try {
            String query = "delete from department where dept_id = '" + dept_id + "' and dept_name = '" + dept_name
                    + "' and manager_name ='" + manager + "' and location = '" + location + "';";

            statement.executeUpdate(query);
            System.out.println(query);
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public Department searchDepartmentByName(String name) {
        try {
            Department department;

            String query = "select * from department where dept_name = '" + name + "';";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String id = resultSet.getString(1);
                String name_ = resultSet.getString(2);
                String manager = resultSet.getString(3);
                String location = resultSet.getString(4);
                department = new Department(id, name_, manager, location);

                return department;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int addDepartmentRowToTable(String departmentID, String departmentName, String managerName, String location) {
        try {
            String primaryKeyCheckQuery = "select * from department where dept_id = '" + departmentID + "';";
            ResultSet resultSet = statement.executeQuery(primaryKeyCheckQuery);

            if (resultSet.next()) {
                return -1;
            }
            String query = "insert into department values('" + departmentID + "','" + departmentName
                    + "','" + managerName + "','" + location + "');";
            System.out.println(query);
            statement.executeUpdate(query);
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    // DEPARTMENT MODULE FUNCTIONS
    // GRADE MODULE FUNCTIONS
    public int addGradeDetails(String id, String name) {
        try {
            String checkQuery = "select * from grade where id = '" + id + "';";
            System.out.println(checkQuery);

            ResultSet resultSet = statement.executeQuery(checkQuery);
            if (resultSet.next()) {
                return PRIMARY_KEY_ERROR;
            }
            String query = "insert into grade values('" + id + "','" + name + "');";
            System.out.println(query);
            statement.executeUpdate(query);

            return SUCCESS;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return DB_ERROR;
        }
    }

    public int uploadBasicGradeDetails(String id, String hra1, String hra2, String hra3, String da, String lta, String ta) {
        try {
            String query = "insert into basic_grade_details  values('" + id + "','" + hra1
                    + "','" + hra2 + "','" + hra3 + "','" + da + "','" + ta + "','" + lta + "');";
            System.out.println(query);
            statement.executeUpdate(query);

            return SUCCESS;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return DB_ERROR;
        }
    }

    public int uploadOtherGradeDetails(String id, String medical, String phonewifi, String other) {

        try {
            String query = "insert into other_grade_details values('" + id + "','" + medical + "','"
                    + phonewifi + "','" + other + "');";
            System.out.println(query);
            statement.executeUpdate(query);

            return SUCCESS;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return DB_ERROR;
        }

    }

    public int uploadDeductionGradeDetails(String id, String ptax, String pfund) {

        try {
            String query = "insert into deduction_grade_details values('" + id + "','" + ptax + "','" + pfund + "');";
            System.out.println(query);
            statement.executeUpdate(query);
            return SUCCESS;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return DB_ERROR;
        }

    }

    public void performGradeDeleteOnError(String grade_id) {
        try {
            String query = "delete from grade where id= '" + grade_id + "';";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int updateGrade(Grade updatedGrade) {

        try {
            String grade_id = updatedGrade.getGradeId();
            String grade_name = updatedGrade.getGradeName();
            String hra1 = updatedGrade.getHraPercentA();
            String hra2 = updatedGrade.getHraPercentB();
            String hra3 = updatedGrade.getHraPercentC();
            String da = updatedGrade.getDaPercent();
            String ta = updatedGrade.getTaPercent();
            String lta = updatedGrade.getLtaPercent();
            String medical = updatedGrade.getMedicalAllowance();
            String phonewifi = updatedGrade.getPhonewifiAllowance();
            String anyother = updatedGrade.getOtherAllowance();
            String ptax = updatedGrade.getProfTax();
            String pfund = updatedGrade.getPf();
            
            
            
            String grade_name_query = "update grade set name = '" + grade_name + "' where id = '" + grade_id + "';";
            String basic_update_query = "update basic_grade_details set hra1 = '" + hra1 + "', hra2 = '" +
                    hra2 + "', hra3='" + hra3 + "', da ='" + da + "', ta='" + ta + "', lta='" + lta +
                    "' where grade_id = '" + grade_id + "';";
            
            String other_update_query = "update other_grade_details set medical = '" + medical +
                    "', phonewifi = '" + phonewifi + "', anyother = '" + anyother +
                    "' where grade_id = '" + grade_id + "';";
            
            String deduction_update_query = "update deduction_grade_details set ptax='" + ptax +
                    "', pfund='" +pfund + "' where grade_id='" + grade_id + "';";
            
            System.out.println(grade_name_query);
            System.out.println(basic_update_query);
            System.out.println(other_update_query);
            System.out.println(deduction_update_query);

            statement.executeUpdate(grade_name_query);
            statement.executeUpdate(basic_update_query);
            statement.executeUpdate(other_update_query);
            statement.executeUpdate(deduction_update_query);

            return SUCCESS;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return DB_ERROR;
        }

    }

    public Grade searchGradeById(String id) {
        try {
            Grade grade = null;
            String query = "select i.grade_id,hra1,hra2,hra3,da,ta,lta,medical,phonewifi,anyother,ptax,pfund,name from "
                    + "basic_grade_details i inner join other_grade_details o on i.grade_id = o.grade_id inner "
                    + "join deduction_grade_details d on d.grade_id = i.grade_id inner join grade g on g.id = d.grade_id  " + "where i.grade_id = '" + id + "';";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {

                String grade_id = resultSet.getString(1);
                String hra1 = resultSet.getString(2);
                String hra2 = resultSet.getString(3);
                String hra3 = resultSet.getString(4);
                String da = resultSet.getString(5);
                String ta = resultSet.getString(6);
                String lta = resultSet.getString(7);
                String medical = resultSet.getString(8);
                String phonewifi = resultSet.getString(9);
                String anyother = resultSet.getString(10);
                String ptax = resultSet.getString(11);
                String pfund = resultSet.getString(12);
                String grade_name = resultSet.getString(13);

                grade = new Grade(grade_id, grade_name, hra1, hra2, hra3, ta, lta, da, medical, phonewifi, anyother, ptax, pfund);

                return grade;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public int deleteGradeById(String grade_id){
        try {
            String query = "delete from grade where id = '" + grade_id + "';";
            
            System.out.println(query);
            
            statement.executeUpdate(query);
            
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return DB_ERROR;
        }
    }

    // GRADE MODULE FUNCTIONS
}
