package io.voucherify.client.utils.response;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmptyResponse {

  public static EmptyResponse create() {
    return new EmptyResponse();
  }
}
