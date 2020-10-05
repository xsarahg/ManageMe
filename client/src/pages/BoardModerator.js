import React, { useState, useEffect } from "react";
import UserService from "../services/test.user.service";
import AuthService from "../services/auth.service";
import PleaseLogin from "../components/PleaseLogin";

//Page is currently unused, made for future proofing

const BoardUser = () => {
    const [content, setContent] = useState("");
    const currentUser = AuthService.getCurrentUser();

    useEffect(() => {
        UserService.getModeratorBoard().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);
            }
        );
    }, []);

    if (currentUser) {
        return (
            <div className="content">
                <header>
                    <h3>{content}</h3>
                </header>
            </div>
        );
    }
    else{
        return(
            <div className="content">
                <PleaseLogin/>
            </div>
        );
    }
};

export default BoardUser;