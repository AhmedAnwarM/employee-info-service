package sa.gov.pension.employee.info.inquiry.control;

import sa.gov.pension.employee.info.inquiry.entity.Employee;
import sa.gov.pension.employee.info.inquiry.entity.EmployeeNinResponse;
import sa.gov.pension.employee.info.inquiry.entity.exceptions.NinNotFoundException;
import sa.gov.pension.profile.logging.LoggingUtil;
import sa.gov.pension.profile.logging.ProfileLogger;
import sa.gov.pension.profile.objects.common.cache.CacheKey;
import sa.gov.pension.profile.objects.common.cache.Cached;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;
import static sa.gov.pension.profile.logging.config.LoggingConfigKeys.EMP_INFO_LOGGER_NAME;

/**
 * @author Ahmed Anwar
 */
@RequestScoped
public class EmployeeInfoRepository {

    private static final ProfileLogger LOGGER = LoggingUtil.getLogger(EMP_INFO_LOGGER_NAME);

    @Inject
    EntityManager em;

    @Cached
    public EmployeeNinResponse getEmployeeNin(@CacheKey String ppaId) throws NinNotFoundException {
        LOGGER.logDebugMessage("Entering EmployeeInfoRepository.getEmployeeNin: " + ppaId, getStackInfo());
        Employee employee = em.find(Employee.class, ppaId);
        if (employee == null || employee.getNin() == null)
            throw new NinNotFoundException(ppaId);
        return new EmployeeNinResponse(employee.getNin());
    }
}
