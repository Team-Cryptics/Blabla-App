package vidur.codeclan.projectx.POJO;

/**
 * Created by samarthgupta on 21/06/17.
 */

public class CategoriesClass {

    String categoryName;
    String categoryImageURL;
    Boolean isClicked;

    public CategoriesClass() {
        this.isClicked = false;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageURL() {
        return categoryImageURL;
    }

    public void setCategoryImageURL(String categoryImageURL) {
        this.categoryImageURL = categoryImageURL;
    }

    public Boolean getClicked() {
        return isClicked;
    }

    public void setClicked(Boolean clicked) {
        isClicked = clicked;
    }
}
