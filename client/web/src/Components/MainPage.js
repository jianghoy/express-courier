import React, { Component } from "react";
import NavBar from "./NavBar";
import SimpleMap from "./SimpleMap";
import OrderPanel from "./OrderPanel";

class MainPage extends Component {
    render() {
        return (
            <div className="MainPage">
                <NavBar/>
                <SimpleMap />
                <InfoCard />
            </div>
        );
    }
}

export default MainPage;