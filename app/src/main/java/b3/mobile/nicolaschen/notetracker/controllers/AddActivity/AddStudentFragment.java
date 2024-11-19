package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

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
        View v = inflater.inflate(R.layout.list_item_student_textfield, container, false);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        initView(v);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        return v;
    }

    private void initView(View v) {
        mMatriculeField = v.findViewById(R.id.matricule_textfield);
        mMatriculeField.setHint("Matricule");
        mNameField = v.findViewById(R.id.assessmentName_textfield);
        mNameField.setHint("Nom");
        mFirstNameField = v.findViewById(R.id.maxNote_textfield);
        mFirstNameField.setHint("Prénom");
    }

    public void addElement() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View newView = inflater.inflate(R.layout.list_item_student_textfield, mContainer, false);
        initView(newView);
        mContainer.addView(newView);
    }

    public void confirm(String bacYearId) {
        int childCount = mContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = mContainer.getChildAt(i);
            mNameField = childView.findViewById(R.id.assessmentName_textfield);
            mFirstNameField = childView.findViewById(R.id.maxNote_textfield);
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
