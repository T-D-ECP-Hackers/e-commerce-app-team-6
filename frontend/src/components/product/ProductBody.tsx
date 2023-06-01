import React, {useContext, useEffect, useState} from 'react';
import Products from "./Products";
import {fetchProducts} from "../../api/fetchProducts";
import {product} from "../../model/productType";
import {fetchBasket} from "../../api/fetchBasket";
import BasketContext from "../../context/BasketContext";

function ProductBody() {

    const [products, setProducts] = useState<product[]>([]);
    const basket = useContext(BasketContext);

    useEffect(() => {
        fetchBasket(basket.setCurrentBasket);
        fetchProducts(setProducts);
    }, [basket.setCurrentBasket]);

    return (
        <div className="products-body">
            <div className="products">
                <div className="products-title">
                    <div>ID</div>
                    <div>Name</div>
                    <div>Description</div>
                    <div>Price</div>
                </div>
                <Products products={products}/>
            </div>
        </div>

    );
}

export default ProductBody;