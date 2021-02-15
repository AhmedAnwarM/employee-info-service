package sa.gov.pension.employee.info.inquiry.entity.exceptions;

/**
 * @author Ahmed Anwar
 */
public class NinNotFoundException extends Exception {

    private static final long serialVersionUID = 4451114629357272861L;
    private String ppaId;

    public NinNotFoundException() {
        super("No NIN was found");
    }

    public NinNotFoundException(String ppaId) {
        super("No NIN was found for PPA ID: " + ppaId);
        this.ppaId = ppaId;
    }

    public NinNotFoundException(String ppaId, Exception e) {
        super("No NIN was found for PPA ID: " + ppaId, e);
    }

    public String getPpaId() {
        return ppaId;
    }
}
