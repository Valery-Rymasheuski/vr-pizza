package com.example.rymasheuski.valery.vrpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rymasheuski.valery.vrpizza.component.SpinnerComponent;
import com.example.rymasheuski.valery.vrpizza.model.Order;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;
import com.example.rymasheuski.valery.vrpizza.util.Validator;

public class PlaceOrderActivity extends AppCompatActivity {

    private static final String TAG = PlaceOrderActivity.class.getName();

    private EditText mStreetEditText;
    private EditText mHouseEditText;
    private EditText mFlatEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mDeliveryTimeEditText;
    private EditText mCouponEditText;
    private EditText mCommentEditText;
    private SpinnerComponent mCitySpinner;
    private SpinnerComponent mPaymentSpinner;

    private Validator mValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);


        UiUtil.prepareToolbar(this, true, R.string.place_order_title);


        mCitySpinner = new SpinnerComponent(this, R.id.spinner_city, R.array.cities_array, R.array.city_id_array);
        mPaymentSpinner = new SpinnerComponent(this, R.id.spinner_payment_type, R.array.payment_type_array, R.array.payment_type_id_array);



        Button sendButton = findViewById(R.id.button_send_order);
        sendButton.setOnClickListener(v -> {
            if(validate()){
                sendOrder();
            }
        });


        mStreetEditText = findViewById(R.id.et_street);
        mHouseEditText = findViewById(R.id.et_house);
        mFlatEditText = findViewById(R.id.et_flat);
        mPhoneEditText = findViewById(R.id.et_phone);
        mEmailEditText = findViewById(R.id.et_email);
        mDeliveryTimeEditText = findViewById(R.id.et_delivery_time);
        mCouponEditText = findViewById(R.id.et_coupon);
        mCommentEditText = findViewById(R.id.et_comment);



        mValidator = Validator.ValidatorBuilder.getInstance(getApplicationContext())
                .addField(mStreetEditText, Validator.Rule.NOT_EMPTY)
                .addField(mHouseEditText, Validator.Rule.NOT_EMPTY)
                .addField(mFlatEditText, Validator.Rule.NOT_EMPTY)
                .addField(mPhoneEditText, Validator.Rule.NOT_EMPTY)
                .addField(mEmailEditText, Validator.Rule.NOT_EMPTY)
                .addField(mDeliveryTimeEditText, Validator.Rule.NOT_EMPTY)
                .build();
    }



    private boolean validate(){
        return mValidator.validate();
    }




    private void sendOrder(){
        Order order = new Order();
        order.setStreet(getValue(mStreetEditText));
        order.setHouse(getValue(mHouseEditText));
        order.setFlat(getValue(mFlatEditText));
        order.setPhone(getValue(mPhoneEditText));
        order.setEmail(getValue(mEmailEditText));
        order.setDeliveryTime(getValue(mDeliveryTimeEditText));
        order.setCoupon(getValue(mCouponEditText));
        order.setComment(getValue(mCommentEditText));

        order.setCityId(mCitySpinner.getSelectedItemId());
        order.setPaymentTypeId(mPaymentSpinner.getSelectedItemId());

        order.setCartItems(CartHelper.getCart().getCartItems());
        CartHelper.getCart().clear();

        Log.d(TAG, "Order = " + order);


        Intent intent = new Intent(this, OrderResultActivity.class);
        startActivity(intent);




    }


    private String getValue(EditText et){
        return String.valueOf(et.getText());
    }
}
