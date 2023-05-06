import React from "react";
import {basket} from "../model/productType";
import {NavigateFunction} from "react-router-dom";
import axios from "axios";
import {basketUrl, checkoutUrl} from "./apiConstants";
import {getUser} from "../functions/authentication";
import {goToProductsPage} from "../functions/navigation";

export function fetchBasket(setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>) {

    let username = getUser();
    if (username !== null) {
        axios(basketUrl + `/${username}`).then(response => {
            setCurrentBasket(response.data)
        }).catch(error => {
            console.error(error.response.data.message)
        })
    }
}

export function addProductToBasket(productId: any,
                                   setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>) {

    let username = getUser();

    axios.post(basketUrl + `/${username}`, null, {
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

    let username = getUser();

    axios.delete(basketUrl + `/${username}`, {
        params: {
            productId: id
        }
    }).then(response => {
        fetchBasket(setCurrentBasket);
    }).catch(error => {
        console.log("Error fetching data: " + error)
    })
}

export function clearBasket(setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>,
                            navigate: NavigateFunction) {
    let username = getUser();
    axios.post(checkoutUrl + `/${username}`, null, {}).then(response => {
        fetchBasket(setCurrentBasket);
    }).catch(error => {
        console.log("Error fetching data: " + error)
    })
    goToProductsPage(navigate);
}