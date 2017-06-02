package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vidur.codeclan.projectx.FlowTextView;
import vidur.codeclan.projectx.R;
import vidur.codeclan.projectx.listeners.OnLinkClickListener;


public class ViewArticleActivity extends ActionBarActivity implements View.OnClickListener {

    private static final float defaultFontSize = 20.0f;

    private FlowTextView flowTextView;
    TextView tvHeading;
    String heading, subheading, subdesp;
    Button sharebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        flowTextView = (FlowTextView) findViewById(R.id. ftv);
        tvHeading = (TextView) findViewById(R.id.textView_article_heading);

        sharebutton = (Button)findViewById(R.id.action_share) ;
        heading = getIntent().getStringExtra("heading_id");
        subheading = getIntent().getStringExtra("subheading_id");
        subdesp = getIntent().getStringExtra("subdisp");

        tvHeading.setText(heading);
        String content = getString(R.string.lorem);
        Spanned html = Html.fromHtml(content);
        flowTextView.setText(html);

        // handle link behaviour
        flowTextView.setOnLinkClickListener(new OnLinkClickListener() {
            @Override
            public void onLinkClick(String url) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

//        Button btnIncreasefontSize = (Button) findViewById(R.id.btn_increase_font_size);
//        btnIncreasefontSize.setOnClickListener(this);
//        Button btnDecreasefontSize = (Button) findViewById(R.id.btn_decrease_font_size);
//        btnDecreasefontSize.setOnClickListener(this);
//        Button btnReset = (Button) findViewById(R.id.btn_reset);
//        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
//            case R.id.btn_increase_font_size:
//                increaseFontSize();
//                break;
//            case R.id.btn_decrease_font_size:
//                decreaseFontSize();
//                break;
//            case R.id.btn_reset:
//                reset();
//                break;
//            default:
//                break;
        }
    }

    private void increaseFontSize(){
        float currentFontSize = flowTextView.getTextsize();
        flowTextView.setTextSize(currentFontSize+1);
    }

    private void decreaseFontSize(){
        float currentFontSize = flowTextView.getTextsize();
        flowTextView.setTextSize(currentFontSize-1);
    }

    private void reset(){
        flowTextView.setTextSize(defaultFontSize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.article_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_increase_textsize :
                increaseFontSize();
                break;
            case R.id.action_decrease_textsize :
                decreaseFontSize();
                break;
            



        }

        return false;
    }


}

