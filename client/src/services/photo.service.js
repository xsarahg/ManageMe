import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8090/api/user/photo/';

const upload = (file, userId, description, profilePicture, onUploadProgress)=>{
    let formData = new FormData();

    formData.append("file",file);
    formData.append("userId", userId);
    formData.append("description", description);
    formData.append("profilePicture", profilePicture);

    const headers = {
        'Content-Type':'multipart/form-data;boundary=032a1ab685934650abbe059cb45d6ff3',
        'Authorization': authHeader().Authorization
    };

    return axios.post(API_URL + "upload", formData, {
        headers: headers,
        onUploadProgress,
        // params:{file, userId, description, profilePicture}
        });
};

const getPhoto = (photoId)=> {
    return axios.get(API_URL +"get-photo", {
        headers:authHeader(),
        params:{photoId}
        });
};

const getPhotos = (userId)=> {
    return axios.get(API_URL +"get-photos", {
        headers:authHeader(),
        params:{userId}
    });
};

const deletePhoto = (photoId) => {
    return axios.delete(API_URL + "delete", {
        headers:authHeader(),
        params:{photoId}});
};

export default {
    upload,
    getPhoto,
    getPhotos,
    deletePhoto
};