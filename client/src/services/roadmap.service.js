import axios from 'axios';
import authHeader from './auth-header';

//GET request all roadmap info for current user, param: userId -> currentUser
//API/USER/ROADMAP/USER

//GET request, request param: roadmapTemplateId
//"/API/USER/ROADMAP/TEMPLATE"


const API_URL = 'http://localhost:8090/api/user/roadmap/';

const getRoadmap = (userId)=> {
    return axios.get(API_URL + "user", {
        headers:authHeader(),
        params:{userId}});
};

const getRoadmapTemplate = (roadmapTemplateId)=> {
    return axios.get(API_URL + "template", {
        headers:authHeader(),
        params:{roadmapTemplateId}});
};


export default {
    getRoadmap,
    getRoadmapTemplate
};