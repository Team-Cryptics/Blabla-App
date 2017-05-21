package vidur.codeclan.projectx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class SpecInfo extends AppCompatActivity {

    // ImageView iv1;
    TextView tv1, tv2, tv3;
    String heading, subheading, subdesp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        heading = getIntent().getStringExtra("heading_id");
        subheading = getIntent().getStringExtra("subheading_id");
        subdesp = getIntent().getStringExtra("subdisp");

        setTitle(heading);
        setContentView(R.layout.activity_spec_info);

        tv1 = (TextView) findViewById(R.id.textView3);
        tv2 = (TextView) findViewById(R.id.textView4);
        tv3 = (TextView) findViewById(R.id.textView5);

        // iv1 = (ImageView)findViewById(R.id.imageView2);


      /*  String msg = getIntent().getStringExtra("img_id");
        Picasso.with(this)
                .load(msg)
                .into(iv1);*/

        tv1.setText(heading);
        tv2.setText(subheading);
        tv3.setText(subdesp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.spec_info_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {



        }

        return false;
    }


}
