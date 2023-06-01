import React from 'react';

function BasketProduct(props: { id: number; name: string; description: string; price: number; quantity: number; }) {

    const {id, name, description, price, quantity} = props
    return (
        <div className="checkout-product">
            <div className="product-id">{id}</div>
            <div className="product-name">{name}</div>
            <div className="product-description">{description}</div>
            <div className="product-price">£{price}</div>
            <div className="product-quantity">{quantity}</div>
        </div>
    );
}

export default BasketProduct;