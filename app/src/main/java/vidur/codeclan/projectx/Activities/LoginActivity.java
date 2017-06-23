package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;
import vidur.codeclan.projectx.POJO.LoginInfo;
import vidur.codeclan.projectx.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLogin, tvRegister,tvForgetPassword;
    View vvLogin, vvRegister;
    LinearLayout llLogin, llRegister;
    Button btLogin, btRegister;
    EditText etEmailLogin, etEmailReg, etPassLogin, etPassReg, etNick, etPassConfirm;
    String emailLogin, emailReg, passLogin, passReg, passRegConfirm, nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        vvLogin = findViewById(R.id.vv_login);
        vvRegister = findViewById(R.id.vv_register);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        llRegister = (LinearLayout) findViewById(R.id.ll_register);
        btLogin = (Button) findViewById(R.id.bt_login);
        btRegister = (Button) findViewById(R.id.bt_register);
        etEmailLogin = (EditText) findViewById(R.id.et_email_login);
        etEmailReg = (EditText) findViewById(R.id.et_email_reg);
        etPassLogin = (EditText) findViewById(R.id.et_password_login);
        etPassReg = (EditText) findViewById(R.id.et_password_reg);
        etNick = (EditText) findViewById(R.id.et_nickname_reg);
        etPassConfirm = (EditText) findViewById(R.id.et_confirmPassword_reg);
        tvForgetPassword = (TextView) findViewById(R.id.tv_forgetPassword);


        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);


    }




    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_login:
                llRegister.setVisibility(View.INVISIBLE);
                llLogin.setVisibility(View.VISIBLE);
                vvLogin.setVisibility(View.VISIBLE);
                vvRegister.setVisibility(View.INVISIBLE);
                break;

            case R.id.tv_register:
                llLogin.setVisibility(View.INVISIBLE);
                llRegister.setVisibility(View.VISIBLE);
                vvLogin.setVisibility(View.INVISIBLE);
                vvRegister.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_login:
                emailLogin = etEmailLogin.getText().toString().trim();
                passLogin = etPassLogin.getText().toString().trim();



                        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://192.168.0.15/blabla/login.php",
                                new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("TAG", "onResponse: " + response);
                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> params = new HashMap<>();
                                params.put("email", emailLogin);
                                params.put("password", passLogin);
                                return params;
                            }
                        });

                break;

            case R.id.bt_register:
                emailReg = etEmailReg.getText().toString().trim();
                passReg = etPassReg.getText().toString().trim();
                passRegConfirm = etPassConfirm.getText().toString().trim();
                nickname = etNick.getText().toString().trim();

                if (!passReg.equals(passRegConfirm)) {
                    Toast.makeText(this, "Passwords donot match", Toast.LENGTH_SHORT).show();
                    return;
                }

                Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://192.168.0.15/blabla/register.php",
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("TAG", "onResponse: " + response);
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("email", emailReg);
                        params.put("password", passReg);
                        params.put("username", nickname);
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

                break;

            case R.id.tv_forgetPassword:
                startActivity(new Intent(LoginActivity.this, ForgetPassActivity.class));
                break;

        }

    }


}
