package com.example.rymasheuski.valery.vrpizza.cart;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;


import com.example.rymasheuski.valery.vrpizza.model.CartItem;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;
import com.example.rymasheuski.valery.vrpizza.util.Saleable;

import java.util.List;

/**
 * Created by valery on 23.8.18.
 */

public class ShoppingCartViewModel extends AndroidViewModel {

    public final ObservableList<CartItem> cartItems = new ObservableArrayList<>();
    public final ObservableField<Double> total = new ObservableField<>();


    public ShoppingCartViewModel(@NonNull Application application) {
        super(application);

        updateOptionsInCart();
        setCartItems();
        calcAndShowTotal();

    }


    public void onClearOrder() {
        CartHelper.getCart().clear();
        setCartItems();

        calcAndShowTotal();
    }


    public void onQuantityChanged(Saleable product, int quantity) {
        CartHelper.getCart().addProduct(product, quantity);
        calcAndShowTotal();
    }

    private void setTotal(int totalValue) {
        total.set(FormatUtil.formatPrice(totalValue));
    }

    private void calcAndShowTotal(){
        List<CartItem> items = CartHelper.getCart().getCartItems();
        int total = 0;
        for(CartItem item : items){

            total += item.getQuantity() * item.getPriceWithOptions();

        }
        setTotal(total);
    }

    private void setCartItems(){
        List<CartItem> newItems = CartHelper.getCart().getCartItems();
        if(newItems.isEmpty()){
            cartItems.clear();
        }else{
            cartItems.addAll(newItems);
        }
    }


    private void updateOptionsInCart(){
        List<CartItem> cartItems = CartHelper.getCart().getCartItems();
        for(CartItem cartItem : cartItems){
            Long productId = cartItem.getProduct().getId();

            FoodOption sizeOption = FoodOptionsHelper.sizeOptionHelper.getOption(productId);
            FoodOption pizzaOption = FoodOptionsHelper.pizzaOptionHelper.getOption(productId);

            cartItem.clearSelectedOptions();
            cartItem.addSelectedOption(sizeOption);
            cartItem.addSelectedOption(pizzaOption);


        }
    }
}
