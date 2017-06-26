package vidur.codeclan.projectx.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import vidur.codeclan.projectx.POJO.CategoriesClass;
import vidur.codeclan.projectx.POJO.User;
import vidur.codeclan.projectx.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "lol";
    TextView tvLogin, tvRegister, tvForgetPassword;
    View vvLogin, vvRegister;
    LinearLayout llLogin, llRegister;
    Button btLogin, btRegister;
    EditText etEmailLogin, etEmailReg, etPassLogin, etPassReg, etNick, etPassConfirm;
    String emailLogin, emailReg, passLogin, passReg, passRegConfirm, nickname;
    ProgressBar pbLogin, pbRegister;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        final String email = sharedPreferences.getString("email", null);
        final String passwd = sharedPreferences.getString("password", null);

        progressDialog = new ProgressDialog(this);

        if (passwd != null && email != null ) {
            progressDialog.setMessage("Please wait");
            progressDialog.show();


            Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://192.168.0.15/blabla/login.php",
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("TAG",response);
                            if (response.equals("true")) {

                                progressDialog.dismiss();
                                startActivity(new Intent(LoginActivity.this, CategorySelectionActivity.class));
                                finish();
                            }

                        }
                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", passwd);
                    return params;
                }
            });


        }

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
        pbLogin = (ProgressBar) findViewById(R.id.progress_login);
        pbRegister = (ProgressBar) findViewById(R.id.progress_register);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);



    }

    private void getUserData(String emailLogin) {

        String url = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/user?q={%22filters%22:[{%22name%22:%22nickname%22,%22op%22:%22eq%22,%22val%22:%22"+emailLogin+"%22}]}";
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                User user = new GsonBuilder().create().fromJson(response,User.class);
                //Use user further.

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
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
                //User login
                emailLogin = etEmailLogin.getText().toString().trim();
                passLogin = etPassLogin.getText().toString().trim();

                if (TextUtils.isEmpty(emailLogin) || TextUtils.isEmpty(passLogin)) {
                    Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                pbLogin.setVisibility(View.VISIBLE);
                btLogin.setVisibility(View.INVISIBLE);

                login();


                break;

            case R.id.bt_register:
                emailReg = etEmailReg.getText().toString().trim();
                passReg = etPassReg.getText().toString().trim();
                passRegConfirm = etPassConfirm.getText().toString().trim();
                nickname = etNick.getText().toString().trim();

                if (TextUtils.isEmpty(emailReg)) {
                    Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nickname)) {
                    Toast.makeText(this, "Please enter a valid nickname", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!passReg.equals(passRegConfirm)) {
                    Toast.makeText(this, "Passwords donot match", Toast.LENGTH_SHORT).show();
                    return;
                }

                pbRegister.setVisibility(View.VISIBLE);
                btRegister.setVisibility(View.INVISIBLE);

                //PS - emailLogin is actually nickname - since the backend guy said nickname to be used for login
                Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/register/" + emailLogin + "/" + emailReg + "/" + passReg,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("TAG",response);

                                if (response.equals("true")) {

                                    llRegister.setVisibility(View.INVISIBLE);
                                    llLogin.setVisibility(View.VISIBLE);
                                    vvLogin.setVisibility(View.VISIBLE);
                                    vvRegister.setVisibility(View.INVISIBLE);
                                    getUserData(emailLogin);
                                    Toast.makeText(LoginActivity.this, "Registration success. Please Login", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pbRegister.setVisibility(View.INVISIBLE);
                        btRegister.setVisibility(View.VISIBLE);
                    }
                }));

                break;

            case R.id.tv_forgetPassword:
                startActivity(new Intent(LoginActivity.this, ForgetPassActivity.class));
                break;

        }

    }

    void login() {
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/auth/" + nickname + "/" + passLogin,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "onResponse: " + response);
                        if (response.equals("True")) {

                            sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString("email", emailLogin);
                            editor.putString("password", passLogin);
                            editor.apply();

                            startActivity(new Intent(LoginActivity.this, CategorySelectionActivity.class));
                            finish();
                        }

                        else if(response.equals("authi")) {

                            pbLogin.setVisibility(View.INVISIBLE);
                            btLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Please verify email address", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else {
                            pbLogin.setVisibility(View.INVISIBLE);
                            btLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Invalid email address or password", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pbLogin.setVisibility(View.INVISIBLE);
                btLogin.setVisibility(View.VISIBLE);

            }
        }));
    }

}
