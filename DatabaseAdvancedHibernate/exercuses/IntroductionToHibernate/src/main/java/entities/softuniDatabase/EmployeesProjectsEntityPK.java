package entities.softuniDatabase;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class EmployeesProjectsEntityPK implements Serializable {
    private Integer employeeId;
    private Integer projectId;

    @Column(name = "employee_id",insertable = false,updatable = false)
    @Id
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "project_id",insertable = false,updatable = false)
    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesProjectsEntityPK that = (EmployeesProjectsEntityPK) o;

        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        return result;
    }
}
