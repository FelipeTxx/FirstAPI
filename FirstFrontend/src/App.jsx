import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import Login from './pages/login'
import Cadastro from './pages/cadastro'
import { useEffect } from 'react';

import './App.css'


function App() {
  const [paginaAtual, setPaginaAtual] = useState("Login")
  const [textoParaMudarPagina, setTextoParaMudarPagina] = useState("click")

  function mudarLoginCadastro() {
    if (paginaAtual === "Login") {
      setPaginaAtual("Cadastro")
      setTextoParaMudarPagina("Não tem conta? clique aqui!")
    }
    else{
      setPaginaAtual("Login")
      setTextoParaMudarPagina("Já uma tem conta? clique aqui!")
    }
  }
  function setarTexto(){
    paginaAtual === "Login" ? setTextoParaMudarPagina("Não tem conta? clique aqui!") : setTextoParaMudarPagina("Já tem uma conta? clique aqui!")
  }
  useEffect(() => {
    setarTexto();
  }, []);


  return (



    <div>
      <span></span>
      <span on={()=>setarTexto()}>{paginaAtual === "Login" ? <Login/> : <Cadastro/>}</span>
      <span className='irLogin' onClick={() => mudarLoginCadastro()}>{textoParaMudarPagina}</span>
    </div>


  )

}

export default App 