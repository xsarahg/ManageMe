import React from 'react';
import{ Link } from 'react-router-dom';
import logo from '../images/logos/ManageMeLogoWeb.png';
import AuthService from "../services/auth.service";
import {useHistory} from "react-router-dom";

function NavigationBar() {
    // variables
    const user = AuthService.getCurrentUser();
    const history = useHistory();
    let userType = "";

    // check user type
    if (user) {
        if (user.roles.includes("ROLE_ADMIN")) {
            userType = "admin"
        } else if (user.roles.includes("ROLE_MODERATOR")) {
            userType = "mod"
        } else if (user.roles.includes("ROLE_USER")) {
            userType = "user"
        }
    }

    // does exactly what you think it does
    function logOut(){
        AuthService.logout();
        history.push("/");
        window.location.reload();
    }

    // return menu based on user type
    switch(userType) {
        case "admin": //currently unused, here for future proofing
            return (
                <div className="top-nav-wrapper">
                    <ul className="admin-menu">
                        <li><img src={logo} alt="logo"/></li>
                        <li><Link to="/" onClick={()=> logOut()}>Log Out</Link></li>
                    </ul>
                </div>
            );
            break;

        case "mod":  //currently unused, here for future proofing
            return (
                <div className="top-nav-wrapper">
                    <ul className="mod-menu">
                        <li><img src={logo} alt="logo"/></li>
                        <li><Link to="/" onClick={()=> logOut()}>Log Out</Link></li>
                    </ul>
                </div>
            );
            break;

        case "user": //menu for logged in users
            return (
                <div className="top-nav-wrapper">
                    <ul className="user-menu">
                        <li><img src={logo} alt="logo"/></li>
                        <li><Link to="/user">Home</Link></li>
                        <li><Link to="/profile">Profile</Link></li>
                        <li><Link to="/roadmap">Roadmap</Link></li>
                        <li><Link to="/tutorials">Tutorials</Link></li>
                        <li><Link to="/" onClick={()=> logOut()}>Log Out</Link></li>
                    </ul>
                </div>
            );
            break;

        default: //used when not logged in
            return(
                <div className="top-nav-wrapper">
                    <ul className="default-menu">
                        <li><img src={logo} alt="logo"/></li>
                        <li><Link to="/">Home</Link></li>
                        <li><Link to="/login">Log in</Link></li>
                        <li><Link to="/sign-up">Sign up</Link></li>
                    </ul>
                </div>
            );
            break;
    }
}

export default NavigationBar;