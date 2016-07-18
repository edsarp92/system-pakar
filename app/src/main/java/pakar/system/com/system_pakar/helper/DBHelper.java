package pakar.system.com.system_pakar.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pakar.system.com.system_pakar.BuildConfig;
import pakar.system.com.system_pakar.R;
import pakar.system.com.system_pakar.model.AnamnesaModel;

/**
 * Created by Edsarp on 2016-06-19.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String dbName = "db_sys_pakar";
    private final Context mContext;

    public DBHelper(Context context) {
        super(context, dbName, null, BuildConfig.VERSION_CODE);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + Penyakit.TB_NAME.getValue() + " ("
                + Penyakit.CL_ID.getValue() + " INTEGER PRIMARY KEY , "
                + Penyakit.CL_NAMA.getValue() + " TEXT,"
                + Penyakit.CL_LATIN.getValue() + " TEXT,"
                + Penyakit.CL_DEFINISI.getValue() + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Anamnesa.TB_NAME.getValue() + " ("
                + Anamnesa.CL_ID.getValue() + " INTEGER PRIMARY KEY ,"
                + Anamnesa.CL_KATEGORI.getValue() + " TEXT,"
                + Anamnesa.CL_PERTANYAAN.getValue() + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Diagnosa.TB_NAME.getValue() + " ("
                + Diagnosa.CL_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Diagnosa.CL_FORMULA.getValue() + " TEXT,"
                + Diagnosa.CL_ID_PENYAKIT.getValue() + " INTEGER,"
                + Diagnosa.CL_SOLUSI.getValue() + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Profile.TB_NAME.getValue() + " ("
                + Profile.CL_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Profile.CL_NAMA.getValue() + " TEXT,"
                + Profile.CL_KELAMIN.getValue() + " TEXT,"
                + Profile.CL_UMUR.getValue() + " INTEGER,"
                + Profile.CL_EMAIL.getValue() + " TEXT)");
        initDb(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Penyakit.TB_NAME.getValue());
        db.execSQL("DROP TABLE IF EXISTS " + Anamnesa.TB_NAME.getValue());
        db.execSQL("DROP TABLE IF EXISTS " + Diagnosa.TB_NAME.getValue());
        db.execSQL("DROP TABLE IF EXISTS " + Profile.TB_NAME.getValue());
        onCreate(db);
    }

    void initDb(SQLiteDatabase db) {
        initPenyakit(db);
        initAnamnesa(db);
        initDiagnosa(db);
    }

    void initPenyakit(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        Resources res = mContext.getResources();
        String[] penyakitStrings = res.getStringArray(R.array.Penyakit);
        for (int i = 0; i < penyakitStrings.length; i++) {
            String[] value = penyakitStrings[i].split("|");
            cv.put(Penyakit.CL_ID.getValue(), value[0]);
            cv.put(Penyakit.CL_NAMA.getValue(), value[1]);
            cv.put(Penyakit.CL_LATIN.getValue(), value[2]);
            cv.put(Penyakit.CL_DEFINISI.getValue(), value[3]);
            db.insert(Penyakit.TB_NAME.getValue(), Penyakit.CL_ID.getValue(), cv);
        }
    }

    void initAnamnesa(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        Resources res = mContext.getResources();
        String[] anamnesaStrings = res.getStringArray(R.array.Anamnesa);
        for (int i = 0; i < anamnesaStrings.length; i++) {
            String[] value = anamnesaStrings[i].split("|");
            cv.put(Anamnesa.CL_ID.getValue(), value[0]);
            cv.put(Anamnesa.CL_KATEGORI.getValue(), value[1]);
            cv.put(Anamnesa.CL_PERTANYAAN.getValue(), value[2]);
            db.insert(Anamnesa.TB_NAME.getValue(), Anamnesa.CL_ID.getValue(), cv);
        }
    }

    void initDiagnosa(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        Resources res = mContext.getResources();
        String[] diagnosaStrings = res.getStringArray(R.array.Diagnosa);
        for (int i = 0; i < diagnosaStrings.length; i++) {
            String[] value = diagnosaStrings[i].split("|");
            cv.put(Diagnosa.CL_FORMULA.getValue(), value[0]);
            cv.put(Diagnosa.CL_ID_PENYAKIT.getValue(), value[1]);
            cv.put(Diagnosa.CL_SOLUSI.getValue(), value[2]);
            db.insert(Diagnosa.TB_NAME.getValue(), Diagnosa.CL_ID.getValue(), cv);
        }
    }

    public List<AnamnesaModel> getAnamnesaByKategori(String kategori) {
        List<AnamnesaModel> anamnesaModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Anamnesa.TB_NAME.getValue() + " WHERE " + Anamnesa.CL_KATEGORI.getValue() + "=?", new String[]{kategori});
        AnamnesaModel anamnesa = null;
        if (c.moveToFirst()) {
            do {
                anamnesa = new AnamnesaModel(c.getInt(0), c.getString(1), c.getString(2));
                anamnesaModels.add(anamnesa);
            } while (c.moveToNext());
        }
        return anamnesaModels;
    }
}
