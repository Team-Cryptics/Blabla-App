package vidur.codeclan.projectx.Activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import vidur.codeclan.projectx.R;

/**
 * Created by SUPERUSER on 23-06-2017.
 */

public class UserSettingActivity extends PreferenceActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

    }
}
