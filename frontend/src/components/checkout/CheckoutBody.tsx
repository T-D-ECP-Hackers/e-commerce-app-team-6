import React from "react";
import {basket} from "../../model/basketType";
import BasketProducts from "./BasketProducts";

function CheckoutBody(props: { basket: basket }) {

    return (
        <div className="checkout">
            <div className="checkout-title">
                <div>ID</div>
                <div>Name</div>
                <div>Description</div>
                <div>Price</div>
                <div>Quantity</div>
            </div>
            <div className="checkout-container">
                <BasketProducts basketProducts={props.basket.basketProducts}/>
            </div>
        </div>
    );
}

export default CheckoutBody;