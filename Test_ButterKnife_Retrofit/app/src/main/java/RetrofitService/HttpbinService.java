package RetrofitService;

import model.Response;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by nazar on 09.06.15.
 */
public interface HttpbinService {
    @GET("/get")
    void getGETResponse(Callback<Response> cb);

    @POST("/post")
    void getPOSTResponse(Callback<Response> cb);

    @POST("/get")
    void getErrorResponse(Callback<Response> cb);
}
