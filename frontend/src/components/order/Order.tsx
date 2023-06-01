import React from 'react';
import {basketProduct} from "../../model/basketProductType";
import OrderProducts from "./OrderProducts";
import {order} from "../../model/orderType";

function Order(props: { id: string; dateTimeOfOrder: string; orderedProducts: basketProduct[]; totalCost: number, completed: boolean; setOrders: (value: (((prevState: order[]) => order[]) | order[])) => void }) {

    return (
        <div className="order">
            <div className="order-id">{props.id}</div>
            <div className="order-date-time">{props.dateTimeOfOrder}</div>
            <div className="order-total-cost">{props.totalCost}</div>
            <div className="order-completed">{props.completed ? "Completed" : "Not Completed"}</div>
            <div className="order-products">
                <OrderProducts orderProducts={props.orderedProducts}/>
            </div>
            {/*TODO - Task 13: add a complete order button here*/}
        </div>
    );
}

export default Order;