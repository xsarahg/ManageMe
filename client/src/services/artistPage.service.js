import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8090/api/user/artist-page';

////// artist page
const getArtistPage = (userId) => {
    return axios.get(API_URL, {
        headers:authHeader(),
        params:{userId}});
};


////// biography
const getBiography = (userId) => {
    return axios.get(API_URL + "/biography", {
        headers:authHeader(),
        params:{userId}});
};

const postBiography = (biography, userId) => {
    return axios.post(API_URL + "/biography", null, {
        headers:authHeader(),
        params:{biography, userId}});
};

const deleteBiography = (userId) => {
    return axios.delete(API_URL + "/biography", {
        headers:authHeader(),
        params:{userId}});
};

const updateBiography = (userId, biography) => {
    return axios.put(API_URL + "/biography", null,{
        headers:authHeader(),
        params:{userId, biography}});
};


////// genres
const getGenres = (userId) => {
    return axios.get(API_URL + "/genres", {
        headers:authHeader(),
        params:{userId}});
};

const getGenre = (genreId) => {
    return axios.get(API_URL + "/genre", {
        headers:authHeader(),
        params:{genreId}});
};

const postGenre = (name, description, rating, userId) => {
    return axios.post(API_URL + "/genre", null,{
        headers:authHeader(),
        params:{name, description, rating, userId}
        });
};

const deleteGenre = (genreId) => {
    return axios.delete(API_URL + "/genre", {
        headers:authHeader(),
        params:{genreId}});
};

const updateGenre = (genreId, name, description, rating) => {
    let genre = [name, description, rating];
    return axios.put(API_URL + "/genre", null,{
        headers:authHeader(),
        params:{genreId, genre}});
};


////// experiences
const getExperiences = (userId) => {
    return axios.get(API_URL + "/experiences", {
        headers:authHeader(),
        params:{userId}});
};

const postExperience = (name, details, institution, place, link, userId) => {
    return axios.post(API_URL + "/experience", null, {
        headers:authHeader(),
        params:{name, details, institution, place, link, userId}
    });
};

const deleteExperience = (experienceId) => {
    return axios.delete(API_URL + "/experience", {
        headers:authHeader(),
        params:{experienceId}});
};


////// news
const getNews = (userId) => {
    return axios.get(API_URL + "/news", {
        headers:authHeader(),
        params:{userId}});
};

const postNews = (name, description, link, userId) => {
    return axios.post(API_URL + "/news-item", null, {
        headers:authHeader(),
        params:{name, description, link, userId}
    });
};

const deleteNews = (newsItemId) => {
    return axios.delete(API_URL + "/news-item", {
        headers:authHeader(),
        params:{newsItemId}});
};


export default {
    getArtistPage,

    getBiography,
    postBiography,
    deleteBiography,
    updateBiography,

    getGenres,
    getGenre,
    postGenre,
    deleteGenre,
    updateGenre,

    getExperiences,
    postExperience,
    deleteExperience,

    getNews,
    postNews,
    deleteNews
};

