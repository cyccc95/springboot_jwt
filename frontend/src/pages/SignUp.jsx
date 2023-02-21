import React, { useState } from 'react';

const SignUp = () => {
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
          type="email"
          placeholder="이메일"
          name="email"
          onChange={handleChange}
        ></input>
        <input
          type="password"
          placeholder="비밀번호"
          name="password"
          onChange={handleChange}
        ></input>
        <button onClick={handleSubmit}>회원가입</button>
      </form>
    </>
  );
};

export default SignUp;
