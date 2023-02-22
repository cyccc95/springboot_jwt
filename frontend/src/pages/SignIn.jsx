import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const SignIn = () => {
  const navigate = useNavigate();

  const [data, setData] = useState({
    userId: '',
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(data);
  };
  return (
    <>
      <form>
        <input
          type="text"
          placeholder="아이디"
          name="userId"
          onChange={handleChange}
        ></input>
        <input
          type="password"
          placeholder="비밀번호"
          name="password"
          onChange={handleChange}
        ></input>
        <button onClick={handleSubmit}>로그인</button>
      </form>
      <button
        onClick={() => {
          navigate('/signUp');
        }}
      >
        회원가입
      </button>
    </>
  );
};

export default SignIn;
