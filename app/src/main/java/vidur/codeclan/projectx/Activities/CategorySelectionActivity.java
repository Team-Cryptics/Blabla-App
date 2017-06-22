package vidur.codeclan.projectx.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import vidur.codeclan.projectx.POJO.CategoriesClass;
import vidur.codeclan.projectx.R;

public class CategorySelectionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    FloatingActionButton fab_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_category);
        fab_proceed = (FloatingActionButton) findViewById(R.id.fab_category_proceed);
        ArrayList<CategoriesClass> list = new ArrayList<>();

        for(int i =0 ; i<20;i++){
            CategoriesClass obj = new CategoriesClass();
            obj.setCategoryName("Music");
            obj.setCategoryImageURL("https://yt3.ggpht.com/0v8T0CTAv8VPxA5lJtz-tqJe-tR-3VQc0ONhD6Az2RWjNRnwh5QQzPYz5I7wbYljU_tQjZ2ok2W59_v_=s900-nd-c-c0xffffffff-rj-k-no");
            list.add(obj);
        }

        adapter = new CategoryAdapter(list,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fab_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategorySelectionActivity.this,TabbedActivity.class));
                finish();
            }
        });

    }


    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

        List<CategoriesClass> list;
        Context c;

        public CategoryAdapter(ArrayList<CategoriesClass> categoryList, Context context) {
            list = categoryList;
            c= context;
        }

        @Override
        public CategoryAdapter.CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_recycler, parent, false);
            return new CategoryHolder(view,list);
        }

        @Override
        public void onBindViewHolder(CategoryAdapter.CategoryHolder holder, int position) {
            Picasso.with(c).load(list.get(position).getCategoryImageURL()).into(holder.iv_category);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


            ImageView iv_category, iv_click;
            List<CategoriesClass> list;

            public CategoryHolder(View itemView, List<CategoriesClass> listForHolder) {
                super(itemView);
                iv_category = (ImageView) itemView.findViewById(R.id.imageView_options);
                iv_click = (ImageView) itemView.findViewById(R.id.imageView_click_options);
                list = listForHolder;
                iv_category.setOnClickListener(this);
                iv_click.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();

                if(!list.get(position).getClicked()) { //If is clicked is false
                    list.get(position).setClicked(true);
                    iv_category.setImageAlpha(100);
                    iv_click.setVisibility(View.VISIBLE);
                }

                else {
                    list.get(position).setClicked(false);
                    iv_category.setAlpha(255);
                    iv_click.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

}
