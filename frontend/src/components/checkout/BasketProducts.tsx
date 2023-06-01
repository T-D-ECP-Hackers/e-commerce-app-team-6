import React from 'react';
import BasketProduct from "./BasketProduct";
import {basketProduct} from "../../model/basketProductType";

function BasketProducts(props: { basketProducts: basketProduct[] }) {
    return <>
        {props.basketProducts?.map((basketProduct, index) => {
            return (<BasketProduct key={index}
                                   id={basketProduct.product.id}
                                   name={basketProduct.product.name}
                                   description={basketProduct.product.description}
                                   price={basketProduct.product.price}
                                   quantity={basketProduct.quantity}/>)
        })}
    </>;
}

export default BasketProducts;