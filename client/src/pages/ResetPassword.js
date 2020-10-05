import React, {useRef, useState} from 'react';
import {useParams} from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "../services/auth.service";
import CheckValidations from "../components/CheckValidations";

const ResetPassword = ()=> {
    //variables
    const form = useRef();
    const checkBtn = useRef();

    const [password,setPassword] = useState("");
    const [password2,setPassword2] = useState("");
    const [success,setSuccess] = useState(false);
    const [message,setMessage] = useState("");

    let {token} = useParams();

    //these set the password values to what was entered
    const onChangePassword = (e)=> {
        const password = e.target.value;
        setPassword(password);
    };

    const onCheckPassword = (e)=> {
        const password = e.target.value;
        setPassword2(password);
    };

    const handleReset = (e)=> {
        e.preventDefault();

        setMessage("");
        setSuccess(false);

        form.current.validateAll();

        if(checkBtn.current.context._errors.length === 0){
            //if there are no problems, reset password
            AuthService.reset(token,password).then(
                (response)=>{
                    setMessage(response.data);
                    setSuccess(true);
                },
                //if there are problems, set error message
                (error)=> {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setMessage(resMessage);
                    setSuccess(false);
                }
            );
        }
    };

    return(
        <div className="content">
            <div className="box box-container">
                {/*form which users access by email to reset password*/}
                <Form onSubmit={handleReset} ref={form}>
                    {!success && (
                        <div>
                            {/*users enter new password here*/}
                            <div className="form-group">
                                <h2>Reset</h2>
                                <label htmlFor="password">Password</label>
                                <Input
                                    type="password"
                                    className="form-control"
                                    name="password"
                                    value={password}
                                    onChange={onChangePassword}
                                    validations={[CheckValidations.required,CheckValidations.vpassword]}
                                />
                            </div>

                            {/*user enters password again*/}
                            <div className="form-group">
                                <label htmlFor="password">Repeat password</label>
                                <Input
                                    type="password"
                                    className="form-control"
                                    name="password"
                                    value={password2}
                                    onChange={onCheckPassword}
                                    validations={[CheckValidations.required]}
                                />
                            </div>

                            {/*if both passwords match, show sign up button*/}
                            {(password === password2 && password !== "")&& (
                                <div className="form-group">
                                    <button className="btn btn-primary btn-block">Reset</button>
                                </div>
                            )}
                        </div>
                    )}

                    {/*display message*/}
                    {message && (
                        <div className="form-group">
                            <div
                                className={ success ? "alert alert-succes" : "alert alert-danger"}
                                role="alert"
                            >
                                {message}
                            </div>
                        </div>
                    )}
                    <CheckButton style={{ display:"none"}} ref={checkBtn}/>
                </Form>
            </div>
        </div>
    );
};

export default ResetPassword;