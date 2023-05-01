import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {goToProductsPage} from "../../functions/navigation";
import {login} from "../../functions/authentication";
import {loginError} from "../../model/loginErrorType";
import {signUpUser} from "../../api/signUpUser";
import ErrorMessage from "./ErrorMessage";


function SignUpForm() {

    const [errorMessages, setErrorMessages] = useState<loginError>({message: ""});
    const navigate = useNavigate();

    const handleSubmit = async (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        const {uname, pass, email} = document.forms[0];
        const userName = uname.value;
        const passWord = pass.value;
        const emailAddress = email.value
        let isUserAuthenticated = await signUpUser(userName, passWord, emailAddress, setErrorMessages);
        if (isUserAuthenticated) {
            console.log(`${userName} is signed up and authenticated`)
            login(userName)
            goToProductsPage(navigate);
        } else {
            console.log(`${userName} is not is signed up or authenticated`)
        }
    };

    const renderForm = (
        <div className="form">
            <form onSubmit={handleSubmit}>
                <div className="input-container">
                    <label>Username </label>
                    <input type="text" name="uname" required/>
                </div>
                <div className="input-container">
                    <label>Password </label>
                    <input type="password" name="pass" required/>
                </div>
                <div className="input-container">
                    <label>Email </label>
                    <input type="email" name="email" required/>
                </div>
                <ErrorMessage errorMessages={errorMessages}/>
                <div className="button-container">
                    <input type="submit" value="Sign Up"/>
                </div>
            </form>
        </div>
    );

    return (
        <div className="sign-up-form">
            <div className="title">Sign Up</div>
            {renderForm}
        </div>
    );
}

export default SignUpForm;