package sa.gov.pension.employee.info.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Ahmed Anwar
 */
@Entity
@Table(schema = "CG_PROFILE", name = "PPA_EMPLOYEES")
public class Employee {

    @Id
    @Column(name = "PPA_ID")
    private String ppaId;

    @Id
    @Column(name = "NIN")
    private Long nin;

    public String getPpaId() {
        return ppaId;
    }

    public void setPpaId(String ppaId) {
        this.ppaId = ppaId;
    }

    public Long getNin() {
        return nin;
    }

    public void setNin(Long nin) {
        this.nin = nin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(ppaId, employee.ppaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ppaId);
    }
}
