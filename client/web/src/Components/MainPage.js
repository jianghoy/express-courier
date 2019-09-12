import React, { Component } from "react";
import NavBar from "./NavBar";
import SimpleMap from "./SimpleMap";
import InfoCard from "./InfoCard";

//TODO:determine should we merge MainPage with App
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
