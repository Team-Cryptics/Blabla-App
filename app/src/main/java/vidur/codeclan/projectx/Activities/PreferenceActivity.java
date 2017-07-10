package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import vidur.codeclan.projectx.R;


public class PreferenceActivity extends android.preference.PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {   Preference edit_categories;
       // ListPreference lp1;
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
           // lp1 = (ListPreference)findPreference("downloadType");
            edit_categories = (Preference) findPreference("applicationUpdates");
            edit_categories.setDefaultValue("false");
            edit_categories.equals(false);


          edit_categories.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
              @Override
              public boolean onPreferenceClick(Preference preference) {
                  edit_categories.setDefaultValue("false");
                  getActivity().getSharedPreferences("User", MODE_PRIVATE).edit().putString("category", null).apply();
                  Intent intent = new Intent(getActivity(),CategorySelectionActivity.class);
                  getActivity().startActivity(intent);
                  getActivity().finish();
                return false;
              }
          });
        }
    }

}

