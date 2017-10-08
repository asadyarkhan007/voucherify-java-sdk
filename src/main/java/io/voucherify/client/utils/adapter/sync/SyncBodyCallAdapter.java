package io.voucherify.client.utils.adapter.sync;

import io.voucherify.client.error.VoucherifyError;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class SyncBodyCallAdapter<T> implements CallAdapter<T, Object> {

  private final Type responseType;

  SyncBodyCallAdapter(Type responseType) {
    this.responseType = responseType;
  }

  @Override
  public Type responseType() {
    return responseType;
  }

  @Override
  public Object adapt(Call<T> call) {
    Response<T> response;

    try {
      response = call.execute();
    } catch (IOException e) {
      throw VoucherifyError.from(e);
    }

    if (response == null) {
      throw VoucherifyError.from("No response received");
    }

    if (response.isSuccessful()) {
      return response.body();
    }

    throw VoucherifyError.from(response);
  }
}
