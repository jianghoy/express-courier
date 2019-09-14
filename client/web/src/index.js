import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import MainPage from "./Components/MainPage";
import { BrowserRouter, Route, Redirect, Switch } from 'react-router-dom'
import CarInfo from './Components/CarInfo';
import * as serviceWorker from './serviceWorker';

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
