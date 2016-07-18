package pakar.system.com.system_pakar.helper;

/**
 * Created by Edsarp on 2016-06-19.
 */
public enum Diagnosa {

    TB_NAME("diagnosa"),
    CL_ID("id"),
    CL_FORMULA("formula"),
    CL_ID_PENYAKIT("id_penyakit"),
    CL_SOLUSI("solusi");

    private String value;

    Diagnosa(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
