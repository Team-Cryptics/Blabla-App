package vidur.codeclan.projectx.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("activation_status")
    @Expose
    private Boolean activationStatus;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("flwrs")
    @Expose
    private Integer flwrs;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("interests")
    @Expose
    private String interests;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("pwdhash")
    @Expose
    private String pwdhash;
    @SerializedName("tokens")
    @Expose
    private java.lang.Object tokens;

    /**
     * No args constructor for use in serialization
     *
     */
    public Author() {
    }

    /**
     *
     * @param id
     * @param activationStatus
     * @param nickname
     * @param interests
     * @param email
     * @param flwrs
     * @param tokens
     * @param pwdhash
     */
    public Author(Boolean activationStatus, String email, Integer flwrs, Integer id, String interests, String nickname, String pwdhash, java.lang.Object tokens) {
        super();
        this.activationStatus = activationStatus;
        this.email = email;
        this.flwrs = flwrs;
        this.id = id;
        this.interests = interests;
        this.nickname = nickname;
        this.pwdhash = pwdhash;
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

    public String getPwdhash() {
        return pwdhash;
    }

    public void setPwdhash(String pwdhash) {
        this.pwdhash = pwdhash;
    }

    public java.lang.Object getTokens() {
        return tokens;
    }

    public void setTokens(java.lang.Object tokens) {
        this.tokens = tokens;
    }

}