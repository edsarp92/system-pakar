package pakar.system.com.system_pakar.helper;

/**
 * Created by Edsarp on 2016-06-19.
 */
public enum Profile {

    TB_NAME("profile"),
    CL_ID("id"),
    CL_NAMA("nama"),
    CL_KELAMIN("kelamin"),
    CL_UMUR("umur"),
    CL_EMAIL("email");

    private String value;

    Profile(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
