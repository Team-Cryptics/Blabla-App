package vidur.codeclan.projectx.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object {

    @SerializedName("activation_status")
    @Expose
    private Boolean activationStatus;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("flwrs")
    @Expose
    private Integer flwrs;
    @SerializedName("followed")
    @Expose
    private List<Followed> followed = null;
    @SerializedName("followers")
    @Expose
    private List<Follower> followers = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("interests")
    @Expose
    private String interests;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("posts")
    @Expose
    private List<Post> posts = null;
    @SerializedName("tokens")
    @Expose
    private java.lang.Object tokens;

    /**
     * No args constructor for use in serialization
     *
     */
    public Object() {
    }

    /**
     *
     * @param id
     * @param followers
     * @param activationStatus
     * @param nickname
     * @param interests
     * @param email
     * @param flwrs
     * @param posts
     * @param tokens
     * @param followed
     */
    public Object(Boolean activationStatus, String email, Integer flwrs, List<Followed> followed, List<Follower> followers, Integer id, String interests, String nickname, List<Post> posts, java.lang.Object tokens) {
        super();
        this.activationStatus = activationStatus;
        this.email = email;
        this.flwrs = flwrs;
        this.followed = followed;
        this.followers = followers;
        this.id = id;
        this.interests = interests;
        this.nickname = nickname;
        this.posts = posts;
        this.tokens = tokens;
    }

    public Boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFlwrs() {
        return flwrs;
    }

    public void setFlwrs(Integer flwrs) {
        this.flwrs = flwrs;
    }

    public List<Followed> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Followed> followed) {
        this.followed = followed;
    }

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public java.lang.Object getTokens() {
        return tokens;
    }

    public void setTokens(java.lang.Object tokens) {
        this.tokens = tokens;
    }

}