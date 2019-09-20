import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import MainPage from "./Components/MainPage";
import { BrowserRouter, Route, Redirect, Switch } from 'react-router-dom'
import CarInfo from './Components/CarInfo';
import DroneInfo from './Components/DroneInfo';
import Help from './Components/Help';
import * as serviceWorker from './serviceWorker';
import OrderList from './Components/OrderList';
import NormalLoginForm from './Components/Login';

ReactDOM.render(<App />, document.getElementById('root'));

function Home() {
    return (
        <MainPage />
    );
}

function App() {
    return (
        <BrowserRouter>
            <div>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route path="/home" component={Home}/>
                    <Route path="/carinfo" component={CarInfo}/>
                    <Route path="/droneinfo" component={DroneInfo}/>
                    <Route path="/help" component={Help}/>
                    <Route path="/orderList" component={OrderList}/>
                    <Route path="/login" component={NormalLoginForm}/>
                    <Route path="/logout" component={NormalLoginForm}/>
                    <Redirect to="/"/>
                </Switch>
            </div>
        </BrowserRouter>
    );
  }
export default App;
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
