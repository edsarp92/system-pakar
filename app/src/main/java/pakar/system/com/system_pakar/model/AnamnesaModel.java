package pakar.system.com.system_pakar.model;

/**
 * Created by Edsarp on 2016-06-19.
 */
public class AnamnesaModel {
    private int id;
    private String kategori;
    private String pertanyaan;

    public AnamnesaModel(int id, String kategori, String pertanyaan) {
        this.id = id;
        this.kategori = kategori;
        this.pertanyaan = pertanyaan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }
}
