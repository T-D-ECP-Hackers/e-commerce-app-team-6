import {NavigateFunction} from "react-router-dom";

export function goToHomePage(navigate: NavigateFunction) {
    navigate("/");
}

export function goToProductsPage(navigate: NavigateFunction) {
    navigate("/products");
}

export function goToCheckout(navigate: NavigateFunction) {
    navigate("/checkout");
}

export function goToOrdersPage(navigate: NavigateFunction) {
    navigate("/orders");
}