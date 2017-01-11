package entities.softuniDatabase;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "departments", schema = "soft_uni", catalog = "")
public class DepartmentsEntity {
    private Integer departmentId;
    private String name;
    private Integer managerId;
    private EmployeesEntity employeesByManagerId;
    private Collection<EmployeesEntity> employeesByDepartmentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "manager_id",insertable = false,updatable = false)
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentsEntity that = (DepartmentsEntity) o;

        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = departmentId != null ? departmentId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id", nullable = false)
    public EmployeesEntity getEmployeesByManagerId() {
        return employeesByManagerId;
    }

    public void setEmployeesByManagerId(EmployeesEntity employeesByManagerId) {
        this.employeesByManagerId = employeesByManagerId;
    }

    @OneToMany(mappedBy = "departmentsByDepartmentId")
    public Collection<EmployeesEntity> getEmployeesByDepartmentId() {
        return employeesByDepartmentId;
    }

    public void setEmployeesByDepartmentId(Collection<EmployeesEntity> employeesByDepartmentId) {
        this.employeesByDepartmentId = employeesByDepartmentId;
    }
}
