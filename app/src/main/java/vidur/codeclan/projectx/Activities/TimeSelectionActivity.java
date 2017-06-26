package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import vidur.codeclan.projectx.R;

public class TimeSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn5, btn15, btn30;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selection);

        category = getSharedPreferences("User", MODE_PRIVATE).getString("category", null);
      //  Log.e("TAG",category);

       // category+="{%22name%22:%22category%22,%22op%22:%22eq%22,%22val%22:%2215%20Minutes%22}";

        btn5 = (Button) findViewById(R.id.bt_five);
        btn15 = (Button) findViewById(R.id.bt_fifteen);
        btn30 = (Button) findViewById(R.id.bt_thirty);

        btn5.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn30.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_five:
                category+="{%22name%22:%22category%22,%22op%22:%22eq%22,%22val%22:%225%20Minutes%22}";
                break;
            case R.id.bt_fifteen:
                category+="{%22name%22:%22category%22,%22op%22:%22eq%22,%22val%22:%2215%20Minutes%22}";
                break;
            case R.id.bt_thirty:
                category+="{%22name%22:%22category%22,%22op%22:%22eq%22,%22val%22:%2230%20Minutes%22}";
                break;
        }

        category+="]}";
        String base_url = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/post";
        Intent intent = new Intent(TimeSelectionActivity.this,TabbedActivity.class);
        intent.putExtra("URL",base_url+category);

        startActivity(intent);
        finish();
    }
}
