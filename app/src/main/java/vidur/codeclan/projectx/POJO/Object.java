package vidur.codeclan.projectx.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("sub_category")
    @Expose
    private java.lang.Object subCategory;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public java.lang.Object getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(java.lang.Object subCategory) {
        this.subCategory = subCategory;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}