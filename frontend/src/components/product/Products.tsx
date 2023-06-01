import React from 'react';
import Product from "./Product";
import {product} from "../../model/productType";

function Products(props: { products: product[] }) {
    return (
            <div className="products-container">
                {props.products?.map((product) => {
                    return (
                        <Product key={product.id} id={product.id} name={product.name} description={product.description}
                                 price={product.price}/>)
                })}
            </div>
    );
}

export default Products;