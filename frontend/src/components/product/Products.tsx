import React from 'react';
import Product from "./Product";
import {product} from "../../model/productType";

function Products(props: { products: product[] }) {
    return (
        <div className="products">
            <div className="products-title">
                <div>ID</div>
                <div>Name</div>
                <div>Description</div>
                <div>Price</div>
            </div>
            <div className="products-container">
                {props.products?.map((product) => {
                    return (<Product key={product.id} id={product.id} name={product.name} description={product.description} price={product.price}/>)
                })}
            </div>

        </div>
    );
}

export default Products;