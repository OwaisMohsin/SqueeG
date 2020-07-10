package com.example.co.squeeg.network;

import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.Model.GETAllGigs;
import com.example.co.squeeg.Model.GETCategory;
import com.example.co.squeeg.Model.GETCountry;
import com.example.co.squeeg.Model.GETFooterInformation;
import com.example.co.squeeg.Model.GETFooterMenu;
import com.example.co.squeeg.Model.GETHomeGigs;
import com.example.co.squeeg.Model.GETLanguageList;
import com.example.co.squeeg.Model.GETLogoutResponse;
import com.example.co.squeeg.Model.GETMyGigs;
import com.example.co.squeeg.Model.GETPaymentGatewayKeys;
import com.example.co.squeeg.Model.GETPriceDetails;
import com.example.co.squeeg.Model.GETProfession;
import com.example.co.squeeg.Model.GETState;
import com.example.co.squeeg.Model.GETStripeConfig;
import com.example.co.squeeg.Model.POSTSellerOfferResponse;
import com.example.co.squeeg.Model.POSTSubscriptionModel;
import com.example.co.squeeg.Model.GETTermsAndConditions;
import com.example.co.squeeg.Model.POSTAcceptBuyRequest;
import com.example.co.squeeg.Model.POSTAcceptSellerDeclineRequest;
import com.example.co.squeeg.Model.POSTAddFav;
import com.example.co.squeeg.Model.POSTBuyNow;
import com.example.co.squeeg.Model.POSTCancelGigs;
import com.example.co.squeeg.Model.POSTChangePassword;
import com.example.co.squeeg.Model.POSTChatHistory;
import com.example.co.squeeg.Model.POSTCreategigs;
import com.example.co.squeeg.Model.POSTDetailGig;
import com.example.co.squeeg.Model.POSTEditGigs;
import com.example.co.squeeg.Model.POSTFavGigs;
import com.example.co.squeeg.Model.POSTForgotPassword;
import com.example.co.squeeg.Model.POSTGigsReview;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTLastVisitedGigs;
import com.example.co.squeeg.Model.POSTLeaveFeedback;
import com.example.co.squeeg.Model.POSTLogin;
import com.example.co.squeeg.Model.POSTMessages;
import com.example.co.squeeg.Model.POSTMyActivity;
import com.example.co.squeeg.Model.POSTPaymentSuccess;
import com.example.co.squeeg.Model.POSTPaypalSettings;
import com.example.co.squeeg.Model.POSTPurchaseCompletedAcceptStatus;
import com.example.co.squeeg.Model.POSTPurchaseSeeFedBck;
import com.example.co.squeeg.Model.POSTRegister;
import com.example.co.squeeg.Model.POSTRejectResponse;
import com.example.co.squeeg.Model.POSTRemoveFav;
import com.example.co.squeeg.Model.POSTSearchGigs;
import com.example.co.squeeg.Model.POSTSellerReviews;
import com.example.co.squeeg.Model.POSTSubCategory;
import com.example.co.squeeg.Model.POSTSubscriptionSuccess;
import com.example.co.squeeg.Model.POSTUpdateProfile;
import com.example.co.squeeg.Model.POSTUserChat;
import com.example.co.squeeg.Model.POSTViewProfile;
import com.example.co.squeeg.Model.POSTVisitGig;
import com.example.co.squeeg.Model.buyer_requests_for_buyer.POSTBuyerRequestForBuyerList;
import com.example.co.squeeg.Model.buyer_requests_for_seller.POSTBuyerRequestForSellerList;
import com.example.co.squeeg.Model.seller_offers_for_buyer.POSTSellerOffersForBuyerList;
import com.example.co.squeeg.Model.seller_services.POSTSellerServicesList;
import com.example.co.squeeg.Model.user_type.UserTypeList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("gigs/messages")
    Call<POSTMessages> postmessages(@Field("page") String page, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("user/login")
    Call<POSTLogin> postLogin(@FieldMap HashMap<String, String> loginData, @Header("token") String token, @Header("language") String Language);

    @GET("gigs")
    Call<GETHomeGigs> getHomeGigs(@Header("token") String user_id, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/categories")
    Call<GETAllGigs> getUserGigs(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/gigs_details")
    Call<POSTDetailGig> getDetailGigs(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/last_visit")
    Call<POSTVisitGig> postVisitedGigs(@FieldMap HashMap<String, String> visitedGigs, @Header("token") String token, @Header("language") String Language);

    @GET("user/country")
    Call<List<GETCountry>> getCountry(@Header("token") String token, @Header("language") String Language);

    @GET("user/state/{stateid}")
    Call<List<GETState>> getState(@Path(value = "stateid", encoded = true) String stateid, @Header("token") String token, @Header("language") String Language);

    @Multipart
    @POST("user/registration")
    Call<POSTRegister> postRegister(@Part("email") RequestBody email,
                                    @Part("username") RequestBody username,
                                    @Part("password") RequestBody password,
                                    @Part("state") RequestBody state,
                                    @Part("country") RequestBody country,
                                    @Part("fullname") RequestBody fullname,
                                    @Part("user_timezone") RequestBody user_timezone,
                                    @Part MultipartBody.Part image,
                                    @Header("token") String token, @Header("language") String Language);


    @FormUrlEncoded
    @POST("user/forgot_password")
    Call<POSTForgotPassword> postForgot(@FieldMap HashMap<String, String> forgotPassword, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("user/change_password")
    Call<POSTChangePassword> postChangePassword(@FieldMap HashMap<String, String> changePassword, @Header("token") String token, @Header("language") String Language);

    @GET("user/profession")
    Call<List<GETProfession>> getProfession(@Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/favourites_gigs")
    Call<POSTFavGigs> getFavLists(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("user/paypal_setting")
    Call<POSTPaypalSettings> postPaypalSettings(@FieldMap HashMap<String, String> paypalsettings, @Header("token") String token, @Header("language") String Language);

    @Multipart
    @POST("user/profile")
    Call<POSTUpdateProfile> postUpdateProfile(@Part("user_contact") RequestBody user_contact,
                                              @Part("user_zip") RequestBody user_zip,
                                              @Part("user_city") RequestBody user_city,
                                              @Part("user_addr") RequestBody user_addr,
                                              @Part("user_desc") RequestBody user_desc,
                                              @Part("country_id") RequestBody country_id,
                                              @Part("state_id") RequestBody state_id,
                                              @Part("profession") RequestBody profession,
                                              @Part("user_name") RequestBody user_name,
                                              @Part("language_tags") RequestBody language_tags,
                                              @Part MultipartBody.Part image,
                                              @Header("token") String token, @Header("language") String Language);

    @GET("gigs/categories")
    Call<GETCategory> getCategories(@Header("token") String token, @Header("language") String Language);

    @GET("gigs/user_type")
    Call<UserTypeList> agetUserType();

    @GET("gigs/categories/{type}")
    Call<GETCategory> getCategoriesAll(@Path(value = "type", encoded = true) String type, @Header("token") String token, @Header("language") String Language);

    @GET("gigs/payment_gateways")
    Call<GETPaymentGatewayKeys> getPaymentKeys(@Header("token") String token, @Header("language") String Language);

    @GET("gigs/footer")
    Call<GETFooterInformation> getInformation(@Header("token") String token, @Header("language") String Language);

    @GET("gigs/footer_menu")
    Call<GETFooterMenu> getHelpInformation(@Header("token") String token, @Header("language") String Language);


    @GET("gigs/terms")
    Call<GETTermsAndConditions> getTermsandConditions(@Header("token") String token, @Header("language") String Language);

    @GET("gigs/gigs_list/{user_id}")
    Call<GETAllGigs> getAllGigs(@Path(value = "user_id", encoded = true) String stateid, @Header("token") String token, @Header("language") String Language);


    @GET("gigs/my_gigs/{user_id}")
    Call<GETMyGigs> getMyGigs(@Path(value = "user_id", encoded = true) String userid, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/my_gigs")
    Call<GETMyGigs> postMyGigs(@FieldMap HashMap<String, String> sellerReviews, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/seller_reviews")
    Call<POSTSellerReviews> getSellerReviews(@FieldMap HashMap<String, String> sellerReviews, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/search_gig")
    Call<POSTSearchGigs> postSearchGigs(@FieldMap HashMap<String, String> searchGigs, @Header("token") String token, @Header("language") String Language);


    @FormUrlEncoded
    @POST("gigs/seller_buyer_review")
    Call<POSTGigsReview> getGigsReviews(@FieldMap HashMap<String, String> sellerReviews, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("user/profile_details")
    Call<POSTViewProfile> postViewProfile(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/categories")
    Call<POSTSubCategory> getSubCategory(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);


    @FormUrlEncoded
    @POST("gigs/add_favourites")
    Call<POSTAddFav> addFav(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/remove_favourites")
    Call<POSTRemoveFav> removeFav(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);


    /*@Multipart
    @POST("gigs/create_gigs")
    Call<POSTCreategigs> createGigs(@FieldMap HashMap<String, String> updateProfile);*/
    @Multipart
    @POST("gigs/create_gigs")
    Call<POSTCreategigs> createGigs(/*@Part("user_id") RequestBody user_id,*/
            @Part("title") RequestBody title,
            @Part("delivering_time") RequestBody delivering_time,
            @Part("gig_price") RequestBody gig_price,
            @Part("gig_tags") RequestBody gig_tags,
            @Part("gig_details") RequestBody gig_details,
            @Part("super_fast_delivery") RequestBody super_fast_delivery,
            @Part("super_fast_delivery_desc") RequestBody super_fast_delivery_desc,
            @Part("super_fast_delivery_date") RequestBody super_fast_delivery_date,
            @Part("super_fast_charges") RequestBody super_fast_charges,
            @Part("requirements") RequestBody requirements,
            @Part("work_option") RequestBody work_option,
            @Part("terms_conditions") RequestBody terms_conditions,
            @Part("extra_gigs") RequestBody extra_gigs,
            @Part("time_zone") RequestBody time_zone,
            @Part("category_id") RequestBody category_id,
            @Part("cost_type") RequestBody price_option,
            @Part MultipartBody.Part image,
            @Header("token") String token,
            @Header("language") String Language
    );

    @Multipart
    @POST("gigs/create_buyer_request")
    Call<POSTCreategigs> createBuyerRequest(/*@Part("user_id") RequestBody user_id,*/
            @Part("USERID") RequestBody USERID,
            @Part("description") RequestBody description,
            @Part("category_id") RequestBody category_id,
            @Part("delivery_time") RequestBody delivery_time,
            @Part("budget") RequestBody budget,
            @Part("date") RequestBody date,
            @Part("time") RequestBody time,
            @Header("token") String token,
            @Header("language") String Language
    );

    @Multipart
    @POST("gigs/create_seller_offer")
    Call<POSTSellerOfferResponse> createSellerOffer(
            @Part("user_id") RequestBody user_id,
            @Part("buyer_id") RequestBody buyer_id,
            @Part("gig_id") RequestBody gig_id,
            @Part("request_id") RequestBody request_id,
            @Part("revision") RequestBody revision,
            @Part("duration") RequestBody duration,
            @Part("description") RequestBody description,
            @Part("budget") RequestBody budget,
            @Part("date") RequestBody date,
            @Part("time") RequestBody time,
            @Header("token") String token,
            @Header("language") String Language
    );

    @Multipart
    @POST("gigs/load_buyer_requests_for_seller")
    Call<POSTBuyerRequestForSellerList> loadBuyerRequestsForSeller(
            @Part("user_id") RequestBody user_id,
            @Header("token") String token,
            @Header("language") String Language
    );

    @Multipart
    @POST("gigs/load_Seller_Offers_for_buyer")
    Call<POSTSellerOffersForBuyerList> loadSellerOffersForBuyer(
            @Part("buyer_id") RequestBody buyer_id,
            @Part("request_id") RequestBody request_id,
            @Header("token") String token,
            @Header("language") String Language
    );

    @Multipart
    @POST("gigs/load_buyer_requests_for_buyer")
    Call<POSTBuyerRequestForBuyerList> loadBuyerRequestsForBuyer(
            @Part("user_id") RequestBody user_id,
            @Header("token") String token,
            @Header("language") String Language
    );

    @Multipart
    @POST("gigs/load_seller_services")
    Call<POSTSellerServicesList> loadSellerServices(
            @Part("user_id") RequestBody user_id,
            @Header("token") String token,
            @Header("language") String Language
    );

    @Multipart
    @POST("gigs/update_gigs")
    Call<POSTCreategigs> updateGigs(@Part("gig_id") RequestBody gig_id,
                                    @Part("user_id") RequestBody user_id,
                                    @Part("title") RequestBody title,
                                    @Part("delivering_time") RequestBody delivering_time,
                                    @Part("gig_price") RequestBody gig_price,
                                    @Part("gig_tags") RequestBody gig_tags,
                                    @Part("gig_details") RequestBody gig_details,
                                    @Part("super_fast_delivery") RequestBody super_fast_delivery,
                                    @Part("super_fast_delivery_desc") RequestBody super_fast_delivery_desc,
                                    @Part("super_fast_delivery_date") RequestBody super_fast_delivery_date,
                                    @Part("super_fast_charges") RequestBody super_fast_charges,
                                    @Part("requirements") RequestBody requirements,
                                    @Part("work_option") RequestBody work_option,
                                    @Part("terms_conditions") RequestBody terms_conditions,
                                    @Part("extra_gigs") RequestBody extra_gigs,
                                    @Part("category_id") RequestBody category_id,
                                    @Part MultipartBody.Part image, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/last_visited_gigs")
    Call<POSTLastVisitedGigs> getLastVisitedGigs(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);


    @FormUrlEncoded
    @POST("gigs/edit_gigs")
    Call<POSTEditGigs> editGigs(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);


    @POST("gigs/my_gig_activity")
    Call<POSTMyActivity> getMyActivity(@Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/buyer_cancel")
    Call<POSTCancelGigs> purchaseCancelGigs(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/leave_feedback")
    Call<POSTLeaveFeedback> postLeaveFeedback(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/seefeedback")
    Call<POSTPurchaseSeeFedBck> postpurchsseFedBck(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/accept_buyer_request")
    Call<POSTAcceptBuyRequest> postacceptbuyrequest(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/sale_order_status")
    Call<POSTAcceptBuyRequest> postorderStatus(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/withdram_payment_request")
    Call<POSTAcceptBuyRequest> postWithdrawRequest(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);


    @FormUrlEncoded
    @POST("gigs/chat_details")
    Call<POSTChatHistory> getChatHistory(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/buyer_chat")
    Call<POSTAcceptBuyRequest> postMessage(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/user_chat")
    Call<POSTUserChat> postUserChat(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/save_device_id")
    Call<POSTAcceptBuyRequest> postPushDetails(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/buy_now")
    Call<POSTBuyNow> postBuyingGigsdetails(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/paypal_success")
    Call<POSTPaymentSuccess> postPaymentSuccess(@FieldMap HashMap<String, String> updateProfile, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/stripe_account_details")
    Call<POSTPaymentSuccess> postStripeCancel(@FieldMap HashMap<String, String> cancelPayment, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/stripe_account_details")
    Call<POSTPaymentSuccess> postRegisterStripeDetails(@FieldMap HashMap<String, String> cancelPayment, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/change_gigs_status")
    Call<POSTPurchaseCompletedAcceptStatus> postPurchaseCompletedAcceptStatus(@FieldMap HashMap<String, String> cancelPayment, @Header("token") String token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/accept_seller_decline_request")
    Call<POSTAcceptSellerDeclineRequest> postAcceptSellerDeclineRequest(@FieldMap HashMap<String, String> cancelPayment, @Header("token") String token, @Header("language") String Language);

    @GET("gigs/price_details")
    Call<GETPriceDetails> getPriceDetails(@Header("token") String token, @Header("language") String Language);

    @GET("user/logout")
    Call<GETLogoutResponse> getLogoutResponse(@Header("token") String Token, @Header("language") String Language);

    @GET("gigs/language_list")
    Call<GETLanguageList> getLanguageList();

    @FormUrlEncoded
    @POST("gigs/rejected_orders")
    Call<POSTRejectResponse> postRejectResponse(@FieldMap HashMap<String, String> rejectOrder, @Header("token") String token, @Header("language") String Language);

    @GET("gigs/stripe_private_key")
    Call<GETStripeConfig> getStripeConfiDetails(@Header("token") String Token, @Header("language") String Language);

    @FormUrlEncoded
    @POST("gigs/language")
    Call<POSTLanguageModel> languageData(@Field("language") String language);

    @FormUrlEncoded
    @POST("user/check_sell_Service")
    Call<POSTSubscriptionModel> postSubscription(@Header("token") String token, @Field("timezone") String timezone);

    @FormUrlEncoded
    @POST("gigs/stripe_subscription_success")
    Call<POSTSubscriptionSuccess> postSubscriptionPayment(@FieldMap HashMap<String, String> subscriptionData, @Header("token") String token, @Header("language") String Language);
}