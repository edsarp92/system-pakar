package pakar.system.com.system_pakar.model;

public class ReviewItemModel {
    public static final int DEFAULT_WEIGHT = 0;

    private int mWeight;
    private String mTitle;
    private String mDisplayValue;
    private String mPageKey;

    public ReviewItemModel(String title, String displayValue, String pageKey) {
        this(title, displayValue, pageKey, DEFAULT_WEIGHT);
    }

    public ReviewItemModel(String title, String displayValue, String pageKey, int weight) {
        mTitle = title;
        mDisplayValue = displayValue;
        mPageKey = pageKey;
        mWeight = weight;
    }

    public String getDisplayValue() {
        return mDisplayValue;
    }

    public void setDisplayValue(String displayValue) {
        mDisplayValue = displayValue;
    }

    public String getPageKey() {
        return mPageKey;
    }

    public void setPageKey(String pageKey) {
        mPageKey = pageKey;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int weight) {
        mWeight = weight;
    }
}
