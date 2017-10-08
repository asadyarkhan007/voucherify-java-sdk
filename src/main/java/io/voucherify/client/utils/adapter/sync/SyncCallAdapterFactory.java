package io.voucherify.client.utils.adapter.sync;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SyncCallAdapterFactory extends CallAdapter.Factory {

  public static CallAdapter.Factory create() {
    return new SyncCallAdapterFactory();
  }

  @Override
  public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
    if (getRawType(returnType) == Call.class) {
      return null;
    }

    if (getRawType(returnType) != Response.class) {
      return new SyncBodyCallAdapter<>(returnType);
    }

    final Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);

    return new SyncResponseCallAdapter<>(responseType);
  }
}
