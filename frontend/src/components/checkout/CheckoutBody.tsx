import React from "react";
import CheckoutProduct from "./CheckoutProduct";
import {basket} from "../../model/productType";

function CheckoutBody(props: { basket: basket}) {

    return (
        <div className="checkout">
            <div className="checkout-title">
                <div>ID</div>
                <div>Name</div>
                <div>Description</div>
                <div>Price</div>
                <div>Amount</div>
            </div>
            <div className="checkout-container">
                {props.basket.basketProducts?.map((basketProduct, index) => {
                    return (<CheckoutProduct key={index}
                                             id={basketProduct.product.id}
                                             name={basketProduct.product.name}
                                             description={basketProduct.product.description}
                                             price={basketProduct.product.price}
                                             count={basketProduct.count}/>)
                })}
            </div>
        </div>
    );
}

export default CheckoutBody;