package sa.gov.pension.employee.info.control;

import sa.gov.pension.employee.info.entity.Employee;
import sa.gov.pension.employee.info.entity.exceptions.NinNotFoundException;

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

    public Long getEmployeeNin(String ppaId) throws NinNotFoundException {
        Employee employee = em.find(Employee.class, ppaId);
        if (employee == null || employee.getNin() == null)
            throw new NinNotFoundException(ppaId);
        return employee.getNin();
    }
}
