import React, {useContext} from 'react';
import {Link, NavigateFunction, useNavigate} from "react-router-dom";
import {getUser, logout} from "../../functions/authentication";
import CheckoutOption from "../checkout/CheckoutOption";
import {fetchBasket} from "../../api/fetchBasket";
import BasketContext from "../../context/BasketContext";

function NavigationBar() {

    const navigate = useNavigate();
    const basket = useContext(BasketContext);

    function logoutAndClearBasket(navigate: NavigateFunction) {

        logout(basket.setCurrentBasket, navigate);
        fetchBasket(basket.setCurrentBasket);

    }

    function getLoginOptions() {
        return getUser() !== null ?
            <>
                <button className="logout" onClick={() => logoutAndClearBasket(navigate)}>Logout</button>
                <label className="user-name">{getUser()}</label>
            </> :
            <Link to={`login`}>Login</Link>;
    }

    return (
        <div className="navigation-bar">
            <Link to={`products`}>Products</Link>
            {getLoginOptions()}
            <CheckoutOption/>
        </div>
    );
}

export default NavigationBar;