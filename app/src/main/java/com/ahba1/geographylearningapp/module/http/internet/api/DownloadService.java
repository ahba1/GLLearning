package com.ahba1.geographylearningapp.module.http.internet.api;

import com.ahba1.geographylearningapp.module.http.BaseRouter;
import com.ahba1.geographylearningapp.module.http.model.CatalogInfo;
import com.ahba1.geographylearningapp.module.http.model.DownloadInfo;
import com.ahba1.geographylearningapp.tools.HttpClient;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;


@BaseRouter(url = HttpClient.BASE_URL)
public interface DownloadService {

    @GET(HttpClient.DOWNLOAD_RELATIVE_URL)
    Observable<CatalogInfo> getDownloadList(
            @Header("Authorization") String token,
            @Query("ancestor") @Nullable Integer ancestor
    );

    @Streaming
    @GET("http://175.24.111.33/{uri}")
    Observable<ResponseBody> downloadFile(@Header("Range") String range, @Path("uri") String uri);
}
