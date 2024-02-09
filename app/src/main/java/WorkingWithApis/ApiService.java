package WorkingWithApis;

import java.util.List;

import Models.SentimentRequest;
import Models.SentimentResponse;
import Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/home")
    Call<SentimentResponse>analyzeSentiment(@Body SentimentRequest sentimentRequest);
}
