package io.voucherify.client.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import okhttp3.ResponseBody;
import retrofit2.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class VoucherifyError extends RuntimeException {

  private Integer code;

  private String details;

  private String key;

  private VoucherifyError(String message) {
    super(message);
    this.code = 500;
    this.details = message;
  }

  private VoucherifyError(int code, String message) {
    super(message);
    this.code = 500;
    this.details = message;
  }

  private VoucherifyError(WrappedError wrapped) {
    super(wrapped.getMessage());
    this.code = wrapped.getCode();
    this.details = wrapped.getDetails();
    this.key = wrapped.getKey();
  }

  private VoucherifyError(Throwable throwable) {
    super(throwable);
    this.code = 500;
  }

  public static VoucherifyError unexpectedError() {
    return new VoucherifyError("Unexpected error occurred");
  }

  public static VoucherifyError from(WrappedError error) {
    return new VoucherifyError(error);
  }

  public static VoucherifyError from(Throwable throwable) {
    return new VoucherifyError(throwable);
  }

  public static VoucherifyError from(String message) {
    return new VoucherifyError(message);
  }

  public static VoucherifyError from(Response response) {
    return new VoucherifyError(response.code(), response.message());
  }

}
