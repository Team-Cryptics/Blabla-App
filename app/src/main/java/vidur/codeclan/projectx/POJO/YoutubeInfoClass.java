package vidur.codeclan.projectx.POJO;

import android.content.Context;

/**
 * Created by SUPERUSER on 23-06-2017.
 */

public class YoutubeInfoClass {
    private String image_id, heading;




    public YoutubeInfoClass(String image_id, String heading) {
        this.image_id = image_id;
        this.heading = heading;
    }

    public String getImage_id() {
        return image_id;
    }

    public String getHeading() {
        return heading;
    }

    public void setImage_id (String image_id) {
        this.image_id = image_id;
    }

    public void setHeading (String heading) {
        this.heading = heading;
    }

}