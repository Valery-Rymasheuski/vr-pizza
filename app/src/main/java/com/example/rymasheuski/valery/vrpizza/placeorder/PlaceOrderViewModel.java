package com.example.rymasheuski.valery.vrpizza.placeorder;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.SingleLiveEvent;
import com.example.rymasheuski.valery.vrpizza.model.Order;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.Validator;

/**
 * Created by valery on 22.8.18.
 */

public class PlaceOrderViewModel extends AndroidViewModel {

    private static final String TAG = PlaceOrderViewModel.class.getName();


    public final ObservableField<String> street = new ObservableField<>();
    public final ObservableField<String> streetError = new ObservableField<>();
    public final ObservableField<String> house = new ObservableField<>();
    public final ObservableField<String> houseError = new ObservableField<>();
    public final ObservableField<String> flat =new ObservableField<>();
    public final ObservableField<String> flatError = new ObservableField<>();
    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> phoneError = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> emailError = new ObservableField<>();
    public final ObservableField<String> deliveryTime = new ObservableField<>();
    public final ObservableField<String> deliveryTimeError = new ObservableField<>();
    public final ObservableField<String> coupon = new ObservableField<>();
    public final ObservableField<String> couponError = new ObservableField<>();
    public final ObservableField<String> comment = new ObservableField<>();
    public final ObservableField<String> commentError = new ObservableField<>();
    public final ObservableField<Integer> cityId = new ObservableField<>();
    public final ObservableField<Integer> paymentTypeId = new ObservableField<>();

    private final SingleLiveEvent<Void> mOpenResultEvent = new SingleLiveEvent<>();


    public PlaceOrderViewModel(@NonNull Application application) {
        super(application);
    }


    public SingleLiveEvent<Void> getOpenResultEvent() {
        return mOpenResultEvent;
    }

    public void send(){
        Order order = getFormData();
        Log.d(TAG, "Order = " + order);
        if(validate(order)){
            sendOrderToServer(order);
        }
    }


    private void sendOrderToServer(Order order){
        order.setCartItems(CartHelper.getCart().getCartItems());
        CartHelper.getCart().clear();


        mOpenResultEvent.call();
    }


    private Order getFormData() {
        Order order = new Order();
        order.setStreet(street.get());
        order.setHouse(house.get());
        order.setFlat(flat.get());
        order.setPhone(phone.get());
        order.setEmail(email.get());
        order.setDeliveryTime(deliveryTime.get());
        order.setCoupon(coupon.get());
        order.setComment(comment.get());

        order.setCityId(cityId.get());
        order.setPaymentTypeId(paymentTypeId.get());

        return order;
    }


    private boolean validate(Order order) {

        String emptyErrorMsg = getApplication().getApplicationContext()
                .getString(R.string.error_is_empty);

        Validator validator = Validator.ValidatorBuilder.getInstance()
                .addField(order.getStreet(), streetError, Validator.Rule.NOT_EMPTY)
                .addField(order.getHouse(), houseError, Validator.Rule.NOT_EMPTY)
                .addField(order.getFlat(), flatError, Validator.Rule.NOT_EMPTY)
                .addField(order.getPhone(), phoneError, Validator.Rule.NOT_EMPTY)
                .addField(order.getEmail(), emailError, Validator.Rule.NOT_EMPTY)
                .addField(order.getDeliveryTime(), deliveryTimeError, Validator.Rule.NOT_EMPTY)
                .setEmptyErrorMessage(emptyErrorMsg)
                .build();

        return validator.validate();

    }
}
