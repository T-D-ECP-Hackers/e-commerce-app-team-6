import React, {useContext, useEffect} from 'react';
import {fetchBasket} from "../../api/fetchBasket";
import BasketContext from "../../context/BasketContext";

function OrderBody() {

    const basket = useContext(BasketContext);

    useEffect(() => {
        fetchBasket(basket.setCurrentBasket);
    }, [basket.setCurrentBasket]);

    return (
        <div className="orders-body">
            <div className="orders">
                <div className="orders-title">
                    <div>ID</div>
                    <div>Date Time Of Order</div>
                    <div>Total Cost</div>
                    <div>Completed</div>
                    <div>Ordered Items</div>
                </div>
                {/*TODO - Task 10: Implement the Orders component here*/}
            </div>
        </div>

    );
}

export default OrderBody;