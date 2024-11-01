package b3.mobile.nicolaschen.notetracker.controllers;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.BacYearLab;

public class BacYearEditFragment extends Fragment {
    public static final String BAC_YEAR_ID = "BACYEAR_ID";
    protected BacYear mBacYear;
    private EditText mTitleField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bacYear_id est l’identifiant de l’objet BacYear à afficher, passé en extra de l’intent
        UUID bacYear_id = (UUID) getActivity().getIntent().getSerializableExtra(BAC_YEAR_ID);
        //récupération de l’objet BacYear correspondant à l’identifiant
        mBacYear = BacYearLab.get(getContext()).getBacYear(bacYear_id);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the fragment_bac_year view
        View v = inflater.inflate(R.layout.fragment_edit_bac_year, container, false);
        // configure the view
        mTitleField = (EditText) v.findViewById(R.id.bac_year_editText);
        mTitleField.setText(mBacYear.getName());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBacYear.setName(s.toString());
                BacYearLab.get(getContext()).updateBacYear(mBacYear);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        return v;
    }
}
