package vidur.codeclan.projectx.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import vidur.codeclan.projectx.R;

public class SettingsActivity extends AppCompatActivity {
    boolean checked;
    Switch swCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        swCategory = (Switch) findViewById(R.id.swCategories);
        swCategory.setChecked(getSharedPreferences("User", MODE_PRIVATE).getString("category", null) == null);

        swCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checked = b;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (checked) {
            getSharedPreferences("User", MODE_PRIVATE).edit().remove("category");
        }
        super.onDestroy();
    }
}
