package io.voucherify.client;

import okhttp3.logging.HttpLoggingInterceptor.Level;

public enum LogLevel {

  NONE {

    @Override
    public Level toHttpLogLevel() {
      return Level.NONE;
    }
  },
  BASIC {

    @Override
    public Level toHttpLogLevel() {
      return Level.BASIC;
    }
  },
  HEADERS {

    @Override
    public Level toHttpLogLevel() {
      return Level.HEADERS;
    }
  },
  BODY {

    @Override
    public Level toHttpLogLevel() {
      return Level.BODY;
    }
  };

  abstract public Level toHttpLogLevel();
}
