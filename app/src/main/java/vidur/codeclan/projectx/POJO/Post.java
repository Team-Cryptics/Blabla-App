package vidur.codeclan.projectx.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("objects")
    @Expose
    private List<PostObject> postObjects;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<PostObject> getPostObjects() {
        return postObjects;
    }

    public void setPostObjects(List<PostObject> postObjects) {
        this.postObjects = postObjects;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}