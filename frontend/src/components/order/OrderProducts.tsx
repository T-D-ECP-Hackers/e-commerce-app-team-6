import React from 'react';
import {basketProduct} from "../../model/basketProductType";
import OrderProduct from "./OrderProduct";

function OrderProducts(props: { orderProducts: basketProduct[] }) {
    return (
        <>
            <div className="order-products-title">
                <div>ID</div>
                <div>Name</div>
                <div>Price</div>
                <div>Quantity</div>
            </div>
            {props.orderProducts?.map((orderProduct, index) => {
                return (<OrderProduct key={index}
                                      id={orderProduct.product.id}
                                      name={orderProduct.product.name}
                                      price={orderProduct.product.price}
                                      count={orderProduct.quantity}/>)
            })}
        </>);
}

export default OrderProducts;