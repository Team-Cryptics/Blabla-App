package vidur.codeclan.projectx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SpecInfo extends AppCompatActivity {

 // ImageView iv1;
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_info);

       // iv1 = (ImageView)findViewById(R.id.imageView2);
        tv1 = (TextView)findViewById(R.id.textView3);
        tv2 = (TextView)findViewById(R.id.textView4);
        tv3 = (TextView)findViewById(R.id.textView5);

      /*  String msg = getIntent().getStringExtra("img_id");
        Picasso.with(this)
                .load(msg)
                .into(iv1);*/


        tv1.setText(getIntent().getStringExtra("heading_id"));
        tv2.setText(getIntent().getStringExtra("subheading_id"));
        tv3.setText(getIntent().getStringExtra("subdisp"));


    }
}
