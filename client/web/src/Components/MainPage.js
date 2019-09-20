import React, { Component } from "react";
import NavBar from "./NavBar";
import SimpleMap from "./SimpleMap";
import OrderPanel from "./OrderPanel";
import Login from "./Login";

//TODO:determine should we merge MainPage with App
class MainPage extends Component {
    render() {
        return (
            <div className="MainPage">
                <NavBar />
                {/* <Login /> */}
                <SimpleMap className="Map" />
                <OrderPanel />
            </div>
        );
    }
}

export default MainPage;
