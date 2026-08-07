import { useState } from "react"
import { useNavigate } from "react-router-dom";
import AlternarEntreCadastro_E_Login from "../components/AlternarEntreCadastro_E_Login";
import authApi from "../api/AuthApi";
import "../App.css"

function Login(){
    const navigate = useNavigate();
    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")

    async function logar(email, senha){
        const chamado = await authApi.authApiPost(email, senha)
        if (chamado){
            navigate("/dashboard")
        }
    }

    return(
        <div className="authPage">
            <div className="authCard">
                <h2>Login</h2>
                <div className="authForm">
                    <div>
                        <label htmlFor="email">E-mail:</label>
                        <input onChange={(evento) => setEmail(evento.target.value)} value={email} type="email" id="email" name="email"/>
                    </div>
                    <div>
                        <label htmlFor="password">Password:</label>
                        <input onChange={(evento) => setSenha(evento.target.value)} value={senha} type="password" id="password" name="password" />
                    </div>
                    <div className="authActions">
                        <button onClick={() => logar(email, senha)} className="login">Login</button>
                        <AlternarEntreCadastro_E_Login path="/cadastro" texto="Não tem conta? Clique Aqui!" />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login

