import React, {useState, useEffect} from "react";
import AuthService from "../services/auth.service";
import PleaseLogin from "../components/PleaseLogin";
import photoService from "../services/photo.service";
import trackService from "../services/track.service";
import artistPageService from "../services/artistPage.service";

const Profile = () => {

    ////This page is a mess due to time constraints. If we had more time, many elements would have become components

    //general variables
    const currentUser = AuthService.getCurrentUser();
    const [artistPage, setArtistPage] = useState("");
    const [currentFile, setCurrentFile] = useState(undefined);
    const [progress, setProgress] = useState(0);
    const [active,setActive] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    //genre variables
    const [genreName, setGenreName] = useState("");
    const [genreRating, setGenreRating] = useState(0);
    const [genreMessage, setGenreMessage] = useState("");

    //experience variables
    const [experienceName, setExperienceName] = useState("");
    const [experienceInstitution, setExperienceInstitution] = useState(0);
    const [experienceMessage, setExperienceMessage] = useState("");

    //biography variables
    const [biography, setBiography] = useState("");
    const [biographyMessage, setBiographyMessage] = useState("");

    //image variables
    const [selectedImage, setSelectedImage] = useState(undefined);
    const [imageMessage, setImageMessage] = useState("");
    const [ImageInfos, setImageInfos] = useState([]);
    const [image, setImage] = useState(undefined);
    const [images, setImages] = useState('');
    const [imageName, setImageName] = useState(undefined);

    //track variables
    const [selectedTracks, setSelectedTracks] = useState(undefined);
    const [trackMessage, setTrackMessage] = useState("");
    const [TrackInfos, setTrackInfos] = useState([]);
    const [tracks, setTracks] = useState('');
    const [trackName, setTrackName] = useState(undefined);


    //functions
    //active determines whether buttons and input fields should be visible
    const toggleActive = () => {
        setActive(!active);
    };

    //gets all data for current user
    const getArtistPage = async () => {
        try {
            const response = await artistPageService.getArtistPage(currentUser.id);
            setArtistPage(response.data);
        } catch (error) {
            setErrorMessage("Did not found an artist page");
        }
    };

    //user posts a new genre
    const postGenre = () => {
        artistPageService.postGenre(genreName, "", genreRating, currentUser.id).then((response) => {
            setGenreMessage(response.data);
            window.location.reload();
        });
    };

    //user posts a new genre
    const postExperience = () => {
        artistPageService.postExperience(experienceName, "", experienceInstitution, "","",currentUser.id).then((response) => {
            setExperienceMessage(response.data);
            window.location.reload();
        });
    };

    //user posts their bio
    const postBiography = () => {
        artistPageService.postBiography(biography, currentUser.id).then((response) => {
            setBiographyMessage(response.data);
            window.location.reload();
        });
    };

    //onChange functions set variables to what was entered
    const onChangeGenreName = (e)=> {
        setGenreName(e.target.value);
    };

    const onChangeExperienceName = (e)=> {
        setExperienceName(e.target.value);
    };

    const onChangeExperienceInstitution = (e)=> {
        setExperienceInstitution(e.target.value);
    };

    const onChangeGenreRating = (e)=> {
        setGenreRating(e.target.value);
    };

    const onChangeBiography = (e)=> {
        setBiography(e.target.value);
    };

    //does what you think it does
    const selectImage = (event) => {
        setSelectedImage(event.target.files);
        setImageName(event.target.files[0].name);
    };

    const selectTrack = (event) => {
        setSelectedTracks(event.target.files);
        setTrackName(event.target.files[0].name);
    };

    const deleteGenre = (genreId) => {
        artistPageService.deleteGenre(genreId).then((response) => {
            setGenreMessage(response.data);
        })
    };

    const deleteExperience = (experienceId) => {
        artistPageService.deleteExperience(experienceId).then((response) => {
            setExperienceMessage(response.data);
        })
    };

    //upload the selected picture
    const uploadPhoto = ()=> {
        let currentFile = selectedImage[0];

        setProgress(0);
        setCurrentFile(currentFile);

        //this uploads the picture to the DB. Description is unused, made just in case we need it
        photoService.upload(currentFile, currentUser.id, "",false, (event)=>{
            setProgress(Math.round((100*event.loaded)/event.total));
        })
            //if upload is successful
            .then((files)=>{
                setImageInfos(files.data);
                setImageMessage("Success!");
            })
            //if upload failed
            .catch(()=>{
                setProgress(0);
                setImageMessage("Could not upload the file... :(");
                setCurrentFile(undefined);
            });
        setSelectedImage(undefined);
    };

    //upload selected mp3 track
    const uploadTrack = ()=> {
        let currentFile = selectedTracks[0];

        setProgress(0);
        setCurrentFile(currentFile);

        //this uploads the track to DB. Name and description are unused, made just in case we need them. Use fileName instead.
        trackService.upload(currentFile, currentUser.id, "", "", (event)=>{
            setProgress(Math.round((100*event.loaded)/event.total));
        })
            //if upload was successful
            .then((files)=>{
                setTrackInfos(files.data);
                setTrackMessage("Success!");
            })
            //if upload failed
            .catch(()=>{
                setProgress(0);
                setTrackMessage("Could not upload the file... :(");
                setCurrentFile(undefined);
            });
        setSelectedTracks(undefined);
    };

    // get all images by userId
    const getImages = async () => {
        try {
            const response = await photoService.getPhotos(currentUser.id);
            setImages(response.data);

        } catch (error) {
            setErrorMessage("Did not fid an image");
        }
    };

    //get all music tracks by userId
    const getTracks = async () => {
        try {
            const response = await trackService.getTracks(currentUser.id);
            setTracks(response.data);

        } catch (error) {
            setErrorMessage("Did not found a track");
        }
    };

    //Allows the user to delete their pictures
    const deletePhoto = (photoId) => {
        photoService.deletePhoto(photoId).then((response) => {
            setImageMessage(response.data);
        })
    };

    //Allows the user to delete their tracks
    const deleteTrack = (trackId) => {
        trackService.deleteTrack(trackId).then((response) => {
            setTrackMessage(response.data);
        })
    };

    //get all user data, images and tracks when page loads or when a new image or track is added
    useEffect(()=>{
        getImages();
        getTracks();
        getArtistPage();
    },[ImageInfos,TrackInfos]);


    if (currentUser) {//check if user is logged in
        return (
            <div className="content">
                {/*Start of profile page*/}
                <div className="profile-intro">
                    <h2>{currentUser.username}'s Profile</h2>
                    <button className="changes-button" onClick={toggleActive}>
                        Change Profile
                    </button>
                </div>

                <div className="profile">
                    <div className="profile-top">
                        {/*<biography box. user can read and change their biography here*/}
                        <div className="profile-box">
                            <h3>Biography</h3>
                            {/*if user has a biography it is displayed here*/}
                            {artistPage.biography && (
                                <p>{artistPage.biography}</p>
                            )}

                            <div className={active ? "profile-change" : "profile-change-hidden"}>
                                {/*input field for biography*/}
                                <input
                                    type="text"
                                    placeholder="Your biogaphy"
                                    name="biography"
                                    value={biography}
                                    onChange={onChangeBiography}
                                />

                                {/*upload biography*/}
                                <button className="upload-button" onClick={postBiography}>Submit</button>

                                {/*show message*/}
                                {biographyMessage && (
                                    <div className="form-group">
                                        {biographyMessage}
                                    </div>
                                )}
                            </div>
                        </div>

                        {/*genre box. users can see, add and delete genres here*/}
                        <div className="profile-box">
                            <h3>Genres</h3>
                            {/*if user already has genres, display them*/}
                            {artistPage.genres && (
                                <div>
                                    {artistPage.genres.map(genre => (
                                        <div key={genre.id}>
                                            <p>{genre.name}, Rating: {genre.rating}</p>
                                            <button className={active ? "profile-change profile-change-delete" : "profile-change-hidden"} onClick={() => {if(window.confirm('Are you sure to delete this item?')){deleteGenre(genre.id)}}}>Delete</button>
                                        </div>
                                    ))}

                                    <div className={active ? "profile-change" : "profile-change-hidden"}>
                                        {/*input genre name*/}
                                        <input
                                            type="text"
                                            maxLength="15"
                                            placeholder="Genre name"
                                            name="name"
                                            value={genreName}
                                            onChange={onChangeGenreName}
                                        />

                                        {/*input your rating for said genre*/}
                                        <input
                                            min="0"
                                            max="10"
                                            type="range"
                                            name="rating"
                                            value={genreRating}
                                            onChange={onChangeGenreRating}
                                        />
                                        <label>{genreRating}</label>

                                        {/*upload genre*/}
                                        <button className="upload-button" onClick={postGenre}>Submit</button>

                                        {genreMessage && (
                                            <div className="form-group">
                                                {genreMessage}
                                            </div>
                                        )}
                                    </div>
                                </div>
                            )}
                        </div>

                        {/*work experience box*/}
                        <div className="profile-box">
                            <h3>Work experience</h3>
                            {artistPage.experiences && (
                                <div>
                                    {artistPage.experiences.map(experience => (
                                            <div key={experience.id}>
                                                <p>{experience.name}, at {experience.institution}</p>
                                                <button className={active ? "profile-change profile-change-delete" : "profile-change-hidden"} onClick={() => {if(window.confirm('Are you sure to delete this item?')){deleteExperience(experience.id)}}}>Delete</button>
                                            </div>
                                        ))}

                                    <div className={active ? "profile-change" : "profile-change-hidden"}>
                                        {/*input experience name*/}
                                        <input
                                            type="text"
                                            placeholder="Job name"
                                            name="name"
                                            value={experienceName}
                                            onChange={onChangeExperienceName}
                                        />

                                        {/*input institution*/}
                                        <input
                                            type="text"
                                            placeholder="company name"
                                            name="name"
                                           // value={experienceInstitution}
                                            onChange={onChangeExperienceInstitution}
                                        />

                                        {/*upload experience*/}
                                        <button className="upload-button" onClick={postExperience}>Submit</button>

                                        {experienceMessage && (
                                            <div className="form-group">
                                                {experienceMessage}
                                            </div>
                                        )}
                                    </div>
                                </div>
                            )}

                        </div>
                    </div>

                    <div className="profile-bottom">
                        {/*pictures box*/}
                       <div className="profile-box" >
                           <h3>Pictures</h3>
                           <div className="upload">
                               <div className={active ? "profile-change file-buttons" : "profile-change-hidden"}>
                                   <div>
                                        {/*select file from pc to upload*/}
                                        <label htmlFor="image-upload" className="file-upload">Select Image</label>
                                        <input id="image-upload" type="file" className="input" onChange={selectImage}/>
                                   </div>

                                   {/*show image name*/}
                                   <div>
                                       {imageName && (
                                               <p>{imageName}</p>
                                       )}
                                   </div>

                                   <div>
                                       {/*upload picture*/}
                                       <button
                                           className="upload-button"
                                           disabled={!selectedImage}
                                           onClick={uploadPhoto}
                                       >
                                           Upload
                                       </button>

                                       {/*show message*/}
                                       <div className="alert alert-light" role="alert">
                                           {imageMessage}
                                       </div>
                                   </div>
                               </div>
                           </div>
                           {images && (
                               <div className="image-container">
                                   {/*if user has images stored*/}
                                   {images.map(image => (
                                       <div key={image.id}>
                                           {/*show images*/}
                                           <img src={`data:image/jpeg;base64,${image.data}`} alt={image.description} width="370px"/>
                                           <button className={active ? "profile-change profile-change-delete" : "profile-change-hidden"} onClick={() => {if(window.confirm('Are you sure to delete this item?')){ deletePhoto(image.id)}}}>Delete</button>
                                       </div>
                                       ))}
                               </div>
                           )}
                       </div>

                        {/*music tracks box*/}
                        <div className="profile-box">
                            <h3>Tracks</h3>
                            <div>
                                <div className={active ? "profile-change file-buttons-narrow" : "profile-change-hidden"}>
                                    <div>
                                        {/*choose file from pc to upload*/}
                                         <label htmlFor="track-upload" className="file-upload">Select Track</label>
                                         <input id="track-upload" type="file" className="input" onChange={selectTrack}/>
                                    </div>

                                    <div>
                                        {/*show track name*/}
                                        {trackName && (
                                            <p>{trackName}</p>
                                        )}
                                    </div>

                                    <div>
                                        {/*upload button*/}
                                        <button
                                            className="upload-button"
                                            disabled={!selectedTracks}
                                            onClick={uploadTrack}
                                        >
                                            Upload
                                        </button>

                                        {/*show message*/}
                                        <div className="alert alert-light" role="alert">
                                            {trackMessage}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            {/*if user already has music tracks*/}
                            {tracks && (
                                <div>
                                    {/*show music tracks*/}
                                {tracks.map(track => (
                                    <div key={track.id}>
                                    <div className="track-container">
                                        <label>{track.fileName}</label>
                                        <audio controls>
                                            <source src={`data:audio/mp3;base64,${track.data}`} type="audio/mp3"/>
                                            Your browser does not support the audio element.
                                        </audio>
                                    </div>
                                        {/*allows users to delete tracks*/}
                                    <button className={active ? "profile-change profile-change-delete" : "profile-change-hidden"} onClick={() => {if(window.confirm('Are you sure to delete this item?')){ deleteTrack(track.id)}}}>Delete</button>
                                    </div>
                                    ))}
                                </div>
                            )}
                        </div>

                    </div>
                </div>
            </div>
        );
    }
    else{//if user is not logged in
        return(
          <div className="content">
              <PleaseLogin/>
          </div>
        );
    }
};

export default Profile;