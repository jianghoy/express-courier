import React, { Component } from "react";
import MainPage from "./Pages/MainPage";
import { Route, Redirect, Switch, withRouter } from "react-router-dom";
import CarInfo from "./Pages/CarInfo";
import DroneInfo from "./Pages/DroneInfo";
import Help from "./Pages/Help";
import OrderList from "./Pages/OrderList";
import NormalLoginForm from "./Pages/Login";
import { Layout, notification } from "antd";
import NavBar from "./Components/NavBar";
import RegisterPage from "./Pages/RegisterPage";
import { ACCESS_TOKEN } from "./Const";
import { request } from "./API/API";
import LoadingIndicator from "./Components/LoadingIndicator";
const { Header, Content } = Layout;
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading: false
        };
        // TODO: fix this
        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }

    getCurrentUser() {
        if (!localStorage.getItem(ACCESS_TOKEN)) {
            return Promise.reject("No access token set.");
        }

        return request({
            url: "/ec/user/me",
            method: "GET"
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

    checkCurrentUser = () => {
        return this.state.currentUser !== null;
    };

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
            message: "Express Courier",
            description: description
        });
    }

    handleLogin() {
        notification.success({
            message: "Express Courier",
            description: "You're successfully logged in."
        });
        this.loadCurrentUser();
        this.props.history.push("/");
    }

    render() {
        // TODO:Loading indicator
        // if (this.state.isLoading) {
        //     return <LoadingIndicator/>
        // }
        return (
            <Layout className="layout">
                <Header style={{ padding: 0 }}>
                    <NavBar
                        currentUser={this.state.currentUser}
                        logout={this.handleLogout}
                    ></NavBar>
                </Header>
                <Content>
                    <div style={{ minHeight: "300px" }}>
                        <Switch>
                            <Route
                                exact
                                path="/"
                                render={props => (
                                    <MainPage
                                        handleCheckUser={this.checkCurrentUser}
                                        {...props}
                                    />
                                )}
                            />
                            <Route
                                path="/home"
                                render={props => (
                                    <MainPage
                                        handleCheckUser={this.checkCurrentUser}
                                        {...props}
                                    />
                                )}
                            />
                            <Route path="/carinfo" component={CarInfo} />
                            <Route path="/droneinfo" component={DroneInfo} />
                            <Route path="/help" component={Help} />
                            <Route path="/orderList" component={OrderList} />
                            <Route
                                path="/login"
                                render={props => (
                                    <NormalLoginForm
                                        onLogin={this.handleLogin}
                                        {...props}
                                    />
                                )}
                            ></Route>
                            <Route path="/register" component={RegisterPage} />
                            <Redirect to="/" />
                        </Switch>
                    </div>
                </Content>
            </Layout>
        );
    }
}
export default withRouter(App);
