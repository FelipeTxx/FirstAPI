import { useState } from "react"
import AlternarEntreCadastro_E_Login from "../components/AlternarEntreCadastro_E_Login"
import userApis from "../api/UserApi"
import { useNavigate } from "react-router-dom"
import "../App.css"

function Cadastro(){
    const navigate = useNavigate()
    const [email, setEmail] = useState("")
    const [senha, setsenha] = useState("")
    const [nome, setNome] = useState("")
    const [idade, setIdade] = useState("")
    const [peso, setPeso] = useState("")
    const [altura, setAltura] = useState("")

    async function cadastrar(){
        const cadastro = userApis.CadastrarUsuario(email, senha, nome, idade, altura, peso)
        if (cadastro){
            await navigate("/")
        }
    }

    return(
        <div className="authPage">
            <div className="authCard">
                <h1>Cadastro</h1>
                <div className="authForm">
                    <div>
                        <label htmlFor="email">Email:</label>
                        <input onChange={(evento)=>setEmail(evento.target.value)} value={email} type="email" name="email" id="email" />
                    </div>
                    <div>
                        <label htmlFor="password">Senha:</label>
                        <input onChange={(evento)=>setsenha(evento.target.value)} value={senha} type="password" name="password" id="password" />
                    </div>
                    <div>
                        <label htmlFor="name">Nome:</label>
                        <input onChange={(evento)=>setNome(evento.target.value)} value={nome} type="text" name="name" id="name" />
                    </div>
                    <div>
                        <label htmlFor="idade">Idade:</label>
                        <input onChange={(evento)=>setIdade(evento.target.value)} value={idade} type="text" name="idade" id="idade" />
                    </div>
                    <div>
                        <label htmlFor="altura">Altura:</label>
                        <input onChange={(evento)=>setAltura(evento.target.value)} value={altura} type="text" name="altura" id="altura" />
                    </div>
                    <div>
                        <label htmlFor="peso">Peso:</label>
                        <input onChange={(evento)=>setPeso(evento.target.value)} value={peso} type="text" name="peso" id="peso" />
                    </div>
                    <div className="authActions">
                        <button onClick={()=>cadastrar()}>Cadastrar</button>
                        <AlternarEntreCadastro_E_Login path="/" texto="Já tem conta? Clique Aqui!" />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Cadastro

