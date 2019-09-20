import React, { Component } from "react";
import MainPage from "./Pages/MainPage";
import { Route, Redirect, Switch } from "react-router-dom";
import CarInfo from "./Pages/CarInfo";
import DroneInfo from "./Pages/DroneInfo";
import Help from "./Pages/Help";
import OrderList from "./Pages/OrderList";
import NormalLoginForm from "./Pages/Login";
import { Layout } from "antd";
import NavBar from "./Components/NavBar";
import RegisterPage from "./Pages/RegisterPage";
const { Content } = Layout;
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false
        }
        // TODO: fix this
        // this.handleLogout = this.handleLogout.bind(this);
        // this.loadCurrentUser = this.loadCurrentUser.bind(this);
        // this.handleLogin = this.handleLogin.bind(this);
    }


    render() {
        return (
            <Layout className = "layout">
                <header><NavBar></NavBar></header>
                
                <Content style= {{ marginTop: '70px' }}>
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
