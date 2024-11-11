package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.BacYearLab;

public class AddBackYearFragment extends Fragment {
    private LinearLayout mContainer;
    private EditText mMatriculeField;
    private EditText mNameField;
    private EditText mFirstNameField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_item_student_textfield, container, false);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        mMatriculeField = v.findViewById(R.id.matricule_textfield);
        mNameField = v.findViewById(R.id.assessmentName_textfield);
        mFirstNameField = v.findViewById(R.id.maxNote_textfield);
        mMatriculeField.setVisibility(View.GONE);
        mFirstNameField.setVisibility(View.GONE);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        return v;
    }

    public void addElement() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View newView = inflater.inflate(R.layout.list_item_student_textfield, mContainer, false);
        mMatriculeField = newView.findViewById(R.id.matricule_textfield);
        mNameField = newView.findViewById(R.id.assessmentName_textfield);
        mFirstNameField = newView.findViewById(R.id.maxNote_textfield);
        mMatriculeField.setVisibility(View.GONE);
        mFirstNameField.setVisibility(View.GONE);
        mContainer.addView(newView);
    }

    public void confirm() {
        int childCount = mContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = mContainer.getChildAt(i);
            mNameField = childView.findViewById(R.id.assessmentName_textfield);
            String name = mNameField.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(getContext(), "Tous les champs doivent être remplis.", Toast.LENGTH_SHORT).show();
                return;
            }
            BacYear bacYear = new BacYear(name);
            BacYearLab.get(getContext()).addBacYear(bacYear);
        }
        getActivity().finish();
    }
}