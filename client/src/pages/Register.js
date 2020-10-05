import React, { useState, useRef } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "../services/auth.service";
import CheckValidations from "../components/CheckValidations";

const Register = ()=> {
    //variables
    const form = useRef();
    const checkBtn = useRef();

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password,setPassword] = useState("");
    const [password2,setPassword2] = useState("");
    const [success,setSuccess] = useState(false);
    const [message,setMessage] = useState("");
    const [category, setCategory] = useState("artist");

    //onChange functions set variables to what was entered
    const onChangeUsername = (e)=> {
        const username = e.target.value;
        setUsername(username);
    };

    const onChangeEmail = (e)=> {
        const email = e.target.value;
        setEmail(email);
    };

    const onChangePassword = (e)=> {
        const password = e.target.value;
        setPassword(password);
    };

    //sets the second password to what was entered
    const onCheckPassword = (e)=> {
        const password = e.target.value;
        setPassword2(password);
    };

    const handleRegister = (e)=> {
        e.preventDefault();

        setMessage("");
        setSuccess(false);

        form.current.validateAll();

        if(checkBtn.current.context._errors.length === 0){
            //if there are no problems, register new user
            AuthService.register(username, email, password, category).then(
                (response)=> {
                    setMessage(response.data.message);
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
                {/*form for registering new users*/}
                <Form onSubmit={handleRegister} ref={form}>
                    {!success && (
                        <div>
                            {/*box for entering username*/}
                            <div className="form-group">
                                <h2>Sign up</h2>
                                <label htmlFor="username">Username</label>
                                <Input
                                    type="text"
                                    className="form-control"
                                    name="username"
                                    value={username}
                                    onChange={onChangeUsername}
                                    validations={[CheckValidations.required,CheckValidations.vusername]}
                                />
                            </div>

                            {/*box for entering email*/}
                            <div className="form-group">
                                <label htmlFor="email">Email</label>
                                <Input
                                    type="text"
                                    className="form-control"
                                    name="email"
                                    value={email}
                                    onChange={onChangeEmail}
                                    validations={[CheckValidations.required,CheckValidations.validEmail]}
                                />
                            </div>

                            {/*add password here*/}
                            <div className="form-group">
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

                            {/*repeat password. password2 must match password*/}
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

                            {/*if the passwords match, show the sign up button*/}
                            {(password === password2 && password !== "")&& ( //only show sign up button if passwords match
                                <div className="form-group">
                                    <button className="btn btn-primary btn-block">Sign Up</button>
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

export default Register;