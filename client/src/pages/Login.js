import React, { useState, useRef } from "react";
import {useHistory} from "react-router-dom";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "../services/auth.service";
import{ Link } from 'react-router-dom';
import CheckValidations from "../components/CheckValidations";

const Login = () => {
    //variables
    const form = useRef();
    const checkBtn = useRef();
    const history = useHistory();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    //onChange functions set vars to what was typed
    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    };

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };

    const handleLogin = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);

        form.current.validateAll();

        if (checkBtn.current.context._errors.length === 0) {
            AuthService.login(username, password).then(
                () => {
                    //the user is now logged in
                    const user = AuthService.getCurrentUser();
                    history.push("/user");
                    window.location.reload();
                    //go to boardUser
                },
                //if there is an error, set error message
                (error) => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setLoading(false);
                    setMessage(resMessage);
                }
            );
        } else {
            setLoading(false);
        }
    };

    return (
        <div className="content">
            <div className="box box-container">
                {/*Login form*/}
                <Form onSubmit={handleLogin} ref={form}>
                    <div className="form-group">
                        <h2>Login</h2>
                        <label htmlFor="username">Username</label>
                        {/*input field for username*/}
                        <Input
                            type="text"
                            className="form-control"
                            name="username"
                            value={username}
                            onChange={onChangeUsername}
                            validations={[CheckValidations.required]}
                        />
                    </div>
                    {/*input field for password*/}

                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <Input
                            type="password"
                            className="form-control"
                            name="password"
                            value={password}
                            onChange={onChangePassword}
                            validations={[CheckValidations.required]}
                        />
                        <ul>
                            {/*if user forgot their password, they can click this link to go to the forgotPassword page*/}
                            <li><Link to="/forgot-password">I forgot my password</Link></li>
                        </ul>
                    </div>

                    {/*Login button*/}
                    <div className="form-group">
                        <button className="btn btn-primary btn-block" disabled={loading}>
                            {loading && (
                                <span className="spinner-border spinner-border-sm"/>
                            )}
                            <span>Login</span>
                        </button>
                    </div>

                    {/*display message*/}
                    {message && (
                        <div className="form-group">
                            <div className="alert alert-danger" role="alert">
                                {message}
                            </div>
                        </div>
                    )}
                    <CheckButton style={{ display: "none" }} ref={checkBtn} />
                </Form>
            </div>
        </div>
    );
};

export default Login;