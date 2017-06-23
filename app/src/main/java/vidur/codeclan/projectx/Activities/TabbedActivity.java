package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import vidur.codeclan.projectx.R;
import vidur.codeclan.projectx.Adapters.TabsPagerAdapter;


public class TabbedActivity extends FragmentActivity implements android.app.ActionBar.TabListener {

    private static final String TAG = "TabbedActivity";
    ViewPager viewPager;
    TabsPagerAdapter adapter;
    android.app.ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetchPosts();

        setContentView(R.layout.activity_tabbed);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setStackedBackgroundDrawable(getResources().getDrawable(R.color.black));

        String[] tabs = { "Videos", "Articles", "Podcasts" };
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }


        viewPager.setCurrentItem(1);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tabbed_activity_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.Profile :
               // startActivity(new Intent(TabbedActivity.this,P));
                break;

            case R.id.Signout:
                getSharedPreferences("User", MODE_PRIVATE).edit().remove("email").remove("password").apply();
                startActivity(new Intent(TabbedActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.About:
                break;

            case R.id.Settings:
                break;
        }

        return true;
    }

    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
            viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

//    http://ec2-13-58-169-227.us-east-2.compute.amazonaws.com/api/user?q={%22filters%22:[{%22name%22:%22email%22,%22op%22:%22eq%22,%22val%22:%22aditya03011997@gmail.com%22}]}

//    public void fetchPosts() {
//        Volley.newRequestQueue(this).add(new StringRequest(URL_POST,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                      //  currPosts = new GsonBuilder().create().fromJson(response, Posts.class);
//                        Log.d(TAG, "onResponse: " + response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(TabbedActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }));
//    }

}
