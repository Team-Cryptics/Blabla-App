
package vidur.codeclan.projectx.POJO;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Bookmark {

    @SerializedName("num_results")
    private Long mNumResults;
    @SerializedName("objects")
    private List<Object> mObjects;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("total_pages")
    private Long mTotalPages;

    public Long getNumResults() {
        return mNumResults;
    }

    public void setNumResults(Long numResults) {
        mNumResults = numResults;
    }

    public List<Object> getObjects() {
        return mObjects;
    }

    public void setObjects(List<Object> objects) {
        mObjects = objects;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

}
