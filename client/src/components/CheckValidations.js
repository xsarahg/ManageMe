import React from 'react';
import {isEmail} from "validator";

// method for required fields
const required = (value) => {
    if (!value) {
        return(
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

// check if email input is valid
const validEmail = (value) => {
    // validate input to be email
    if (!isEmail(value)) {
        return (
            // if not correct, return alert message
            <div className="alert-danger">
                This is not a valid email.
            </div>
        );
    }
};

// check if username input is valid
const vusername = value => {
    // check if the input length is bigger than 3 and smaller than 20
    if (value.length < 3 || value.length > 20) {
        return (
            // if not return alert message
            <div className="alert-danger">
                Must be 3 - 20 characters
            </div>
        );
    }
};

// check if password input is valid
const vpassword = value => {
    // check if the input length is bigger than 6 and smaller than 40
    if (value.length < 6 || value.length > 40) {
        return (
            // if not return alert message
            <div className="alert-danger">
                Must be 6 - 40 characters
            </div>
        );
    }
};

export default{
    required,
    validEmail,
    vusername,
    vpassword
}