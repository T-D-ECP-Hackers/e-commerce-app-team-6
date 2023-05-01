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
    }, []);

    return (
        <div className="products-body">
            <Products products={products}/>
        </div>
    );
}

export default ProductBody;