package b3.mobile.nicolaschen.notetracker.controllers.AddOrEditActivity;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.Student;
import b3.mobile.nicolaschen.notetracker.models.StudentLab;

public class AddStudentFragment extends Fragment {
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
        View v = inflater.inflate(R.layout.list_item_textfield, container, false);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        mMatriculeField = v.findViewById(R.id.matricule_textfield);
        mNameField = v.findViewById(R.id.name_textfield);
        mFirstNameField = v.findViewById(R.id.firstname_textfield);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        return v;
    }

    public void addElement() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View newView = inflater.inflate(R.layout.list_item_textfield, mContainer, false);
        mMatriculeField = newView.findViewById(R.id.matricule_textfield);
        mNameField = newView.findViewById(R.id.name_textfield);
        mFirstNameField = newView.findViewById(R.id.firstname_textfield);
        mContainer.addView(newView);
    }

    public void confirm(String bacYearId) {
        int childCount = mContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = mContainer.getChildAt(i);
            mNameField = childView.findViewById(R.id.name_textfield);
            mFirstNameField = childView.findViewById(R.id.firstname_textfield);
            mMatriculeField = childView.findViewById(R.id.matricule_textfield);
            String name = mNameField.getText().toString();
            String firstname = mFirstNameField.getText().toString();
            String matricule = mMatriculeField.getText().toString();
            if (name.isEmpty() || firstname.isEmpty() || matricule.isEmpty()) {
                Toast.makeText(getContext(), "Tous les champs doivent être remplis.", Toast.LENGTH_SHORT).show();
                return;
            }
            Student student = new Student(matricule,firstname,name,bacYearId);
            StudentLab.get(getContext()).addStudent(student);

        }
        getActivity().finish();
    }
}
