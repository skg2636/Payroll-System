
package Department;

public class Department {
    private String dept_id,dept_name,manager,location;

    public Department(String dept_id, String dept_name, String manager, String location) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.manager = manager;
        this.location = location;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
    
}
