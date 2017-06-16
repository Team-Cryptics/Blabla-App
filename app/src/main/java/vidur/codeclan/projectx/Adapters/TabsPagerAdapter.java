package vidur.codeclan.projectx.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import vidur.codeclan.projectx.Fragments.ArticlesFragment;
import vidur.codeclan.projectx.Fragments.PodcastFragment;
import vidur.codeclan.projectx.Fragments.VideosFragment;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.i("TAG",position+" ");
        switch (position){
            case 0:
                return new VideosFragment();
            case 1:
                    return new ArticlesFragment();

            case 2:
                return new PodcastFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
