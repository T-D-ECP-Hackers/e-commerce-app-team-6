import {loginError} from "../model/loginErrorType";
import axios, {AxiosError} from "axios";
import {registerUrl} from "./apiConstants";

export async function signUpUser(userName: any,
                                 passWord: any,
                                 email: any,
                                 setErrorMessages: (value: (((prevState: loginError) => loginError) | loginError)) => void) {

    try {
        let response = await axios.post(registerUrl, {
            username: userName,
            password: passWord,
            email: email
        }, {})
        return response.data
    } catch (error: any) {
        if (error.code === AxiosError.ERR_NETWORK) {
            setErrorMessages({message: error.message})
        } else {
            setErrorMessages({message: error.response.data.message})
        }
    }
}