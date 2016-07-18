package pakar.system.com.system_pakar.viewmodel;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import pakar.system.com.system_pakar.event.Callbacks;
import pakar.system.com.system_pakar.model.PageModel;
import pakar.system.com.system_pakar.model.ReviewItemModel;
import pakar.system.com.system_pakar.ui.MultipleChoiceFragment;

public class MultipleFixedChoicePage extends SingleFixedChoicePage {
    public MultipleFixedChoicePage(Callbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return MultipleChoiceFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItemModel> dest) {
        StringBuilder sb = new StringBuilder();

        ArrayList<String> selections = mData.getStringArrayList(PageModel.SIMPLE_DATA_KEY);
        if (selections != null && selections.size() > 0) {
            for (String selection : selections) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(selection);
            }
        }

        dest.add(new ReviewItemModel(getTitle(), sb.toString(), getKey()));
    }

    @Override
    public boolean isCompleted() {
        ArrayList<String> selections = mData.getStringArrayList(PageModel.SIMPLE_DATA_KEY);
        return selections != null && selections.size() > 0;
    }
}
