import React, {useState} from 'react';
import '../../style/App.scss';
import {Outlet, useLocation} from "react-router-dom";
import Header from "./Header";
import {basket} from "../../model/basketType";
import BasketContext from "../../context/BasketContext";
import RootPage from "../pages/RootPage";

function App() {

    const location = useLocation();
    const [currentBasket, setCurrentBasket] = useState<basket | null>(null)

    return (
        <BasketContext.Provider value={{currentBasket, setCurrentBasket}}>
            <div className="app">
                <Header/>
                {location.pathname === "/" && <RootPage/>}
                <Outlet/>
            </div>
        </BasketContext.Provider>
    );
}

export default App;