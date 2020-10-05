import React, { useState, useRef } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import axios from "axios";
import CheckValidations from "../components/CheckValidations";

const API_URL = "http://localhost:8090/api/auth/";

// POST request to send an email
const forgotPassword = (email, setMessage)=>{
    setMessage("Loading...");
    return axios
        .post(
            API_URL + "password", null, {params:{email}});
};

const ForgotPassword = () => {
    //variables
    const form = useRef();
    const checkBtn = useRef();

    const [email, setEmail] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    //sets email to what was just entered
    const onChangeEmail = (e) => {
        const email = e.target.value;
        setEmail(email);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);

        form.current.validateAll();

        //if there are no problems, send email
        if (checkBtn.current.context._errors.length === 0) {
            forgotPassword(email, setMessage).then(
                (response) => {
                    if (response.data){
                        JSON.stringify(response.data);
                    }
                    setMessage(response.data);
                    return response.data;
                })
        } else {
            setLoading(false);
        }
    };

    return (
        <div className="content">
            <div className="box box-container">
                {/*small form where users can enter their email so they can reset their password*/}
                <Form onSubmit={handleSubmit} ref={form}>
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

                    <div className="form-group">
                        <button className="btn btn-primary btn-block" disabled={loading}>
                            {loading && (
                                <span className="spinner-border spinner-border-sm"></span>
                            )}
                            <span>Submit</span>
                        </button>
                    </div>

                    {/*display the message*/}
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
export default ForgotPassword;