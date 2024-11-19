package b3.mobile.nicolaschen.notetracker.utils;

import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DisplayUtils {
    public static void setLeftMargin(LinearLayout layout, int level) {
        int leftMargin = level * 64;
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
        params.setMargins(leftMargin, params.topMargin, params.rightMargin, params.bottomMargin);
        layout.setLayoutParams(params);
    }
}
