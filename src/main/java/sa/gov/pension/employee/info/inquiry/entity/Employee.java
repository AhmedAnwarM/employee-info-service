package sa.gov.pension.employee.info.inquiry.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Ahmed Anwar
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "CG_PROFILE", name = "PPA_EMPLOYEES")
public class Employee implements Serializable {

    private static final long serialVersionUID = -2061604832116969063L;

    @Id
    @Column(name = "PPA_ID")
    @EqualsAndHashCode.Include
    private String ppaId;

    @Column(name = "NIN")
    private Long nin;
}
