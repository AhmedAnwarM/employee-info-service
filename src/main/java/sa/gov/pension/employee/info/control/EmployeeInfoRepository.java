package sa.gov.pension.employee.info.control;

import sa.gov.pension.employee.info.entity.Employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Ahmed Anwar
 */
@RequestScoped
public class EmployeeInfoRepository {

    @Inject
    EntityManager em;

    public Long getEmployeeNin(String ppaId) {
        return em.find(Employee.class, ppaId).getNin();
    }
}
