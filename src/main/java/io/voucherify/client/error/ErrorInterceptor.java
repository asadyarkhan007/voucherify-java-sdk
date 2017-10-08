package io.voucherify.client.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ErrorInterceptor implements Interceptor {

  private ObjectMapper mapper = new ObjectMapper();


  @Override
  public Response intercept(Chain chain) throws IOException {
    Response response = chain.proceed(chain.request());

    if (response.isSuccessful()) {
      return response;
    }

    ResponseBody body = response.body();
    if (body != null) {
      WrappedError error = mapper.readValue(body.string(), WrappedError.class);
      throw VoucherifyError.from(error);
    }

    throw VoucherifyError.unexpectedError();
  }

}
