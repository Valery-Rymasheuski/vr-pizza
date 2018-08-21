package com.example.rymasheuski.valery.vrpizza.cart;


import com.example.rymasheuski.valery.vrpizza.base.BaseMvpPresenter;
import com.example.rymasheuski.valery.vrpizza.model.CartItem;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;

import java.util.List;

/**
 * Created by valery on 21.8.18.
 */

public class ShoppingCartPresenter extends BaseMvpPresenter<ShoppingCartContract.MvpView>
        implements ShoppingCartContract.MvpPresenter  {

    public ShoppingCartPresenter(ShoppingCartContract.MvpView mvpView) {
        super(mvpView);
    }

    @Override
    public void onViewIsReady() {
        updateOptionsInCart();
        setCartItems();
        calcAndShowTotal();
    }

    @Override
    public void onClearOrderClicked() {
        CartHelper.getCart().clear();
        setCartItems();

        calcAndShowTotal();
    }

    @Override
    public void onOrderClicked() {
        getMvpView().goToPlaceOrder();
    }


    @Override
    public void onChangeQuantity() {
        calcAndShowTotal();
    }

    private void setCartItems(){
        getMvpView().setCartItems(CartHelper.getCart().getCartItems());
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


    private void calcAndShowTotal(){
        List<CartItem> items = CartHelper.getCart().getCartItems();
        int total = 0;
        for(CartItem item : items){

            total += item.getQuantity() * item.getPriceWithOptions();

        }
        getMvpView().setTotal(total);
    }
}
