import React, {createContext} from "react";
import {basket} from "../model/productType";

type BasketContextType = {
    currentBasket: basket | null,
    setCurrentBasket: React.Dispatch<React.SetStateAction<basket | null>>
}

const iBasketContextState = {
    currentBasket: null,
    setCurrentBasket: () => {}
}

const BasketContext = createContext<BasketContextType>(iBasketContextState)

export default BasketContext