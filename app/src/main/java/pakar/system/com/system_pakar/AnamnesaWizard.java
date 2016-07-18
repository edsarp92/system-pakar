
package pakar.system.com.system_pakar;

import android.content.Context;

import pakar.system.com.system_pakar.helper.DBHelper;
import pakar.system.com.system_pakar.listener.AbstractWizardModel;
import pakar.system.com.system_pakar.listener.PageList;
import pakar.system.com.system_pakar.viewmodel.BranchPage;
import pakar.system.com.system_pakar.viewmodel.CustomerInfoPage;
import pakar.system.com.system_pakar.viewmodel.SingleFixedChoicePage;

public class AnamnesaWizard extends AbstractWizardModel {
    DBHelper db = null;

    public AnamnesaWizard(Context context) {
        super(context);
        db = new DBHelper(context);

    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(

                new CustomerInfoPage(this, "Profile")
                        .setRequired(true),

                new BranchPage(this, "Kategori")
                        .addBranch("Dewasa",
                                new SingleFixedChoicePage(this, "Anda sakit?")
                                        .setChoices("Yes", "No")
                                        .setRequired(true),
                                new SingleFixedChoicePage(this, "Anda meriang?")
                                        .setChoices("Yes", "No")
                                        .setRequired(true),
                                new SingleFixedChoicePage(this, "Anda flu?")
                                        .setChoices("Yes", "No")
                                        .setRequired(true),
                                new SingleFixedChoicePage(this, "Anda batuk?")
                                        .setChoices("Yes", "No")
                                        .setRequired(true))
                        .addBranch("Anak",
                                new SingleFixedChoicePage(this, "Anda sakit?")
                                        .setChoices("Yes", "No")
                                        .setRequired(true),
                                new SingleFixedChoicePage(this, "Anda sakit?")
                                        .setChoices("Yes", "No")
                                        .setRequired(true)
                        .setRequired(true))


        );
    }
}
