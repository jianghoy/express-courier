import React, { Component } from "react";
import MainPage from "./Pages/MainPage";
import { Route, Redirect, Switch } from "react-router-dom";
import CarInfo from "./Pages/CarInfo";
import DroneInfo from "./Pages/DroneInfo";
import Help from "./Pages/Help";
import OrderList from "./Pages/OrderList";
import NormalLoginForm from "./Pages/Login";
import { Layout, notification } from "antd";
import NavBar from "./Components/NavBar";
import RegisterPage from "./Pages/RegisterPage";
import {ACCESS_TOKEN} from "./Const";
import {request} from "./API/API";
const { Content } = Layout;
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading:false
        };
        // TODO: fix this
        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }

    getCurrentUser() {
        if(!localStorage.getItem(ACCESS_TOKEN)) {
            return Promise.reject("No access token set.");
        }
    
        return request({
            url: "/ec/user/me",
            method: 'GET'
        });
    }

    loadCurrentUser() {
        this.setState({
            isLoading: true
        });
        this.getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            })
            .catch(error => {
                this.setState({
                    isLoading: false
                });
            });
    }

    componentDidMount() {
        this.loadCurrentUser();
    }

    handleLogout(
        redirectTo = "/",
        notificationType = "success",
        description = "You're successfully logged out."
    ) {
        localStorage.removeItem(ACCESS_TOKEN);

        this.setState({
            currentUser: null,
            isAuthenticated: false
        });

        this.props.history.push(redirectTo);

        notification[notificationType]({
            message: "Polling App",
            description: description
        });
    }

    handleLogin() {
        notification.success({
            message: "Polling App",
            description: "You're successfully logged in."
        });
        this.loadCurrentUser();
        this.props.history.push("/");
    }

    render() {
        return (
            <Layout className="layout">
                <NavBar currentUser={this.state.currentUser}></NavBar>]
                <Content style={{ marginTop: "70px" }}>
                    <div>
                        <Switch>
                            <Route exact path="/" component={MainPage} />
                            <Route path="/home" component={MainPage} />
                            <Route path="/carinfo" component={CarInfo} />
                            <Route path="/droneinfo" component={DroneInfo} />
                            <Route path="/help" component={Help} />
                            <Route path="/orderList" component={OrderList} />
                            <Route path="/login" component={NormalLoginForm} />
                            <Route path="/logout" component={NormalLoginForm} />
                            <Route path="/register" component={RegisterPage} />
                            <Redirect to="/" />
                        </Switch>
                    </div>
                </Content>
            </Layout>
        );
    }
}
export default App;
