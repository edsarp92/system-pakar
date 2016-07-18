/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pakar.system.com.system_pakar.listener;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import pakar.system.com.system_pakar.event.Callbacks;
import pakar.system.com.system_pakar.model.PageModel;

public abstract class AbstractWizardModel implements Callbacks {
    protected Context mContext;

    private List<Callbacks> mListeners = new ArrayList<Callbacks>();
    private PageList mRootPageList;

    public AbstractWizardModel(Context context) {
        mContext = context;
        mRootPageList = onNewRootPageList();
    }

    protected abstract PageList onNewRootPageList();

    @Override
    public void onPageDataChanged(PageModel pageModel) {
        for (int i = 0; i < mListeners.size(); i++) {
            mListeners.get(i).onPageDataChanged(pageModel);
        }
    }

    @Override
    public void onPageTreeChanged() {
        for (int i = 0; i < mListeners.size(); i++) {
            mListeners.get(i).onPageTreeChanged();
        }
    }

    public PageModel findByKey(String key) {
        return mRootPageList.findByKey(key);
    }

    public void load(Bundle savedValues) {
        for (String key : savedValues.keySet()) {
            mRootPageList.findByKey(key).resetData(savedValues.getBundle(key));
        }
    }

    public void registerListener(Callbacks listener) {
        mListeners.add(listener);
    }

    public Bundle save() {
        Bundle bundle = new Bundle();
        for (PageModel pageModel : getCurrentPageSequence()) {
            bundle.putBundle(pageModel.getKey(), pageModel.getData());
        }
        return bundle;
    }

       public List<PageModel> getCurrentPageSequence() {
        ArrayList<PageModel> flattened = new ArrayList<PageModel>();
        mRootPageList.flattenCurrentPageSequence(flattened);
        return flattened;
    }

    public void unregisterListener(Callbacks listener) {
        mListeners.remove(listener);
    }
}
