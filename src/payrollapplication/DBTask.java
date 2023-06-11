/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollapplication;

import Department.Department;
import Grade.Grade;
import Leave.EmployeeLeave;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    
    public ArrayList<Grade> getListofGradeFromTable(){
        try {
            ArrayList<Grade> grade_list = new ArrayList<>();
            String query = "select i.grade_id,hra1,hra2,hra3,da,ta,lta,medical,phonewifi,anyother,ptax,pfund,name from "
                    + "basic_grade_details i inner join other_grade_details o on i.grade_id = o.grade_id inner "
                    + "join deduction_grade_details d on d.grade_id = i.grade_id inner join grade g on g.id = d.grade_id ; "  ;
            
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
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

                Grade grade = new Grade(grade_id, grade_name, hra1, hra2, 
                        hra3, ta, lta, da, medical, phonewifi, anyother, ptax, pfund);
                
                grade_list.add(grade);          
            }
            
            
            
            return grade_list;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    // GRADE MODULE FUNCTIONS
    
    
    // EMPLOYEE MODULE FUNCTIONS
    public int uploadEmployeeDetails(HashMap<String,String> generalMap,
                                     HashMap<String,String> contactMap,
                                     HashMap<String,String> bankMap,
                                     HashMap<String,String> employeeMap){
        try {            
            String primaryQueryCheck = "select * from employee where employee_id = '" + generalMap.get("ID") + "';";
            ResultSet set = statement.executeQuery(primaryQueryCheck);
            if(set.next()){
                return PRIMARY_KEY_ERROR;
            }else{
                String uploadQueryGeneralDetails = "insert into employee values('" + 
                        generalMap.get("ID") + "','" + 
                        generalMap.get("FIRST_NAME") + "','" + generalMap.get("LAST_NAME") + "','" +
                        generalMap.get("GENDER") + "','" + generalMap.get("DOB") + "','" + 
                        generalMap.get("PAN") + "','" + generalMap.get("AADHAR") + "','" +
                        generalMap.get("STATUS") + "','" + generalMap.get("NATIONALITY") + "');";
                String uploadQueryContactDetails = "insert into employee_contact values('" +
                        generalMap.get("ID") + "','" +
                        contactMap.get("LINE_1") + "','" + contactMap.get("LINE_2") + "','" +
                        contactMap.get("LOCALITY") + "','" + contactMap.get("LANDMARK") + "','" +
                        contactMap.get("PIN") + "','" + contactMap.get("CITY") + "','" +
                        contactMap.get("STATE") + "','" + contactMap.get("COUNTRY") + "','" +
                        contactMap.get("EMAIL") + "','" + contactMap.get("PHONE") + "');";
                
                String uploadQueryBankDetails = "insert into bank_details values('" +
                        generalMap.get("ID") + "','" +
                        bankMap.get("AC_NO") + "','" + bankMap.get("BANK_NAME") + "','" +
                        bankMap.get("IFSC") + "','" + bankMap.get("BRANCH") + "','" +
                        bankMap.get("TYPE") + "','" + bankMap.get("REGISTERED_NAME") + "');";
                
                String uploadQueryHrDetails = "insert into hr_details values('" +
                        generalMap.get("ID") + "','" +
                        employeeMap.get("DEPARTMENT") + "','" + employeeMap.get("GRADE") + "','" +
                        employeeMap.get("BASICS") + "','" + employeeMap.get("DOJ") + "','" +
                        employeeMap.get("LEAVES") + "','" + employeeMap.get("USERNAME") + "','" +
                        employeeMap.get("PASSWORD") + "','" + employeeMap.get("DESIGNATION") +
                        "','" + employeeMap.get("LOGIN_ALLOWED") + "','"+ employeeMap.get("CITY-TYPE")  +"');" ;
                statement.executeUpdate(uploadQueryGeneralDetails);
                statement.executeUpdate(uploadQueryContactDetails);
                statement.executeUpdate(uploadQueryBankDetails);
                statement.executeUpdate(uploadQueryHrDetails);
                
                return SUCCESS;
                
            }
            
            
            
        } catch (SQLException ex) {
            try {
                System.out.println(ex);
                Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
                statement.executeUpdate("delete from employee where employee_id = '" + generalMap.get("ID") + "';");
                return DB_ERROR;
            } catch (SQLException ex1) {
                Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex1);
                return DB_ERROR;
            }
            
           
        }
    }
    
    public HashMap<String,String> getDepartmentListFromDB(){
        try {
            HashMap<String,String> resultMap = new HashMap<>();
            String query = "select dept_id,dept_name from department;";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultMap.put(resultSet.getString(2), resultSet.getString(1));
            }
            
            
            return resultMap;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public HashMap<String,String> getGradeListFromDB(){
        try {
            HashMap<String,String> resultMap = new HashMap<>();
            String query = "select * from grade;";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultMap.put(resultSet.getString(2), resultSet.getString(1));
            }
            
            
            return resultMap;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    // EMPLOYEE MODULE FUNCTIONS
    
    
    // LEAVE MODULE
    
    public int leaveLogResultUpdate(String empid,String leavemonth,
            String leaveYear,String approval,
            String comment, String leave_request_id, String duration){
        
        String query1 = "update  leave_request set status='" + approval + 
                "' where leave_request_id = '" + leave_request_id + "';";
        String query2 = "insert into leave_log values('" + empid + "','" + leavemonth + "','" +
                leaveYear + "','" + approval + "','" + comment + "'," + "now()" + ",'" +
                leave_request_id + "','" + duration + "');";
        try {
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            
            return SUCCESS;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return DB_ERROR;
        }
        
        
        
        
        
    }
    
    public ArrayList<EmployeeLeave> fetchAllLeaveRequest(){
        try {
            ArrayList<EmployeeLeave> resultList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select * from leave_request");
            while(resultSet.next()){
                if(resultSet.getString(7) == null){
                resultList.add(new EmployeeLeave(resultSet.getString(1), 
                        resultSet.getString(2), resultSet.getString(3), 
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
                }
            }
            
            return resultList;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public String getPaidLeavesAllowed(String empId){
        try {
            String query = "select paid_leaves from hr_details where id = '" + empId + "';";
            ResultSet resultSet = statement.executeQuery(query);
            String paidleaves = null;
            while(resultSet.next()){
                paidleaves = resultSet.getString(1);
            }
          
            
            return paidleaves;
        } catch (SQLException ex) {
            
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int getCityByEmployeeId(String empId){
        try {
            String query = "select city_type from hr_details where id= '" + empId + "'";
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println(query);
            String grade = "";
            while(resultSet.next()){
                grade = resultSet.getString(1);
            }
            if(null != grade)switch (grade) {
                case "Tier-1":
                    return 1;
                case "Tier-2":
                    return 2;
                case "Tier-3":
                    return 3;
                default:
                    break;
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
        
    }
    
    public String getLeaveDetailsByEmployeeId(String empId, String mm, String yy)
    {
        try {
            String query = "select sum(duration) from leave_log where leaveapproved='Approved' and "
                    + "empid='" + empId + "' and leavemonth= '" + mm + "' and leaveyear = '" + yy + "';";
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println(query);
            String totalLeave = "0";
            while(resultSet.next()){
                totalLeave = resultSet.getString(1);
            }
            
            return totalLeave;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public String getBasicByEmployeeId(String empId){
         try {
            String query = "select basics from hr_details where id = '" + empId + "';";
            ResultSet resultSet = statement.executeQuery(query);
            String basic = null;
            while(resultSet.next()){
                basic = resultSet.getString(1);
            }
          
            
            return basic;
        } catch (SQLException ex) {
            
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getGradeIdByEmployeeId(String empId){
        try {
            String query = "select grade_id from hr_details where id = '" + empId + "';";
            ResultSet resultSet = statement.executeQuery(query);
            String gradeId = null;
            while(resultSet.next()){
                gradeId = resultSet.getString(1);
            }
          
            
            return gradeId;
        } catch (SQLException ex) {
            
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public HashMap<String,String> fetechAllEmployeeList(){
        try{
            HashMap<String,String> resultMap = new HashMap<>();
            String query = "select employee_id,concat(first_name,' ',last_name) from employee where emp_status='Active';";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                resultMap.put(resultSet.getString(1), resultSet.getString(2));
            }
            
            return resultMap;
            
        }catch(SQLException ex){
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    
    public String getDateOfJoiningOfEmployee(String empid){
        try{
            String query = "select jod from hr_details where id='" + empid + "';";
            ResultSet res = statement.executeQuery(query);
            String result ="";
            
            while(res.next()){
                result = res.getString(1);
            }
            
            return result;
            
        }catch(SQLException ex){
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public HashMap<String,String> fetchAllEmployeeByDepartment(String deptId){
        try {
            HashMap<String,String> resultMap = new HashMap<>();
            
            String query = "select employee_id,concat(first_name,' ',last_name) from employee where employee_id in "
                    + "(select h.id from hr_details h inner join department d on h.dept_id = d.dept_id "
                    + "where d.dept_id = '" + deptId + "');";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultMap.put(resultSet.getString(2),resultSet.getString(1));
            }
            
            return resultMap;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DBTask.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
}
