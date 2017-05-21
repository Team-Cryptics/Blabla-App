package vidur.codeclan.projectx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategorySelectionActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView iv_op1, iv_click1,iv_op2,iv_click2,iv_click3,iv_op3;
    int optionArray[];


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);


        iv_click1 = (ImageView) findViewById(R.id.click1);
        iv_op1 = (ImageView) findViewById(R.id.option1);
        iv_click2 = (ImageView) findViewById(R.id.click2);
        iv_op2 = (ImageView) findViewById(R.id.option2);
        iv_click3 = (ImageView) findViewById(R.id.click3);
        iv_op3 = (ImageView) findViewById(R.id.option3);

        optionArray = new int[10];
        optionArray[3]=1; optionArray[1]=1; optionArray[2]=1;

        iv_op1.setOnClickListener(this);
        iv_op2.setOnClickListener(this);
        iv_op3.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.option1 :
                select_category(iv_op1,iv_click1,optionArray,1);
                break;

            case R.id.option2 :
                select_category(iv_op2,iv_click2,optionArray,2);
                break;

            case R.id.option3 :
                select_category(iv_op3,iv_click3,optionArray,3);
                break;


        }
    }

    void select_category(ImageView image, ImageView click,int optionArray[],int index){

        if(optionArray[index]==1) {
            image.setAlpha(100);
            click.setVisibility(View.VISIBLE);
            optionArray[index]=0;
        }

        else if(optionArray[index]==0){
            image.setAlpha(255);
            click.setVisibility(View.GONE);
            optionArray[index]=1;
        }

    }

}
