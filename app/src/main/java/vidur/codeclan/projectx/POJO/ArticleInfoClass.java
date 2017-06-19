package vidur.codeclan.projectx.POJO;

/**
 * Created by Sarthak on 21-05-2017.
 */

public class ArticleInfoClass {

    private String image_id, heading, subheading, url;

    public ArticleInfoClass(String image_id, String heading, String subheading, String url)
    {
        this.image_id=image_id;
        this.heading=heading;
        this.subheading=subheading;
        this.url = url;
    }



    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
