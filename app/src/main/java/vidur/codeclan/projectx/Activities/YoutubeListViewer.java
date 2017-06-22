package vidur.codeclan.projectx.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import vidur.codeclan.projectx.Adapters.ArticleInfoAdapter;
import vidur.codeclan.projectx.Adapters.YoutubeInfoAdapter;
import vidur.codeclan.projectx.POJO.ArticleInfoClass;
import vidur.codeclan.projectx.POJO.YoutubeInfoClass;
import vidur.codeclan.projectx.R;

public class YoutubeListViewer extends AppCompatActivity {


    RecyclerView recyclerView;
   YoutubeInfoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<YoutubeInfoClass> list = new ArrayList<YoutubeInfoClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list_viewer);

        YoutubeInfoClass info = new YoutubeInfoClass("https://image.freepik.com/free-vector/white-squares-on-colorful-squares-background_23-2147500535.jpg","Abc cde");

        for(int i =0;i<10;i++){
            list.add(i,info);
        }

        recyclerView = (RecyclerView)findViewById(R.id.recycler_youtube);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new YoutubeInfoAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);





    }
}
