package pakar.system.com.system_pakar.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pakar.system.com.system_pakar.R;
import pakar.system.com.system_pakar.event.PageFragmentCallbacks;
import pakar.system.com.system_pakar.viewmodel.CustomerInfoPage;

public class CustomerInfoFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_KEY = "key";
    private SimpleDateFormat dateFormatter;
    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private CustomerInfoPage mPage;
    private TextView mNameView;
    private TextView mBirthDay;
    private TextView mEmailView;
    private RadioButton rdbMale, rdbFemale;
    private RadioGroup mGender;

    private DatePickerDialog datePickerDialog;


    public static CustomerInfoFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        CustomerInfoFragment fragment = new CustomerInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CustomerInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateFormatter=new SimpleDateFormat("dd-MMMM-yyyy", Locale.US);
        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (CustomerInfoPage) mCallbacks.onGetPage(mKey);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_profile, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        mNameView = ((TextView) rootView.findViewById(R.id.your_name));
        mNameView.setText(mPage.getData().getString(CustomerInfoPage.NAME_DATA_KEY));
        mBirthDay=((TextView) rootView.findViewById(R.id.your_birth_day));
        mBirthDay.setText(mPage.getData().getString(CustomerInfoPage.BIRTH_DAY_DATA_KEY));
        mEmailView = ((TextView) rootView.findViewById(R.id.your_email));
        mEmailView.setText(mPage.getData().getString(CustomerInfoPage.EMAIL_DATA_KEY));
        mGender = (RadioGroup) rootView.findViewById(R.id.rgGender);
        rdbMale = (RadioButton) rootView.findViewById(R.id.rdbMale);
        rdbFemale = (RadioButton) rootView.findViewById(R.id.rdbMale);
        setDateTimeField();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
        }

        mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                    int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPage.getData().putString(CustomerInfoPage.NAME_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                mPage.notifyDataChanged();
            }
        });

        mBirthDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                                          int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPage.getData().putString(CustomerInfoPage.BIRTH_DAY_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                mPage.notifyDataChanged();
            }
        });

        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                    int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPage.getData().putString(CustomerInfoPage.EMAIL_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                mPage.notifyDataChanged();
            }
        });
        mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdbMale:
                        mPage.getData().putString(CustomerInfoPage.GENDER_DATA_KEY, "Male");
                        break;
                    case R.id.rdbFemale:
                        mPage.getData().putString(CustomerInfoPage.GENDER_DATA_KEY,"Female");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (mNameView != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (!menuVisible) {
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        }
    }

    private void setDateTimeField() {
        mBirthDay.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mBirthDay.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onClick(View view) {
        if(view == mBirthDay) {
            datePickerDialog.show();
        }
    }


}
