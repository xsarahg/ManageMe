import React, { useState, useEffect } from "react";
import QuoteService from "../services/quote.service";
import AuthService from "../services/auth.service";
import PleaseLogin from "../components/PleaseLogin";
import Singer from "../images/singer.svg";

const BoardUser = () => {
    const currentUser = AuthService.getCurrentUser();
    const [quote, setQuote] = useState('');
    const [errorMessage, setErrorMessage] = useState("");

    const getRandomQuote = async () => {
        try {
            const response = await QuoteService.getRandomQuote(currentUser.category);
            setQuote(response.data);
        } catch (error) {
            setErrorMessage("Start you journey!");
        }
    };

    useEffect(()=>{
        getRandomQuote();
    },[]);


    if (currentUser) {
        return (
            <div className="content">
                <header className="greeting">
                    <h1>Welcome Back {currentUser.username}!</h1>
                </header>
                <div className="quote-box">
                    <h3>"{quote.text}"</h3>
                    <h4>- {quote.author}</h4>
                    {errorMessage && (
                        <h2>{errorMessage}</h2>
                    )}
                </div>
                {currentUser.category === "CATEGORY_ARTIST" && (
                    <img className="singer-image" src={Singer} alt="singer"/>
                )}
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