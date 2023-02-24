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
    // axios.defaults.headers.common[
    //   'Authorization'
    // ] = `Bearer ${localStorage.getItem('accessToken')}`;
    // axios
    //   .get('/api/member/1')
    //   .then((response) => setUser({ ...user, ...response.data.data }))
    //   .catch((error) => console.log(error));
  }, []);

  return (
    <div style={{ width: '50%', padding: '20px' }}>
      <h1>Chart Page</h1>
      <div></div>
    </div>
  );
};

export default ChartPage;
