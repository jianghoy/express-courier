import React from 'react';
import './App.css';
import NavBar from "./Components/NavBar"
import SimpleMap from './Components/SimpleMap';
import InfoCard from "./Components/InfoCard";

function App() {
  return (
    <div className="App">
        <NavBar/>
        <SimpleMap/>
        <InfoCard/>
    </div>
  );
}

export default App;
