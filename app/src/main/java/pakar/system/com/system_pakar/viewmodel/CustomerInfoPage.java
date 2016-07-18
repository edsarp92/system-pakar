package pakar.system.com.system_pakar.viewmodel;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import java.util.ArrayList;

import pakar.system.com.system_pakar.event.Callbacks;
import pakar.system.com.system_pakar.model.PageModel;
import pakar.system.com.system_pakar.model.ReviewItemModel;
import pakar.system.com.system_pakar.ui.CustomerInfoFragment;

public class CustomerInfoPage extends PageModel {
    public static final String NAME_DATA_KEY = "nama";
    public static final String GENDER_DATA_KEY="gender";
    public static final String OLD_DATA_KEY="old";
    public static final String EMAIL_DATA_KEY = "email";


    public CustomerInfoPage(Callbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return CustomerInfoFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItemModel> dest) {
        dest.add(new ReviewItemModel("Your name", mData.getString(NAME_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItemModel("Your gender", mData.getString(GENDER_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItemModel("Your old", mData.getString(OLD_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItemModel("Your email", mData.getString(EMAIL_DATA_KEY), getKey(), -1));
    }


    public int getOld() {
        return mData.getInt(OLD_DATA_KEY);
    }
    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(NAME_DATA_KEY));
    }
}
