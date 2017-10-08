package io.voucherify.client.utils.adapter.sync;

import io.voucherify.client.error.VoucherifyError;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class SyncResponseCallAdapter<T> implements CallAdapter<T, Response<T>> {

  private final Type responseType;

  SyncResponseCallAdapter(Type responseType) {
    this.responseType = responseType;
  }

  @Override
  public Type responseType() {
    return responseType;
  }

  @Override
  public Response<T> adapt(Call<T> call) {
    Response<T> response;
    try {
      response = call.execute();
    } catch (IOException e) {
      throw VoucherifyError.from(e);
    }

    if (response == null) {
      return null;
    }

    if (response.isSuccessful()) {
      return Response.success(response.body(), response.raw());
    }

    return Response.error(response.code(), response.errorBody());
  }
}
