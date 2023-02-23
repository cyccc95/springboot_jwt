import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const ChartPage = () => {
  const [user, setUser] = useState({
    loginId:'',
    nickname:''
  });

  useEffect(() => {
    axios.defaults.headers.common[
      'Authorization'
    ] = `Bearer ${localStorage.getItem('accessToken')}`;
    axios
      .get('/api/user/1')
      .then((response) => setUser({...user, response.data.data}))
      .catch((error) => console.log(error));
  }, []);

  return (
    <div style={{ width: '50%', padding: '20px' }}>
      <h1>Chart Page</h1>
      <div></div>
    </div>
  );
};

export default ChartPage;
