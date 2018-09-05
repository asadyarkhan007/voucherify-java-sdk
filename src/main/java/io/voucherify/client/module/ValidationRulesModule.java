package io.voucherify.client.module;

import io.reactivex.Observable;
import io.voucherify.client.api.VoucherifyApi;
import io.voucherify.client.callback.VoucherifyCallback;
import io.voucherify.client.model.validationRules.ValidationRules;
import io.voucherify.client.model.validationRules.response.ValidationRulesResponse;
import io.voucherify.client.module.ValidationRulesModule.ExtAsync;
import io.voucherify.client.module.ValidationRulesModule.ExtRxJava;
import io.voucherify.client.utils.Irrelevant;
import io.voucherify.client.utils.RxUtils;

import java.util.concurrent.Executor;

public final class ValidationRulesModule extends AbsModule<ExtAsync, ExtRxJava> {

  public ValidationRulesModule(VoucherifyApi api, Executor executor) {
    super(api, executor);
  }

  public ValidationRulesResponse create(ValidationRules validationRules) {
    return executeSyncApiCall(api.createValidationRules(validationRules));
  }

  public ValidationRulesResponse get(String id) {
    return executeSyncApiCall(api.getValidationRules(id));
  }

  public ValidationRulesResponse update(ValidationRules validationRules) {
    return executeSyncApiCall(api.updateValidationRules(validationRules.getId(), validationRules));
  }

  public void delete(String id) {
    executeSyncApiCall(api.deleteValidationRules(id));
  }

  @Override
  ExtAsync createAsyncExtension() {
    return new ExtAsync();
  }

  @Override
  ExtRxJava createRxJavaExtension() {
    return new ExtRxJava();
  }

  @Override
  public ExtAsync async() {
    return extAsync;
  }

  @Override
  public ExtRxJava rx() {
    return extRxJava;
  }

  public class ExtAsync extends AbsModule.Async {

    public void create(
        ValidationRules validationRules, VoucherifyCallback<ValidationRulesResponse> callback) {
      RxUtils.subscribe(executor, rx().create(validationRules), callback);
    }

    public void get(String id, VoucherifyCallback<ValidationRulesResponse> callback) {
      RxUtils.subscribe(executor, rx().get(id), callback);
    }

    public void update(
        ValidationRules validationRules, VoucherifyCallback<ValidationRulesResponse> callback) {
      RxUtils.subscribe(executor, rx().update(validationRules), callback);
    }

    public void delete(String id, VoucherifyCallback<Irrelevant> callback) {
      RxUtils.subscribe(executor, rx().delete(id), callback);
    }
  }

  public class ExtRxJava extends AbsModule.Rx {

    public Observable<ValidationRulesResponse> create(final ValidationRules validationRules) {
      return RxUtils.defer(
          new RxUtils.DefFunc<ValidationRulesResponse>() {

            @Override
            public ValidationRulesResponse method() {
              return ValidationRulesModule.this.create(validationRules);
            }
          });
    }

    public Observable<ValidationRulesResponse> get(final String id) {
      return RxUtils.defer(
          new RxUtils.DefFunc<ValidationRulesResponse>() {

            @Override
            public ValidationRulesResponse method() {
              return ValidationRulesModule.this.get(id);
            }
          });
    }

    public Observable<ValidationRulesResponse> update(final ValidationRules validationRules) {
      return RxUtils.defer(
          new RxUtils.DefFunc<ValidationRulesResponse>() {

            @Override
            public ValidationRulesResponse method() {
              return ValidationRulesModule.this.update(validationRules);
            }
          });
    }

    public Observable<Irrelevant> delete(final String id) {
      return RxUtils.defer(
          new RxUtils.DefFunc<Irrelevant>() {

            @Override
            public Irrelevant method() {
              ValidationRulesModule.this.delete(id);
              return Irrelevant.NO_RESPONSE;
            }
          });
    }
  }
}
