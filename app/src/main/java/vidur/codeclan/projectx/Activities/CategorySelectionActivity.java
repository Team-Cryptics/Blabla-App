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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import vidur.codeclan.projectx.POJO.CategoriesClass;
import vidur.codeclan.projectx.R;

public class CategorySelectionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    FloatingActionButton fab_proceed;
    int i;
    String categoryURl = "?q={%22filters%22:[{%22or%22:[";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);


        getSharedPreferences("User", MODE_PRIVATE).edit().remove("category").apply();
        if (getSharedPreferences("User", MODE_PRIVATE).getString("category", null) != null) {

            startActivity(new Intent(CategorySelectionActivity.this,TimeSelectionActivity.class));
            finish();
        }



        recyclerView = (RecyclerView) findViewById(R.id.recycler_category);
        fab_proceed = (FloatingActionButton) findViewById(R.id.fab_category_proceed);
        final ArrayList<CategoriesClass> list = new ArrayList<>();
        fab_proceed.setVisibility(View.INVISIBLE);

        list.add(new CategoriesClass("Business & Finance","BusinessAndFinance","http://meghancurrie.ca/wp-content/uploads/2015/09/Finance1.jpg"));
        list.add(new CategoriesClass("Technology","Technology","https://www.siliconfeed.com/wp-content/uploads/2017/04/20161028-technology-top-image.jpg"));
        list.add(new CategoriesClass("Data Structures & Algorithms","DSAndAlgo","https://cdn3.iconfinder.com/data/icons/abstract-1/512/algorithm-256.png"));
        list.add(new CategoriesClass("Life Hacks","Lifehacks","https://cdn1.12stone.com/wp-content/uploads/2014/10/Life-Hacks.jpg"));
        list.add(new CategoriesClass("Machine Learning & AI","MachineLearningAndAI","https://cdn2.techworld.com/cmsdata/features/3623340/deeplearning_neuralnetworks_AI_thumb800.jpg"));
        list.add(new CategoriesClass("Soft skills","Softskills","https://mediacm.blob.core.windows.net/media/2016/08/soft-skills.jpg"));


        adapter = new CategoryAdapter(list,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fab_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for( i =0;i<list.size();i++){
                    if(list.get(i).getClicked()){

                        categoryURl += "{%22name%22:%22types%22,%22op%22:%22eq%22,%22val%22:%22"+list.get(i).getCategoryType()+"%22},";

                    }
                }

                categoryURl = categoryURl.substring(0,categoryURl.length()-1);
                categoryURl+="]},";



               // ?q={"filters":[{"or":[{"name":"types","op":"eq","val":"Lifehacks"},{"name":"types","op":"eq","val":"Technology"}]},{"name":"category","op":"eq","val":"5 20Minutes"}]}

                getSharedPreferences("User", MODE_PRIVATE).edit().putString("category", categoryURl).apply();

                startActivity(new Intent(CategorySelectionActivity.this,TimeSelectionActivity.class));
                finish();
            }
        });

    }

    //http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/post
    // ?q={%22filters%22:[{%22or%22:[{%22name%22:%22types%22,%22op%22:%22eq%22,%22val%22:%22Lifehacks%22},{%22name%22:%22types%22,%22op%22:%22eq%22,%22val%22:%22Technology%22}]},{%22name%22:%22category%22,%22op%22:%22eq%22,%22val%22:%2215%20Minutes%22}]}

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

        List<CategoriesClass> list;
        Context c;
        int numClicked=0;

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
            holder.tv_title.setText(list.get(position).getCategoryName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


            ImageView iv_category, iv_click;
            TextView tv_title;
            List<CategoriesClass> list;

            public CategoryHolder(View itemView, List<CategoriesClass> listForHolder) {
                super(itemView);
                iv_category = (ImageView) itemView.findViewById(R.id.imageView_options);
                iv_click = (ImageView) itemView.findViewById(R.id.imageView_click_options);
                tv_title = (TextView) itemView.findViewById(R.id.textView_category_title);
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
                    numClicked++;
                }

                else {
                    list.get(position).setClicked(false);
                    iv_category.setAlpha(255);
                    iv_click.setVisibility(View.INVISIBLE);
                    numClicked--;
                }

                if(numClicked>0){
                    fab_proceed.setVisibility(View.VISIBLE);
                }

                else {
                    fab_proceed.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

}

