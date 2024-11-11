package b3.mobile.nicolaschen.notetracker.controllers.ListActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

public class DetailedStudentNoteActivity extends ListActivity{
    @Override
    protected void onActionButtonClick(UUID bacYearId) {

    }

    @Override
    protected String getTitleText(String Name) {
        return "";
    }

    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return DetailedStudentNoteFragment();
    }
}
