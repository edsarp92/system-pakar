
package pakar.system.com.system_pakar.viewmodel;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

import pakar.system.com.system_pakar.event.Callbacks;
import pakar.system.com.system_pakar.model.PageModel;
import pakar.system.com.system_pakar.model.ReviewItemModel;
import pakar.system.com.system_pakar.ui.SingleChoiceFragment;

public class SingleFixedChoicePage extends PageModel {
    protected ArrayList<String> mChoices = new ArrayList<String>();

    public SingleFixedChoicePage(Callbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return SingleChoiceFragment.create(getKey());
    }

    public String getOptionAt(int position) {
        return mChoices.get(position);
    }

    public int getOptionCount() {
        return mChoices.size();
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItemModel> dest) {
        dest.add(new ReviewItemModel(getTitle(), mData.getString(SIMPLE_DATA_KEY), getKey()));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(SIMPLE_DATA_KEY));
    }

    public SingleFixedChoicePage setChoices(String... choices) {
        mChoices.addAll(Arrays.asList(choices));
        return this;
    }

    public SingleFixedChoicePage setValue(String value) {
        mData.putString(SIMPLE_DATA_KEY, value);
        return this;
    }
}
