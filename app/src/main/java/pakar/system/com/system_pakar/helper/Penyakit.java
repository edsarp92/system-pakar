package pakar.system.com.system_pakar.helper;

/**
 * Created by Edsarp on 2016-06-19.
 */
public enum Penyakit {
    TB_NAME("penyakit"),
    CL_ID("id"),
    CL_NAMA("nama"),
    CL_LATIN("latin"),
    CL_DEFINISI("definis");

    private String value;

    Penyakit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
