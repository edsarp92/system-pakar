package pakar.system.com.system_pakar.viewmodel;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

import pakar.system.com.system_pakar.event.Callbacks;
import pakar.system.com.system_pakar.model.PageModel;
import pakar.system.com.system_pakar.listener.PageList;
import pakar.system.com.system_pakar.model.ReviewItemModel;
import pakar.system.com.system_pakar.ui.SingleChoiceFragment;

public class BranchPage extends SingleFixedChoicePage {
    private List<Branch> mBranches = new ArrayList<Branch>();

    public BranchPage(Callbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public PageModel findByKey(String key) {
        if (getKey().equals(key)) {
            return this;
        }

        for (Branch branch : mBranches) {
            PageModel found = branch.childPageList.findByKey(key);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    @Override
    public void flattenCurrentPageSequence(ArrayList<PageModel> destination) {
        super.flattenCurrentPageSequence(destination);
        for (Branch branch : mBranches) {
            if (branch.choice.equals(mData.getString(PageModel.SIMPLE_DATA_KEY))) {
                branch.childPageList.flattenCurrentPageSequence(destination);
                break;
            }
        }
    }

    public BranchPage addBranch(String choice, PageModel... childPageModels) {
        PageList childPageList = new PageList(childPageModels);
        for (PageModel pageModel : childPageList) {
            pageModel.setParentKey(choice);
        }
        mBranches.add(new Branch(choice, childPageList));
        return this;
    }
    
    public BranchPage addBranch(String choice) {
        mBranches.add(new Branch(choice, new PageList()));
        return this;
    }

    @Override
    public Fragment createFragment() {
        return SingleChoiceFragment.create(getKey());
    }

    public String getOptionAt(int position) {
        return mBranches.get(position).choice;
    }

    public int getOptionCount() {
        return mBranches.size();
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItemModel> dest) {
        dest.add(new ReviewItemModel(getTitle(), mData.getString(SIMPLE_DATA_KEY), getKey()));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(SIMPLE_DATA_KEY));
    }

    @Override
    public void notifyDataChanged() {
        mCallbacks.onPageTreeChanged();
        super.notifyDataChanged();
    }

    public BranchPage setValue(String value) {
        mData.putString(SIMPLE_DATA_KEY, value);
        return this;
    }

    private static class Branch {
        public String choice;
        public PageList childPageList;

        private Branch(String choice, PageList childPageList) {
            this.choice = choice;
            this.childPageList = childPageList;
        }
    }
}
