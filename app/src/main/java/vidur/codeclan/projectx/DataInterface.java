package vidur.codeclan.projectx;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import vidur.codeclan.projectx.POJO.LoginInfo;

/**
 * Created by samarthgupta on 23/06/17.
 */

public interface DataInterface {

    @POST(" ")
    Call<LoginInfo> loginUser(@Body LoginInfo info);
}
