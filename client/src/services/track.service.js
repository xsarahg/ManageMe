import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8090/api/user/track/';

const upload = (file, userId, name, description, onUploadProgress)=>{
    let formData = new FormData();

    formData.append("file",file);
    formData.append("userId", userId);
    formData.append("name", name);
    formData.append("description", description);

    const headers = {
        'Content-Type':'multipart/form-data;boundary=032a1ab685934650abbe059cb45d6ff3',
        'Authorization': authHeader().Authorization
    };

    return axios.post(API_URL + "upload", formData, {
        headers: headers,
        onUploadProgress,
    });
};

const getTracks = (userId)=> {
    return axios.get(API_URL +"get-tracks", {
        headers:authHeader(),
        params:{userId}
    });
};

const deleteTrack = (trackId) => {
    return axios.delete(API_URL + "delete", {
        headers:authHeader(),
        params:{trackId}});
};

export default {
    upload,
    getTracks,
    deleteTrack
};