import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom";
import userApis from "../api/UserApi"
import api from "../api/api.js"
import "../App.css"

function EditarUser(){
    const navigate = useNavigate()
    const [dadosUsuario, setDadosUsuario] = useState(null)
    const [nome, setNome] = useState("")
    const [idade, setIdade] = useState("")
    const [peso, setPeso] = useState("")
    const [altura, setAltura] = useState("")

    async function atualizarUser(){
        await userApis.AtualizarUsuario(nome, idade, altura, peso) 
    }

    async function deleteUser(){
        await userApis.deleteUser()
        navigate("/")
    }

    useEffect(() => {
        async function carregarDados(){
            const dados = await api.PegarDadosUsuario()
            setDadosUsuario(dados)

            if (dados) {
                setNome(dados.nome || "")
                setIdade(dados.idade || "")
                setAltura(dados.altura || "")
                setPeso(dados.peso || "")
            }
        }

        carregarDados()
    }, [])

    return(
        <div className="authPage">
            <div className="authCard">
                <button onClick={()=>navigate("/dashboard")}>Voltar</button>
                <h1 style={{ marginTop: "16px" }}>{dadosUsuario?.nome || "Editar usuário"}</h1>
                <div className="authForm">
                    <div>
                        <label htmlFor="name">Nome:</label>
                        <input onChange={(evento) => setNome(evento.target.value)} value={nome} type="text" name="name" id="name" />
                    </div>
                    <div>
                        <label htmlFor="idade">Idade:</label>
                        <input onChange={(evento) => setIdade(evento.target.value)} value={idade} type="text" name="idade" id="idade" />
                    </div>
                    <div>
                        <label htmlFor="altura">Altura:</label>
                        <input onChange={(evento) => setAltura(evento.target.value)} value={altura} type="text" name="altura" id="altura" />
                    </div>
                    <div>
                        <label htmlFor="peso">Peso:</label>
                        <input onChange={(evento) => setPeso(evento.target.value)} value={peso} type="text" name="peso" id="peso" />
                    </div>
                    <div className="authActions">
                        <button onClick={()=>atualizarUser()}>Alterar dados</button>
                        <button onClick={()=>deleteUser()}>Excluir Conta</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default EditarUser

