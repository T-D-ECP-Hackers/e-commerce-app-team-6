import {NavigateFunction} from "react-router-dom";
import {goToHomePage} from "./navigation";
import {basket} from "../model/productType";
import React from "react";

export function login(user: string) {
    localStorage.setItem('user', user);
}

export function logout(setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>, navigate: NavigateFunction) {
    setCurrentBasket(null);
    localStorage.removeItem('user');
    goToHomePage(navigate);
}

export function getUser() {
    return localStorage.getItem('user')
}