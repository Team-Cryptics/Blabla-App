package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vidur.codeclan.projectx.R;

public class TimeSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn5, btn15, btn30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selection);

        String category = getSharedPreferences("User", MODE_PRIVATE).getString("category", null);

        btn5 = (Button) findViewById(R.id.bt_five);
        btn15 = (Button) findViewById(R.id.bt_fifteen);
        btn30 = (Button) findViewById(R.id.bt_thirty);

        btn5.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn30.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(TimeSelectionActivity.this,TabbedActivity.class));
        finish();
        switch (view.getId()){



        }
    }
}
