import {basket} from "../model/basketType";
import axios from "axios";
import {ordersURL} from "./apiConstants";
import {order} from "../model/orderType";

export function generateNewOrder(currentBasket: basket | null, totalCost: number) {

    axios.post(ordersURL, {
        basket: currentBasket,
        totalCost: totalCost,
    }, {}).then(response => {
        if(response.status === 200) {
            console.log("Generating order - order number: " + response.data);
        }
    }).catch(error => {
        console.log("Error fetching data: " + error)
    })
}

// TODO - Task 10: implement fetch orders
export function fetchOrders(setOrders: (value: (((prevState: order[]) => order[]) | order[])) => void) {
}

// TODO - Task 13: Implement a complete order axios fetch method to call the backend api
export function completeOrder(orderId: any, setOrders: (value: (((prevState: order[]) => order[]) | order[])) => void) {
}