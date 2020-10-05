export default function authHeader() {

    // get current user's local storage
    const user = JSON.parse(localStorage.getItem('user'));

    // check if the current user is logged-in and has a JWT token
    if (user && user.accessToken) {
        // if so, return authorization header with user's JWT token
        return { Authorization: 'Bearer ' + user.accessToken };
    } else {
        // if not, return an empty object
        return {};
    }
}