import React from 'react';

//must be added to every page that requires a logged in user so you can't access it without logging in.
function PleaseLogin(){
    return(
        <div className="content">
            <div className="please-login">
                <h2>Whoops!</h2>
                <p>It seems you're not yet logged in. Please do so or create an account.</p>
            </div>
        </div>
    );
}

export default PleaseLogin;