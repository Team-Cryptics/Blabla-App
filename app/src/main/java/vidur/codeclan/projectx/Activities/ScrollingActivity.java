package vidur.codeclan.projectx.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import vidur.codeclan.projectx.R;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/user?q={%22filters%22:[{%22name%22:%22email%22,%22op%22:%22eq%22,%22val%22:%22"+getSharedPreferences("User", MODE_PRIVATE).getString("email", null)+"%22}]}",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "onResponse: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }
}
