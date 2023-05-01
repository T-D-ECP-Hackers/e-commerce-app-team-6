import React from 'react';
import ProductBody from '../product/ProductBody';
import {getUser} from "../../functions/authentication";
import LoginPage from "./LoginPage";

function ProductsPage() {

    return (
        getUser() !== null ? (
            <div className="products-page">
                <ProductBody/>
            </div>
        ) : LoginPage()
    );
}

export default ProductsPage;