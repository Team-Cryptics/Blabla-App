package vidur.codeclan.projectx.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import vidur.codeclan.projectx.POJO.Post;
import vidur.codeclan.projectx.R;

public class TimeSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn5, btn15, btn30;
    String category;
    public static Post posts;
    ProgressDialog progressDialog;

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

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("lol");
        progressDialog.setMessage("double lol");
        progressDialog.setCancelable(false);
        progressDialog.show();

        category+="]}";
        String base_url = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/post";
        final Intent intent = new Intent(TimeSelectionActivity.this,TabbedActivity.class);

        String url = base_url+category;
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("TAG",response);
                posts = new GsonBuilder().create().fromJson(response, Post.class);
                progressDialog.cancel();
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));

    }
}
