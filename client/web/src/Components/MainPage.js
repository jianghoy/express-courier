import React, { Component } from "react";
import NavBar from "./NavBar";
import SimpleMap from "./SimpleMap";
import OrderPanel from "./OrderPanel";
import OrderList from "./OrderList";

//TODO:determine should we merge MainPage with App
class MainPage extends Component {
    render() {
        return (
            <div className="MainPage">
                <NavBar />
                <SimpleMap className="Map" />
                {/* //TODO: put className on InfoCard component */}
                {/* <InfoCard className = "infoCard"/> */}
                <OrderList />
                <OrderPanel />
            </div>
        );
    }
}

export default MainPage;
