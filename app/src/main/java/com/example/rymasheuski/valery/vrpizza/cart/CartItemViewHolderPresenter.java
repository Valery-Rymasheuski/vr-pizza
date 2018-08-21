package com.example.rymasheuski.valery.vrpizza.cart;

import com.example.rymasheuski.valery.vrpizza.base.recycler.BaseMvpViewHolderPresenter;
import com.example.rymasheuski.valery.vrpizza.model.CartItem;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;

/**
 * Created by valery on 21.8.18.
 */

public class CartItemViewHolderPresenter extends BaseMvpViewHolderPresenter<CartItem, CartItemViewHolderContract.MvpView>
    implements CartItemViewHolderContract.MvpPresenter{


    @Override
    public void onViewIsReady() {
        CartItem model = getModel();

        getMvpView().setProductInfo(model.getProduct().getName(),
                model.getProduct().getDescription(),
                model.getOptionsLabel());

        getMvpView().setWeightAndPrice(model.getWeightWithOptions(), model.getPriceWithOptions());

        getMvpView().setQuantity(model.getQuantity());
    }

    @Override
    public void onQuantityChanged(int quantity) {
        CartHelper.getCart().addProduct(getModel().getProduct(), quantity);
        getMvpView().callQuantityListener(getModel().getProduct(), quantity);
    }


}
