import React from 'react';

function CheckoutProduct(props: { id: number; name: string; description: string; price: number; count: number; }) {

    const {id, name, description, price, count} = props
    return (
        <div className="checkout-product">
            <div className="product-id">{id}</div>
            <div className="product-name">{name}</div>
            <div className="product-description">{description}</div>
            <div className="product-price">£{price}</div>
            <div className="product-amount">{count}</div>
        </div>
    );
}

export default CheckoutProduct;