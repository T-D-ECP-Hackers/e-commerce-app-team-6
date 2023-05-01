import React from 'react';
import {useRouteError} from "react-router-dom";

export default function ErrorPage() {
    interface Choice {
        statusText: string;
        data: string;
    }

    const error = useRouteError() as Choice;

    console.error(error);

    return (
        <div id="error-page">
            <h1>Oops!</h1>
            <p>Sorry, an unexpected error has occurred.</p>
            <p>
                <i>{error.statusText}</i>
            </p>
            <p>
                <i>{error.data}</i>
            </p>
        </div>
    );
}