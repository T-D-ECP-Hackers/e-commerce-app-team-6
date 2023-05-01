import React, {useContext} from 'react';
import {FiShoppingCart} from 'react-icons/fi'
import {useNavigate} from "react-router-dom";
import {goToCheckout} from "../../functions/navigation";
import BasketContext from "../../context/BasketContext";

function CheckoutOption() {

    const basket = useContext(BasketContext);
    const navigate = useNavigate();

    return (
        <div className="basket">
            <FiShoppingCart onClick={() => goToCheckout(navigate)}/>
            <label
                className={"number-of-checkout-items"}>{basket.currentBasket === null ? 0 : basket.currentBasket.totalProducts}</label>
        </div>
    );
}

export default CheckoutOption;