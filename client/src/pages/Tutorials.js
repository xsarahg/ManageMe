import React, {useEffect, useState} from 'react';
import Video from "../components/Video";
import AuthService from "../services/auth.service";
import PleaseLogin from "../components/PleaseLogin";

function Tutorials(){
    const currentUser = AuthService.getCurrentUser();

    if(currentUser) {
        return (
            <>
                <div className="head">
                    <h1>Tutorials</h1>
                    <h5>Here you will find the best tutorials selected by our team to help you on your way.</h5>
                </div>

                <div className="tutorial-wrapper">
                    <div className="video-box">
                        <Video
                            name={"How to write a CV"}
                            channel={"StandOut CV"}
                            link={"https://www.youtube.com/embed/uG_LKVJjuAc"}
                            description={"An excellent video explaining how to write a professional looking cv, which is the first step to a successful career. "}
                        />
                    </div>

                    <div className="video-box">
                        <Video
                            name={"Voice Lessons for Beginners"}
                            channel={"SuperiorSinging"}
                            link={"https://www.youtube.com/embed/QOBmBH6GVBg"}
                            description={"Superior Singing Method is a professional online vocal lessons program which allows you to maximize your vocal skills lightning fast. "}
                        />
                    </div>

                    <div className="video-box">
                        <Video
                            name={"PERSONAL Bio Example"}
                            channel={"Professor Heather Austin"}
                            link={"https://www.youtube.com/embed/ecw_ugVd81k"}
                            description={"In this video you will learn to write a short professional bio. "}
                        />
                    </div>

                    <div className="video-box">
                        <Video
                            name={"How To Start A Music Career At 16 "}
                            channel={"AdamIvy"}
                            link={"https://www.youtube.com/embed/hPxfsVo-w04"}
                            description={"Learning how to be successful in the music industry when you're still in school is no easy task. We will help you te get the fundamentals you need. "}
                        />
                    </div>
                </div>
            </>
        );
    }
    else{
       return(
         <div className="content">
             <PleaseLogin/>
         </div>
       );
    }
}

export default Tutorials;