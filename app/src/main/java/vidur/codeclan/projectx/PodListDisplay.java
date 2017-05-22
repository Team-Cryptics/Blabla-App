package vidur.codeclan.projectx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PodListDisplay extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PodClass> list = new ArrayList<PodClass>();
    String[] image,heading,subheading,subdisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_pod);

        image = getResources().getStringArray(R.array.image);
        heading = getResources().getStringArray(R.array.heading);
        subheading = getResources().getStringArray(R.array.subheading);
        subdisp = getResources().getStringArray(R.array.subdisp);

        int count = 0;
        for(String Heading : heading)
        {
            PodClass podClass = new PodClass(image[count],heading[count],subheading[count], subdisp[count]);
            count++;
            list.add(podClass);
        }

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new PodAdapter(list, this);
        recyclerView.setAdapter(adapter);

    }
}
