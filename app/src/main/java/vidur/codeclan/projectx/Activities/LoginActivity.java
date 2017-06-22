package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import vidur.codeclan.projectx.POJO.User;
import vidur.codeclan.projectx.R;

import static vidur.codeclan.projectx.POJO.GlobalAccess.URL_LOGIN;
import static vidur.codeclan.projectx.POJO.GlobalAccess.currUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText etEmail, etPassword;
    ImageButton btnLogin;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (ImageButton) findViewById(R.id.btnLogin);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etEmail.getText().toString().isEmpty() && ! etPassword.getText().toString().isEmpty()) {
                    login();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login() {
        btnLogin.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        etEmail.setEnabled(false);
        etPassword.setEnabled(false);

        Volley.newRequestQueue(this).add(new StringRequest(URL_LOGIN + "?q={%22filters%22:[{%22name%22:%22email%22,%22op%22:%22eq%22,%22val%22:%22" + etEmail.getText().toString() + "%22}]}",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        User localUser = new GsonBuilder().create().fromJson(response, User.class);
                        if (localUser.getNumResults() == 0) {
                            Toast.makeText(LoginActivity.this, "Email ID doesn't exist", Toast.LENGTH_SHORT).show();

                            btnLogin.setVisibility(View.VISIBLE);
                            pbLoading.setVisibility(View.GONE);
                            etEmail.setEnabled(true);
                            etPassword.setEnabled(true);
                        } else if (localUser.getObjects().get(0).getFollowers().get(0).getPwdhash().equals("true")) {
                            Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
///////////////
                            btnLogin.setVisibility(View.VISIBLE);
                            pbLoading.setVisibility(View.GONE);
                            etEmail.setEnabled(true);
                            etPassword.setEnabled(true);
                        } else {
                            currUser = localUser;
                            startActivity(new Intent(LoginActivity.this, CategorySelectionActivity.class));
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

                btnLogin.setVisibility(View.VISIBLE);
                pbLoading.setVisibility(View.GONE);
                etEmail.setEnabled(true);
                etPassword.setEnabled(true);
            }
        }));
    }
}
