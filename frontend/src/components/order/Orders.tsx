import React from 'react';
import {order} from "../../model/orderType";

function Orders(props: { orders: order[], setOrders: (value: (((prevState: order[]) => order[]) | order[])) => void}) {
    return (
        <div className="orders-container">
        {/*TODO - Task 10: mapping of orders into order components*/}
        </div>
    );
}

export default Orders;