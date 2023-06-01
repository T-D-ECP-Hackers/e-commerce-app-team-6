import {product} from "../model/productType";
import axios from "axios";
import {productsUrl} from "./apiConstants";

export function fetchProducts(setProducts: (value: (((prevState: product[]) => product[]) | product[])) => void) {

    axios(productsUrl).then(response => {
        setProducts(response.data)
    }).catch(error => {
        console.error(error.response.data.message)
    })
}