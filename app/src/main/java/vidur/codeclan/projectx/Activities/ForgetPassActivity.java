package vidur.codeclan.projectx.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import vidur.codeclan.projectx.R;

public class ForgetPassActivity extends AppCompatActivity {

    EditText etEmailForget;
    TextView tvCancel;
    Button btSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        etEmailForget = (EditText) findViewById(R.id.et_forgetPassword);
        tvCancel = (TextView) findViewById(R.id.tv_forgetPass_cancel);
        btSubmit = (Button) findViewById(R.id.bt_forgetPassword);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etEmailForget.getText().toString().isEmpty()) {
                    Volley.newRequestQueue(ForgetPassActivity.this).add(new StringRequest(Request.Method.POST, "http://192.168.0.15/blabla/smtpforgot.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("TAG", "onResponse: " + response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<>();
                            params.put("email", etEmailForget.getText().toString());
                            return params;
                        }
                    }).setRetryPolicy(new RetryPolicy() {
                        @Override
                        public int getCurrentTimeout() {
                            return 0;
                        }

                        @Override
                        public int getCurrentRetryCount() {
                            return 0;
                        }

                        @Override
                        public void retry(VolleyError error) throws VolleyError {

                        }
                    });
                } else {
                    Toast.makeText(ForgetPassActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
