package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import vidur.codeclan.projectx.R;

import static vidur.codeclan.projectx.POJO.GlobalAccess.URL_LOGIN;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLogin,tvRegister;
    View vvLogin,vvRegister;
    LinearLayout llLogin, llRegister;
    Button btLogin, btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        vvLogin =  findViewById(R.id.vv_login);
        vvRegister = findViewById(R.id.vv_register);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        llRegister = (LinearLayout) findViewById(R.id.ll_register);
        btLogin = (Button) findViewById(R.id.bt_login);
        btRegister = (Button) findViewById(R.id.bt_register);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);

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
            case R.id.bt_login :

                break;

            case R.id.bt_register :

                break;

        }

    }
}
