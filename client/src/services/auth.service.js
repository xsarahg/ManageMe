import axios from "axios";

// base url for requests to back-end
const API_URL = "http://localhost:8090/api/auth/";

// register POST request
const register = (username,email,password,category) => {
    return axios.post(API_URL + "sign-up", {
        username,
        email,
        password,
        category,
    });
};

//reset password POST request
const reset = (token,password) => {
  return axios.post(API_URL + "password/reset",null, {params:
          {
              token,
              password
          }
  });
};

// login POST request
const login = (username, password) => {
    return axios
        .post(API_URL + "login", {
        username,
        password,
    })
        .then((response) => {
        if (response.data.accessToken){
            localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
    });
};

// does exactly what you think it does
const logout = ()=> {
    localStorage.removeItem("user");
};

const getCurrentUser = ()=> {
    return JSON.parse(localStorage.getItem("user"));
};

export default{
    register,
    reset,
    login,
    logout,
    getCurrentUser
};
