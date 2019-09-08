import React from 'react';
import MainPage from "./Components/MainPage";


// TODO: decide how to serve various pages: via router or components
function App() {
  return (
    <div className="App">
        {/* {(showMainPage)?<MainPage/>:<anotherPage/>} */}
        <MainPage/>
    </div>
  );
}

export default App;
