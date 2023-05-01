import React from "react";
import {loginError} from "../../model/loginErrorType";

function ErrorMessage(props: {errorMessages: loginError}) {
    return <div className="error">{props.errorMessages.message}</div>
}

export default ErrorMessage;