import axios from 'axios';
import authHeader from './auth-header';

// TEST AUTHORIZAION
// base url for requests to back-end
const API_URL = 'http://localhost:8090/api/test/';

const getPublicContent = ()=> {
    return axios.get(API_URL + "all");
};

const getUserBoard = ()=> {
    return axios.get(API_URL + "user", {headers:authHeader()});
};

const getAdminBoard = ()=> {
    return axios.get(API_URL + "admin", {headers:authHeader()});
};

const getModeratorBoard = ()=> {
    return axios.get(API_URL + "mod", {headers:authHeader()});
};

export default{
    getAdminBoard,
    getModeratorBoard,
    getPublicContent,
    getUserBoard,
};
