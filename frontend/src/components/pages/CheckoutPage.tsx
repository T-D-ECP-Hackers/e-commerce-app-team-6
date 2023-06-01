import React, {useContext, useEffect, useState} from "react";
import CheckoutBody from "../checkout/CheckoutBody";
import BasketContext from "../../context/BasketContext";
import {fetchBasket} from "../../api/fetchBasket";
import {basket} from "../../model/basketType";
import CheckoutSummary from "../checkout/CheckoutSummary";
import {getTotalCostOfProducts} from "../../functions/getTotalCostOfProducts";

function CheckoutPage() {

    const basket = useContext(BasketContext);
    const [showCheckoutSummary, setShowCheckoutSummary] = useState(false);

    useEffect(() => {
        fetchBasket(basket.setCurrentBasket);
    }, [basket.setCurrentBasket]);

    // TODO - Task 4: someone made a mistake here, we only want to return this if our basket is null,
    //  else return our current basket
    function getBasket(currentBasket: basket | null) {

        return {id: 0, totalProducts: 0, basketProducts: []};
    }

    return (
        <div className={showCheckoutSummary ? "checkout-page-summary" : "checkout-page"}>
            {showCheckoutSummary ? <CheckoutSummary setShowCheckoutSummary={setShowCheckoutSummary}/> :
                <>
                    <div className="header-container">
                        <div className="checkout-button" onClick={() => setShowCheckoutSummary(true)}>
                            <h1>Total Cost of Products - £ {getTotalCostOfProducts(basket.currentBasket)}</h1>
                            <h1>Checkout
                                - {basket.currentBasket == null ? 0 : basket.currentBasket.totalProducts} Products</h1>
                        </div>
                    </div>
                    <CheckoutBody basket={getBasket(basket.currentBasket)}/>
                </>
            }
        </div>
    );
}

export default CheckoutPage;