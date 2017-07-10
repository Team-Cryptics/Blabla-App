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

    ViewPager viewPager;
    TabsPagerAdapter adapter;
    android.app.ActionBar actionBar;
    static String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetchPosts();

        url = getIntent().getStringExtra("URL");
        setContentView(R.layout.activity_tabbed);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setStackedBackgroundDrawable(getResources().getDrawable(R.color.black));

      //  String[] tabs = { "Videos", "Articles", "Podcasts" };
        String[] tabs = { "Videos", "Articles"};
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
                startActivity(new Intent(this, ProfileActivity.class));
                break;

            case R.id.Signout:
                getSharedPreferences("User", MODE_PRIVATE).edit().remove("email").remove("password").apply();
                startActivity(new Intent(TabbedActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.About:
                break;

            case R.id.Settings:
                startActivity(new Intent(this, PreferenceActivity.class));
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


}
