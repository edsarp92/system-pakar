package pakar.system.com.system_pakar.helper;

/**
 * Created by Edsarp on 2016-06-19.
 */
public enum  Anamnesa {
    TB_NAME("anamnesa"),
    CL_ID("id"),
    CL_KATEGORI("kategori"),
    CL_PERTANYAAN("pertanyaan");

    private String value;

    Anamnesa(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
