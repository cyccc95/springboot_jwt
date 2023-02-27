import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import client from '../config/axiosConfig';

const ChartPage = () => {
  const [user, setUser] = useState({
    loginId: '',
    nickname: '',
  });

  useEffect(() => {
    const timer = setInterval(() => {
      client
        .get('/api/member/me')
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
    }, 5000);

    return () => clearInterval(timer);
  }, []);

  return (
    <div style={{ width: '50%', padding: '20px' }}>
      <h1>Chart Page</h1>
      <div></div>
    </div>
  );
};

export default ChartPage;
