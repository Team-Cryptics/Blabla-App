package vidur.codeclan.projectx.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("objects")
    @Expose
    private List<Object> objects = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    /**
     * No args constructor for use in serialization
     *
     */
    public Example() {
    }

    /**
     *
     * @param page
     * @param numResults
     * @param objects
     * @param totalPages
     */
    public Example(Integer numResults, List<Object> objects, Integer page, Integer totalPages) {
        super();
        this.numResults = numResults;
        this.objects = objects;
        this.page = page;
        this.totalPages = totalPages;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
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