import React from 'react';

function Video({name,channel,link,description}){
    return(
      <div className="video-container">
          <div className="video">
            <iframe width="560" height="315" src={link} frameborder="0" allow="accelerometer; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
          </div>
          <div className="video-text">
              <h3>{name}</h3>
              <p>{`By ${channel}`}</p>
          </div>
          <p>{description}</p>
      </div>
    );
}

export default Video;