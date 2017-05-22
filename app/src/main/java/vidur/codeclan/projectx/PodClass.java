package vidur.codeclan.projectx;

/**
 * Created by Sarthak on 21-05-2017.
 */

public class PodClass {

    public PodClass(String image_id, String heading, String subheading, String subdisp)
    {
        this.image_id=image_id;
        this.heading=heading;
        this.subheading=subheading;
        this.subdisp=subdisp;
    }

    private String image_id, heading, subheading,subdisp;

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

    public String getSubdisp() {
        return subdisp;
    }

    public void setSubdisp(String subdisp) {
        this.subdisp = subdisp;
    }



}
