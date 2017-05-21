package vidur.codeclan.projectx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategorySelectionActivity extends AppCompatActivity {

    ImageView im,tick;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);


        tick= (ImageView) findViewById(R.id.imageView);
        im = (ImageView) findViewById(R.id.temp);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(i==1) {
                im.setAlpha(100);
                tick.setVisibility(View.VISIBLE);
                i=0;
                }

                else if(i==0){
                    im.setAlpha(255);
                    tick.setVisibility(View.GONE);
                    i=1;
                }

            }
        });

    }
}
