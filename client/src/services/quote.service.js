import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8090/api/user/quote';

const getRandomQuote = (categoryName)=> {
    return axios.get(API_URL + "/random", {
        headers:authHeader(),
        params:{categoryName}});
};

export default{
    getRandomQuote
};
