import React from 'react';
import {Link} from "react-router-dom";
import CheckoutOption from "../checkout/CheckoutOption";

function NavigationBar() {

    return (
        <div className="navigation-bar">
            <Link to={`products`}>Products</Link>
            <Link to={`orders`}>Orders</Link>
            <CheckoutOption/>
        </div>
    );
}

export default NavigationBar;