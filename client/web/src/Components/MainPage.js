import React, { Component } from "react";
import NavBar from "./NavBar";
import SimpleMap from "./SimpleMap";
import OrderPanel from "./OrderPanel";
import Login from "./Login";
import NewMap from "./NewMap"

const mapStyles = {
    width: '100%',
    height: '100%'
  };

//TODO:determine should we merge MainPage with App
class MainPage extends Component {
    render() {
        return (
            <div className="MainPage">
                <NavBar />
                <Login />
                {/* <SimpleMap className="Map" /> */}
                <NewMap/>
                <OrderPanel />
            </div>
        );
    }
}

export default MainPage;
