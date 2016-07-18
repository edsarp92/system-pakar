package pakar.system.com.system_pakar.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import pakar.system.com.system_pakar.event.Callbacks;
import pakar.system.com.system_pakar.event.PageTreeNode;


public abstract class PageModel implements PageTreeNode {

    public static final String SIMPLE_DATA_KEY = "_";

    protected Callbacks mCallbacks;

    protected Bundle mData = new Bundle();
    protected String mTitle;
    protected boolean mRequired = false;
    protected String mParentKey;

    protected PageModel(Callbacks callbacks, String title) {
        mCallbacks = callbacks;
        mTitle = title;
    }

    public Bundle getData() {
        return mData;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isRequired() {
        return mRequired;
    }

    public void setParentKey(String parentKey) {
        mParentKey = parentKey;
    }

    @Override
    public PageModel findByKey(String key) {
        return getKey().equals(key) ? this : null;
    }

    @Override
    public void flattenCurrentPageSequence(ArrayList<PageModel> dest) {
        dest.add(this);
    }

    public abstract Fragment createFragment();

    public String getKey() {
        return (mParentKey != null) ? mParentKey + ":" + mTitle : mTitle;
    }

    public abstract void getReviewItems(ArrayList<ReviewItemModel> dest);

    public boolean isCompleted() {
        return true;
    }

    public void resetData(Bundle data) {
        mData = data;
        notifyDataChanged();
    }

    public void notifyDataChanged() {
        mCallbacks.onPageDataChanged(this);
    }

    public PageModel setRequired(boolean required) {
        mRequired = required;
        return this;
    }
}
