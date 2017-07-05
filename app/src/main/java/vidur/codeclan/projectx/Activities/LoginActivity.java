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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.widget.LoginButton;
import com.google.gson.GsonBuilder;

import vidur.codeclan.projectx.POJO.User;
import vidur.codeclan.projectx.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "lol";
    TextView tvLogin, tvRegister, tvForgetPassword;
    View vvLogin, vvRegister;
    LinearLayout llRegister,llLogin;
    Button btLogin, btRegister;
    EditText etNickLogin, etEmailReg, etPassLogin, etPassReg, etNick, etPassConfirm;
    String nickLogin, emailReg, passLogin, passReg, passRegConfirm, nickReg;
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
//        //lg = (LoginButton)findViewById(R.id.login_button);
//        FacebookSdk.sdkInitialize(this.getApplicationContext());
//        //lg.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
//        callbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//                        AccessToken accessToken = loginResult.getAccessToken();
//                        GraphRequest request = GraphRequest.newMeRequest(
//                                accessToken,
//                                new GraphRequest.GraphJSONObjectCallback() {
//                                    @Override
//                                    public void onCompleted(
//                                            JSONObject object,
//                                            GraphResponse response) {
//                                        try {
//
//                                            String userID = (String) object.get("id");
//                                            String userName = (String) object.get("name");
//                                         //String email = object.getString("email");
//                                            //String birthday = object.getString("birthday");
//                                            // For Profile Picture
//                                           //Below
//                                           //  Picasso.with(LoginActivity.this).load("https://graph.facebook.com/" + userID + "/picture?type=large").into();
//                                            //Bitmap b = (Bitmap) object.get("picture");
//
//                                            Log.i("userid",userID);
//                                            Log.i("username", userName);
//                                          //  Log.i("userEmail",email);
//                                           // Log.i("userbirthday", birthday);
//                                            Log.i("o/p", "name");
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//
//                                    }
//                                });
//                        Bundle parameters = new Bundle();
//                        parameters.putString("fields", "id,name,link,birthday,picture");
//                        request.setParameters(parameters);
//                        request.executeAsync();
//                    }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
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

        if (passwd != null && nick != null) {
            progressDialog.setMessage("Please wait");
            progressDialog.show();

            String check = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/auth/" + nick + "/" + passwd;
            Log.i("APP OPEN LOGIN TAG", check);
            Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/auth/" + nick + "/" + passwd,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("TAG", response);
                            if (response.equals("True")||response.equals("true")) {
                                progressDialog.dismiss();
                                getUserData(nick);
                                startActivity(new Intent(LoginActivity.this, CategorySelectionActivity.class));
                                finish();
                            } else if (response.equals("False")||response.equals("false")) {
                                progressDialog.dismiss();

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
        etNickLogin = (EditText) findViewById(R.id.et_nickname_login);
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

    private void getUserData(String nickname) {

        String url = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/user?q={%22filters%22:[{%22name%22:%22nickname%22,%22op%22:%22eq%22,%22val%22:%22" + nickname + "%22}]}";
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                User user = new GsonBuilder().create().fromJson(response, User.class);
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
                nickLogin = etNickLogin.getText().toString().trim();
                passLogin = etPassLogin.getText().toString().trim();

                if (TextUtils.isEmpty(nickLogin) || TextUtils.isEmpty(passLogin)) {
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
                nickReg = etNick.getText().toString().trim();

                if (TextUtils.isEmpty(emailReg)) {
                    Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nickReg)) {
                    Toast.makeText(this, "Please enter a valid nickReg", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!passReg.equals(passRegConfirm)) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                pbRegister.setVisibility(View.VISIBLE);
                btRegister.setVisibility(View.INVISIBLE);

                //PS - nickLogin is actually nickReg - since the backend guy said nickReg to be used for login
                Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/register/" + nickReg + "/" + emailReg + "/" + passReg,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("TAG", response);

                                if (response.equals("true")||response.equals("True")) {

                                    llRegister.setVisibility(View.GONE);
                                    llLogin.setVisibility(View.VISIBLE);
                                    vvLogin.setVisibility(View.VISIBLE);
                                    vvRegister.setVisibility(View.INVISIBLE);
                                    getUserData(nickLogin);
                                    Toast.makeText(LoginActivity.this, "Registration success. Please Login", Toast.LENGTH_SHORT).show();
                                    pbRegister.setVisibility(View.INVISIBLE);
                                    btRegister.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    pbRegister.setVisibility(View.INVISIBLE);
                                    btRegister.setVisibility(View.VISIBLE);
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
        Log.d("LOGIN TAG", "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/auth/" + nickLogin + "/" + passLogin );
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/auth/" + nickLogin + "/" + passLogin,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "onResponse: " + response);
                        if (response.equals("True")||response.equals("true")) {

                            sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString("nickname", nickLogin);
                            editor.putString("password", passLogin);
                            editor.apply();
                            getUserData(nickLogin);
                            startActivity(new Intent(LoginActivity.this, CategorySelectionActivity.class));
                            finish();
                        } else if (response.equals("authi")) {

                            pbLogin.setVisibility(View.INVISIBLE);
                            btLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Please verify email address", Toast.LENGTH_LONG).show();
                            return;
                        } else {
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
////
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//       // callbackManager.onActivityResult(requestCode, resultCode, data);
//    }
//}
