
import { useState } from "react"
import api from '../api/api'
import { useNavigate } from "react-router-dom";
import AlternarEntreCadastro_E_Login from "../components/AlternarEntreCadastro_E_Login";
import authApi from "../api/AuthApi";



function Login(){

    const navigate = useNavigate();

    async function logar(email, senha){
        const chamado = await authApi.authApiPost(email, senha)
        if (chamado){
            navigate("/dashboard")
        }
    }


    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")


    return(

        

        <div>
            <h2>Login</h2>
            <label htmlFor="email">E-mail:</label>
            <input onChange={(evento) => setEmail(evento.target.value)} value={email} type="email" id="email" name="email"/>
            <br />
            <label htmlFor="password">Password:</label>
            <input onChange={(evento) => setSenha(evento.target.value)} value={senha} type="password" id="password" name="password" />
            <br />
            <button onClick={() => logar(email, senha)} className="login">Login</button>
            <br />
            <AlternarEntreCadastro_E_Login path="/cadastro" texto="Não tem conta? Clique Aqui!" />
        </div>
        

    
    )

}

export default Login