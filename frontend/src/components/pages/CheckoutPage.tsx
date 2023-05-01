import React, {useContext, useEffect} from "react";
import CheckoutBody from "../checkout/CheckoutBody";
import {useNavigate} from "react-router-dom";
import {getUser} from "../../functions/authentication";
import LoginPage from "./LoginPage";
import BasketContext from "../../context/BasketContext";
import {clearBasket, fetchBasket} from "../../api/fetchBasket";

function CheckoutPage() {

    const basket = useContext(BasketContext);
    const navigate = useNavigate();

    useEffect(() => {
        fetchBasket(basket.setCurrentBasket);
    }, []);

    function getEmptyBasket() {
        let username = getUser();
        if (username === null) {
            username = "";
        }
        return {id: 0, username: username, totalProducts: 0, basketProducts: []};
    }

    return (
        getUser() !== null ? (
            <div className="checkout-page">
                <div className="header-container">
                    <h1 onClick={() => clearBasket(basket.setCurrentBasket, navigate)}>
                        Checkout {basket.currentBasket === null ? 0 : basket.currentBasket.totalProducts}
                    </h1>
                </div>
                <CheckoutBody basket={basket.currentBasket === null ? getEmptyBasket() : basket.currentBasket}/>
            </div>
        ) : LoginPage()
    );
}

export default CheckoutPage;