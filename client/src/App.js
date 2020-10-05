import React from "react";
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";
import "./styles/App.scss";
import MenuBar from "./components/NavigationBar";
import { //import all pages so they can be added to router
    HomePage,
    LoginPage,
    ProfilePage,
    RegisterPage,
    AdminHome,
    ModeratorHome,
    UserHome,
    ForgotPassword,
    ResetPassword,
    Tutorials,
    Roadmap,
} from "./pages";

//add pages and define their url
function App(){
  return(
      <div className="wrapper">
        <Router>
          <MenuBar/>
          <Switch>
              <Route exact path="/">
                  <HomePage/>
              </Route>
              <Route exact path="/mod">
                  <ModeratorHome/>
              </Route>
              <Route exact path="/admin">
                  <AdminHome/>
              </Route>
              <Route exact path="/user">
                  <UserHome/>
              </Route>
              <Route exact path="/profile">
                  <ProfilePage/>
              </Route>
              <Route exact path="/login">
                  <LoginPage/>
              </Route>
              <Route exact path="/sign-up">
                  <RegisterPage/>
              </Route>
              <Route exact path="/forgot-password">
                  <ForgotPassword/>
              </Route>
              <Route path="/api/auth/password/:token" children={<ResetPassword/>} />
              <Route exact path="/tutorials">
                  <Tutorials/>
              </Route>
              <Route exact path="/roadmap">
                  <Roadmap/>
              </Route>
          </Switch>
        </Router>
      </div>
  );
}

export default App;