import React from 'react';
import { Route, Routes } from 'react-router-dom';
import SignUp from './pages/SignUp';
import SignIn from './pages/SignIn';
import Chart from './pages/Chart';

function App() {
  return (
    <Routes>
      <Route path="/" exact={true} element={<SignUp />} />
      <Route path="/signIn" exact={true} element={<SignIn />} />
      <Route path="/chart" exact={true} element={<Chart />} />
    </Routes>
  );
}

export default App;
