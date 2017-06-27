package vidur.codeclan.projectx.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samarthgupta on 27/06/17.
 */

public class User {
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("objects")
    @Expose
    private List<UserObject> objects = null;
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

    public List<UserObject> getObjects() {
        return objects;
    }

    public void setObjects(List<UserObject> objects) {
        this.objects = objects;
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
