import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import Login from './pages/login'
import Cadastro from './pages/cadastro'
import { useEffect } from 'react';
import PegarDadosUsuario from './api/api.js'
import { Route } from 'react-router-dom'
import AppRoutes from './routes/AppRoutes.jsx'
import { BrowserRouter } from 'react-router-dom'

import './App.css'
import { Routes } from 'react-router-dom'


function App() {

  return (
    <AppRoutes/>
  )

}

export default App 