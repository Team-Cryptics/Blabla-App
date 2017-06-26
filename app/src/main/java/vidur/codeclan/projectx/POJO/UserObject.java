package vidur.codeclan.projectx.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by samarthgupta on 27/06/17.
 */

public class UserObject {

    @SerializedName("activation_status")
    @Expose
    private Boolean activationStatus;
    @SerializedName("bitmap")
    @Expose
    private java.lang.Object bitmap;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("interests")
    @Expose
    private String interests;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("tokens")
    @Expose
    private java.lang.Object tokens;

    public Boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public java.lang.Object getBitmap() {
        return bitmap;
    }

    public void setBitmap(java.lang.Object bitmap) {
        this.bitmap = bitmap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public java.lang.Object getTokens() {
        return tokens;
    }

    public void setTokens(java.lang.Object tokens) {
        this.tokens = tokens;
    }
}
