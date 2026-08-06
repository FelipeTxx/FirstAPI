
import api from "../api/api"
import { useEffect, useState } from "react"
import UserCard from "../components/UserCard"
import CreateTask from "../components/CreateTask"

import CreateTaskBtn from "../components/CreateTaskBtn"

function Dashboard(){
    const [createTaskForm, setCreateTaskForm] = useState(false)
    function alternarTaskBar(){
        setCreateTaskForm(!createTaskForm)
        return createTaskForm
    }


    const [dadosUsuario, setDadosUsuario] = useState(null)

    useEffect(() => {
        async function carregarDados(){

            const dados = await api.PegarDadosUsuario()
            setDadosUsuario(dados)
        }
        carregarDados()
        
    }, [])

    return(

        <div>
            <h2>Dados:</h2>
            <UserCard dadosUser={dadosUsuario} />
            <CreateTaskBtn alternar={alternarTaskBar}/>
            {createTaskForm ? <CreateTask alternar={alternarTaskBar}/> : null}
        </div>


    )

}
export default Dashboard