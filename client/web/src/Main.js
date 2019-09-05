import React, { Component } from "react";
import { Route, NavLink, HashRouter } from "react-router-dom";
import Car from "./Car";
import Drone from "./Drone";
import Help from "./Help";
import Login from "./Login";

class Main extends Component {
    render() {
        return (
            <HashRouter>
            <div>
                <h1> Menu </h1>
                <ul className="header">
                    <li><NavLink to="">Logo / Home page</NavLink></li>
                    <li><a href="/car">Car Info</a></li>
                    <li><a href="/drone">Drone Info</a></li>
                    <li><a href="/help">Help</a></li>
                    <li><a href="/login">Login</a></li>
                </ul>
                <div classname="content">
                
                </div>
            </div>
            </HashRouter>
        );
    }
}

export default Main;