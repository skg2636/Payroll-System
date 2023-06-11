
package Leave;


public class EmployeeLeave {
    private String empid,empname,no_of_days,leaveDates,reason,leaveid;

    public EmployeeLeave(String empid, String empname, String no_of_days, String leaveDates, String reason, String leaveid) {
        this.empid = empid;
        this.empname = empname;
        this.no_of_days = no_of_days;
        this.leaveDates = leaveDates;
        this.reason = reason;
        this.leaveid = leaveid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(String no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getLeaveDates() {
        return leaveDates;
    }

    public void setLeaveDates(String leaveDates) {
        this.leaveDates = leaveDates;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(String leaveid) {
        this.leaveid = leaveid;
    }

    
    
    
}
