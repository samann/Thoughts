package droidowl.thoughts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment
public class ThoughtActivityFragment extends Fragment {





    public ThoughtActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thought, container, false);
    }

}
