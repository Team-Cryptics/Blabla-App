package vidur.codeclan.projectx.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.GsonBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import vidur.codeclan.projectx.POJO.CategoriesClass;
import vidur.codeclan.projectx.POJO.User;
import vidur.codeclan.projectx.R;

import com.facebook.appevents.AppEventsLogger;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static vidur.codeclan.projectx.R.id.imageView;


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
    public static User globalUser;

    LoginButton lg;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lg = (LoginButton)findViewById(R.id.login_button);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        //lg.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        AccessToken accessToken = loginResult.getAccessToken();
                        GraphRequest request = GraphRequest.newMeRequest(
                                accessToken,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        try {

                                            String userID = (String) object.get("id");
                                            String userName = (String) object.get("name");
                                         //String email = object.getString("email");
                                            //String birthday = object.getString("birthday");
                                            // For Profile Picture
                                           //Below
                                           //  Picasso.with(LoginActivity.this).load("https://graph.facebook.com/" + userID + "/picture?type=large").into();
                                            //Bitmap b = (Bitmap) object.get("picture");

                                            Log.i("userid",userID);
                                            Log.i("username", userName);
                                          //  Log.i("userEmail",email);
                                           // Log.i("userbirthday", birthday);
                                            Log.i("o/p", "name");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,link,birthday,picture");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
//
//                <com.facebook.login.widget.LoginButton
//        android:id="@+id/login_button"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_gravity="center_horizontal"
//        android:layout_marginTop="100dp"
//        android:layout_marginBottom="30dp"
//        android:layout_marginLeft="100dp"
//        android:layout_alignParentBottom="true"/>
//        callbackManager = CallbackManager.Factory.create();
//
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        startActivity(new Intent(LoginActivity.this, CategorySelectionActivity.class));
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });

        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        final String nick = sharedPreferences.getString("nickname", null);
        final String passwd = sharedPreferences.getString("password", null);

        progressDialog = new ProgressDialog(this);

        if (passwd != null && nick != null ) {
            progressDialog.setMessage("Please wait");
            progressDialog.show();


            Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/auth/" + nick + "/" + passLogin,
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
            }));


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
                globalUser = user;

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
                            editor.putString("nickname", emailLogin);
                            editor.putString("password", passLogin);
                            editor.apply();
                            getUserData(emailLogin);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
