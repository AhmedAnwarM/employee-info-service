package sa.gov.pension.employee.info.entity.exceptions;

/**
 * @author Ahmed Anwar
 */
public class NinNotFoundException extends Exception {
    private static final long serialVersionUID = 6575739754820777587L;

    public NinNotFoundException(String ppaId) {
        super("No NIN was found for PPA ID: " + ppaId);
    }

    public NinNotFoundException(String ppaId, Exception e) {
        super("No NIN was found for PPA ID: " + ppaId, e);
    }
}
