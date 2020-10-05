import React from "react";
import{
    BsFillStarFill as StarIcon,
    BsAwardFill as RibbonIcon
} from 'react-icons/bs'

function Home() {
    //only seen by users who aren't logged in
    return(
        <div className="content">
            <div className="top-container">
                <div className="picture">
                    <h1>Manage Me</h1>
                    <h4>Your personal guide into the industry</h4>
                </div>
                <div className="side-box side-box-a">
                    <h3>Musical Expertise</h3>
                    <p>Our team consists of experienced managers from the music industry, providing you with the expertise you need to break through.</p>
                    <StarIcon/>
                </div>
                <div className="side-box side-box-b">
                    <h3>A step-by-step program</h3>
                    <p>With our Roadmap you will learn everything you need to know, and upon completion you will receive a certification to show potential employers what you can do.</p>
                    <RibbonIcon/>
                </div>
            </div>
        </div>
    );
}

export default Home;