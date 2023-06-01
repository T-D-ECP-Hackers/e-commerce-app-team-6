import React, {useContext} from "react";
import {checkout} from "../../api/fetchBasket";
import {useNavigate} from "react-router-dom";
import BasketContext from "../../context/BasketContext";
import {getTotalCostOfProducts} from "../../functions/getTotalCostOfProducts";
import {generateNewOrder} from "../../api/fetchOrders";

function CheckoutSummary({setShowCheckoutSummary}: { setShowCheckoutSummary: React.Dispatch<React.SetStateAction<boolean>> }) {

    const basket = useContext(BasketContext);
    const navigate = useNavigate();

    let totalCostOfItems = getTotalCostOfProducts(basket.currentBasket);
    let shippingCost = getShippingCost(totalCostOfItems)


    // TODO - Task 7: calculate the shipping costs
    // For every £1 worth of items, shipping costs 10p
    // If the total cost of items was £10, the shipping would be £1
    // Also if the total cost of items is over or equal to £50, the shipping costs are free or worth £0
    function getShippingCost(totalCostOfItems: number) {

        let shippingCost = 0.0000;
        return roundToTwoDecimalPlaces(shippingCost);
    }

    // TODO - Task 7: calculate the total cost of items and shipping
    function getTotalCost(totalCostOfItems: number, shippingCost: number) {

        let totalCost = 0.0000;
        return roundToTwoDecimalPlaces(totalCost);
    }

    // This function will round any number to two digits
    // If you have the number "0.01234" you will get "0.01"
    function roundToTwoDecimalPlaces(num: number) {
        return +(Math.round(Number(num + "e+2")) + "e-2");
    }

    function checkoutBasket() {
        generateNewOrder(basket.currentBasket, getTotalCost(totalCostOfItems, shippingCost));
        checkout(basket.setCurrentBasket, navigate);
    }

    return (
        <div className="checkout-summary-container">
            <div className="checkout-summary">
                <label>Subtotal - £ {totalCostOfItems}</label>
                <label>Shipping cost - £ {shippingCost}</label>
                <label>Total cost - £ {getTotalCost(totalCostOfItems, shippingCost)}</label>
                <div className="checkout-summary-buttons">
                    <div className="checkout-summary-button" onClick={() => setShowCheckoutSummary(false)}>Go Back</div>
                    <div className="checkout-summary-button" onClick={() => checkoutBasket()}>Complete Checkout</div>
                </div>

            </div>
        </div>
    )
}

export default CheckoutSummary;