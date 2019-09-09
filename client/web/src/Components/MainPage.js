import React, { Component } from "react";
import NavBar from "./NavBar";
import SimpleMap from "./SimpleMap";
import OrderPanel from "./OrderPanel";

//TODO:determine should we merge MainPage with App
class MainPage extends Component {
    render() {
        return (
            <div className="MainPage">
                <NavBar />
                <SimpleMap className="Map" />
                {/* //TODO: put className on InfoCard component */}
                {/* <InfoCard className = "infoCard"/> */}
                <OrderPanel />
            </div>
        );
    }
}

export default MainPage;
