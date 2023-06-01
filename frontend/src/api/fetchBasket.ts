import React from "react";
import {basket} from "../model/basketType";
import {NavigateFunction} from "react-router-dom";
import axios from "axios";
import {basketUrl, checkoutUrl} from "./apiConstants";
import {goToProductsPage} from "../functions/navigation";

export function fetchBasket(setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>) {

    axios(basketUrl).then(response => {
        setCurrentBasket(response.data)
    }).catch(error => {
        console.error(error.response.data.message)
    })
}

export function addProductToBasket(productId: any,
                                   setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>) {

    axios.post(basketUrl, null, {
        params: {
            productId: productId
        }
    }).then(response => {
        fetchBasket(setCurrentBasket);
    }).catch(error => {
        console.log("Error fetching data: " + error)
    })

}

export function removeProductFromBasket(id: number,
                                        setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>) {

    axios.delete(basketUrl, {
        params: {
            productId: id
        }
    }).then(response => {
        fetchBasket(setCurrentBasket);
    }).catch(error => {
        console.log("Error fetching data: " + error)
    })
}

export function checkout(setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>,
                         navigate: NavigateFunction) {
    axios.post(checkoutUrl, null, {}).then(response => {
        fetchBasket(setCurrentBasket);
    }).catch(error => {
        console.log("Error fetching data: " + error)
    })
    goToProductsPage(navigate);
}