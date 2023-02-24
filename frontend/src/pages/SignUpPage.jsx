import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

const SignInPage = () => {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    loginId: '',
    password: '',
    nickname: '',
  });

  const changeValue = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const signUpHandler = (e) => {
    e.preventDefault();
    axios
      .post('/api/member/signUp', {
        loginId: user.loginId,
        password: user.password,
        nickname: user.nickname,
      })
      .then((response) => response.data.code === 200 && navigate('/signIn'))
      .catch((error) => console.log(error.response.data.message));
  };

  return (
    <div style={{ width: '50%', padding: '20px' }}>
      <h1>SignUp Page</h1>
      <Form onSubmit={signUpHandler}>
        <Form.Group className="mb-3">
          <Form.Label>ID</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter ID"
            name="loginId"
            onChange={changeValue}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Enter Password"
            name="password"
            onChange={changeValue}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Nickname</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Nickname"
            name="nickname"
            onChange={changeValue}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          SignUp
        </Button>
      </Form>
    </div>
  );
};

export default SignInPage;
