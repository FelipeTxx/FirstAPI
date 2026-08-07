import { useState } from "react"
import { useEffect } from "react"
import { useNavigate } from "react-router-dom";
import userApis from "../api/UserApi"
import api from "../api/api.js"

function EditarUser(){


    const navigate = useNavigate()
    const [dadosUsuario, setDadosUsuario] = useState(null)

    const [nome, setNome] = useState("")
    const [idade, setIdade] = useState("")
    const [peso, setPeso] = useState("")
    const [altura, setAltura] = useState("")

    async function atualizarUser(){
        const atualizado = await userApis.AtualizarUsuario(nome, idade, altura, peso) 
    }
    async function deleteUser(){
        const deleted = await userApis.deleteUser()
        navigate("/")
    }

    useEffect(() => {
        async function carregarDados(){
               
            const dados = await api.PegarDadosUsuario()
            await setDadosUsuario(dados)

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

        
        <div>
            <button onClick={()=>navigate("/dashboard")}>Voltar</button>
            <br />
            
            <label htmlFor="name">Nome: </label>
            <input onChange={(evento) => setNome(evento.target.value)} value={nome} type="text" name="name" id="name" />
            <br />
            <label htmlFor="idade">Idade:</label>
            <input onChange={(evento) => setIdade(evento.target.value)} value={idade} type="text" name="idade" id="idade" />
            <br />
            <label htmlFor="altura">Altura: </label>
            <input onChange={(evento) => setAltura(evento.target.value)} value={altura} type="text" name="altura" id="altura" />
            <br />
            <label htmlFor="peso">Peso: </label>
            <input onChange={(evento) => setPeso(evento.target.value)} value={peso} type="text" name="peso" id="peso" />
            <br />
            <button onClick={()=>atualizarUser()}>Alterar dados</button>
            <button onClick={()=>deleteUser()}>Excluir Conta</button>


        
        </div>

    )

}

export default EditarUser