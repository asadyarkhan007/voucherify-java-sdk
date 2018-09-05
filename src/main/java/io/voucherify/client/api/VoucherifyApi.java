package io.voucherify.client.api;

import io.voucherify.client.model.campaign.AddVoucherToCampaign;
import io.voucherify.client.model.campaign.CampaignImportVouchers;
import io.voucherify.client.model.campaign.CreateCampaign;
import io.voucherify.client.model.campaign.UpdateCampaign;
import io.voucherify.client.model.campaign.response.AddVoucherToCampaignResponse;
import io.voucherify.client.model.campaign.response.CampaignResponse;
import io.voucherify.client.model.campaign.response.CampaignsResponse;
import io.voucherify.client.model.customer.Customer;
import io.voucherify.client.model.customer.response.CustomerResponse;
import io.voucherify.client.model.customer.response.CustomersResponse;
import io.voucherify.client.model.distribution.CreateExport;
import io.voucherify.client.model.distribution.PublishVoucher;
import io.voucherify.client.model.distribution.response.ExportResponse;
import io.voucherify.client.model.distribution.response.ListPublicationsResponse;
import io.voucherify.client.model.distribution.response.PublishVoucherResponse;
import io.voucherify.client.model.event.CustomEvent;
import io.voucherify.client.model.event.response.CustomEventResponse;
import io.voucherify.client.model.order.CreateOrder;
import io.voucherify.client.model.order.UpdateOrder;
import io.voucherify.client.model.order.response.CreateOrderResponse;
import io.voucherify.client.model.order.response.GetOrderResponse;
import io.voucherify.client.model.order.response.ListOrdersResponse;
import io.voucherify.client.model.product.Product;
import io.voucherify.client.model.product.SKU;
import io.voucherify.client.model.product.response.ProductResponse;
import io.voucherify.client.model.product.response.ProductsResponse;
import io.voucherify.client.model.product.response.SKUResponse;
import io.voucherify.client.model.product.response.SKUsResponse;
import io.voucherify.client.model.promotion.CreatePromotionCampaign;
import io.voucherify.client.model.promotion.Tier;
import io.voucherify.client.model.promotion.reponse.CreatePromotionCampaignResponse;
import io.voucherify.client.model.promotion.reponse.ListPromotionTiersResponse;
import io.voucherify.client.model.promotion.reponse.TierResponse;
import io.voucherify.client.model.redemption.RedeemPromotion;
import io.voucherify.client.model.redemption.RedeemVoucher;
import io.voucherify.client.model.redemption.RollbackRedemption;
import io.voucherify.client.model.redemption.response.RedeemPromotionResponse;
import io.voucherify.client.model.redemption.response.RedeemVoucherResponse;
import io.voucherify.client.model.redemption.response.RedemptionEntryResponse;
import io.voucherify.client.model.redemption.response.RedemptionsResponse;
import io.voucherify.client.model.redemption.response.RollbackRedemptionResponse;
import io.voucherify.client.model.redemption.response.VoucherRedemptionsResponse;
import io.voucherify.client.model.segment.Segment;
import io.voucherify.client.model.segment.response.SegmentResponse;
import io.voucherify.client.model.validation.PromotionValidation;
import io.voucherify.client.model.validation.VoucherValidation;
import io.voucherify.client.model.validation.VoucherValidationResponse;
import io.voucherify.client.model.validation.response.PromotionValidationResponse;
import io.voucherify.client.model.validationRules.ValidationRules;
import io.voucherify.client.model.validationRules.response.ValidationRulesResponse;
import io.voucherify.client.model.voucher.AddBalance;
import io.voucherify.client.model.voucher.CreateVoucher;
import io.voucherify.client.model.voucher.ImportVouchers;
import io.voucherify.client.model.voucher.VoucherUpdate;
import io.voucherify.client.model.voucher.response.AddBalanceResponse;
import io.voucherify.client.model.voucher.response.VoucherResponse;
import io.voucherify.client.model.voucher.response.VouchersResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface VoucherifyApi {

  // CAMPAIGNS

  @POST("campaigns")
  Call<CampaignResponse> createCampaign(@Body CreateCampaign createCampaign);

  @POST("campaigns/{name}/vouchers")
  Call<AddVoucherToCampaignResponse> addVoucherToCampaign(
      @Path("name") String campaignName, @Body AddVoucherToCampaign addVoucherToCampaign);

  @GET("campaigns/{name}")
  Call<CampaignResponse> getCampaign(@Path("name") String campaignName);

  @GET("campaigns")
  Call<CampaignsResponse> listCampaigns(@QueryMap Map<String, Object> filter);

  @PUT("campaigns/{name}")
  Call<CampaignResponse> updateCampaign(
      @Path("name") String campaignName, @Body UpdateCampaign updateCampaign);

  @POST("campaigns/{name}/vouchers/{code}")
  Call<AddVoucherToCampaignResponse> addVoucherToCampaignWithCode(
      @Path("name") String campaignName,
      @Path("code") String voucherCode,
      @Body AddVoucherToCampaign addVoucherToCampaign);

  @DELETE("campaigns/{name}")
  Call<Void> deleteCampaign(@Path("name") String campaignName, @Query("force") Boolean force);

  @POST("campaigns/{name}/import")
  Call<Void> importVouchersToCampaign(
      @Path("name") String campaignName, @Body CampaignImportVouchers importVouchers);

  // CUSTOMERS

  @POST("customers")
  Call<CustomerResponse> createCustomer(@Body Customer customer);

  @GET("customers/{id}")
  Call<CustomerResponse> getCustomerById(@Path("id") String customerId);

  @PUT("customers/{id}")
  Call<CustomerResponse> updateCustomer(@Path("id") String customerId, @Body Customer customer);

  @DELETE("customers/{id}")
  Call<Void> deleteCustomer(@Path("id") String customerId);

  @GET("customers")
  Call<CustomersResponse> listCustomers(@QueryMap Map<String, Object> filter);

  // REDEMPTIONS

  @POST("vouchers/{code}/redemption")
  Call<RedeemVoucherResponse> redeem(@Path("code") String code, @Body RedeemVoucher redeemVoucher);

  @POST("promotions/tiers/{id}/redemption")
  Call<RedeemPromotionResponse> redeem(
      @Path("id") String id, @Body RedeemPromotion redeemPromotion);

  @GET("redemptions")
  Call<RedemptionsResponse> listRedemptions(@QueryMap Map<String, Object> filter);

  @GET("vouchers/{code}/redemption")
  Call<VoucherRedemptionsResponse> getVoucherRedemptions(@Path("code") String code);

  @POST("redemptions/{id}/rollback")
  Call<RollbackRedemptionResponse> rollbackRedemption(
      @Path("id") String redemptionId,
      @Query("reason") String reason,
      @Body RollbackRedemption rollbackRedemption);

  @GET("redemptions/{id}")
  Call<RedemptionEntryResponse> getRedemption(@Path("id") String redemptionId);

  // DISTRIBUTIONS

  @POST("vouchers/publish")
  Call<PublishVoucherResponse> publishVoucher(@Body PublishVoucher publishVoucher);

  @POST("exports")
  Call<ExportResponse> createExport(@Body CreateExport createExport);

  @GET("exports/{id}")
  Call<ExportResponse> getExport(@Path("id") String id);

  @DELETE("exports/{id}")
  Call<Void> deleteExport(@Path("id") String id);

  @GET("publications")
  Call<ListPublicationsResponse> list(@QueryMap Map<String, Object> filter);

  // VOUCHERS

  @POST("vouchers")
  Call<VoucherResponse> createVoucher(@Body CreateVoucher createVoucher);

  @POST("vouchers/{code}")
  Call<VoucherResponse> createVoucher(@Path("code") String code, @Body CreateVoucher createVoucher);

  @GET("vouchers/{code}")
  Call<VoucherResponse> getVoucher(@Path("code") String code);

  @PUT("vouchers/{code}")
  Call<VoucherResponse> updateVoucher(@Path("code") String code, @Body VoucherUpdate voucherUpdate);

  @DELETE("vouchers/{code}")
  Call<Void> deleteVoucher(@Path("code") String code, @Query("force") Boolean force);

  @GET("vouchers")
  Call<VouchersResponse> listVouchers(@QueryMap Map<String, Object> filter);

  @POST("vouchers/{code}/enable")
  Call<VoucherResponse> enable(@Path("code") String code);

  @POST("vouchers/{code}/disable")
  Call<VoucherResponse> disable(@Path("code") String code);

  @POST("vouchers/{code}/balance")
  Call<AddBalanceResponse> addBalance(@Path("code") String code, @Body AddBalance addBalance);

  @POST("vouchers/import")
  Call<Void> importVouchers(@Body ImportVouchers vouchers);

  // VALIDATIONS

  @POST("vouchers/{code}/validate")
  Call<VoucherValidationResponse> validateVoucher(
      @Path("code") String code, @Body VoucherValidation voucherValidation);

  @POST("promotions/validation")
  Call<PromotionValidationResponse> validatePromotion(
      @Body PromotionValidation promotionValidation);

  // PRODUCTS

  @POST("products")
  Call<ProductResponse> createProduct(@Body Product product);

  @GET("products/{id}")
  Call<ProductResponse> getProduct(@Path("id") String id);

  @PUT("products/{id}")
  Call<ProductResponse> updateProduct(@Path("id") String id, @Body Product product);

  @GET("products")
  Call<ProductsResponse> getProducts(@QueryMap Map<String, Object> filter);

  @DELETE("products/{id}")
  Call<Void> deleteProduct(@Path("id") String id, @QueryMap Map<String, Object> params);

  // SKU

  @POST("products/{id}/skus")
  Call<SKUResponse> createSKU(@Path("id") String productId, @Body SKU sku);

  @GET("products/{id}/skus/{skuId}")
  Call<SKUResponse> getSKU(@Path("id") String productId, @Path("skuId") String skuId);

  @PUT("products/{id}/skus/{skuId}")
  Call<SKUResponse> updateSKU(
      @Path("id") String productId, @Path("skuId") String skuId, @Body SKU sku);

  @GET("products/{id}/skus")
  Call<SKUsResponse> getSKUs(@Path("id") String productId);

  @DELETE("products/{id}/skus/{skuId}")
  Call<Void> deleteSKU(
      @Path("id") String productId,
      @Path("skuId") String skuId,
      @QueryMap Map<String, Object> params);

  // SEGMENTS

  @POST("segments")
  Call<SegmentResponse> createSegment(@Body Segment segment);

  @GET("segments/{id}")
  Call<SegmentResponse> getSegment(@Path("id") String id);

  @DELETE("segments/{id}")
  Call<Void> deleteSegment(@Path("id") String id);

  // VALIDATION RULES

  @POST("validation-rules")
  Call<ValidationRulesResponse> createValidationRules(@Body ValidationRules validationRules);

  @GET("validation-rules/{id}")
  Call<ValidationRulesResponse> getValidationRules(@Path("id") String id);

  @PUT("validation-rules/{id}")
  Call<ValidationRulesResponse> updateValidationRules(
      @Path("id") String id, @Body ValidationRules validationRules);

  @DELETE("validation-rules/{id}")
  Call<Void> deleteValidationRules(@Path("id") String id);

  // PROMOTIONS

  @POST("campaigns")
  Call<CreatePromotionCampaignResponse> createPromotionCampaign(
      @Body CreatePromotionCampaign createPromotionCampaign);

  @GET("promotions/{id}/tiers")
  Call<ListPromotionTiersResponse> listPromotionTiers(@Path("id") String id);

  @POST("promotions/{id}/tiers")
  Call<TierResponse> addPromotionTier(@Path("id") String id, @Body Tier tier);

  @PUT("promotions/tiers/{id}")
  Call<TierResponse> updatePromotionTier(@Path("id") String id, @Body Tier tier);

  @DELETE("promotions/tiers/{id}")
  Call<Void> deletePromotionTier(@Path("id") String id);

  // ORDERS
  @POST("orders")
  Call<CreateOrderResponse> createOrder(@Body CreateOrder createOrder);

  @GET("orders/{id}")
  Call<GetOrderResponse> getOrder(@Path("id") String id);

  @PUT("orders/{id}")
  Call<GetOrderResponse> updateOrder(@Path("id") String id, @Body UpdateOrder updateOrder);

  @GET("orders")
  Call<ListOrdersResponse> listOrders(@QueryMap Map<String, Object> filter);

  // EVENTS
  @POST("events")
  Call<CustomEventResponse> createCustomEvent(@Body CustomEvent event);
}
