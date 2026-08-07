
import api from "../api/api"
import { useEffect, useState } from "react"
import UserCard from "../components/UserCard"
import CreateTask from "../components/CreateTask"
import habitApi from "../api/HabitApi";
import CreateTaskBtn from "../components/CreateTaskBtn"
import TaskCard from "../components/TaskCard";
import BtnTaskCard from "../components/BtnTaskCard";
import UserSlideBar from "../components/UserSlideBar";



function Dashboard(){
    const [createTaskForm, setCreateTaskForm] = useState(false)
    function alternarTaskBar(){
        setCreateTaskForm(!createTaskForm)
        pegarTasks()
        return createTaskForm
    }

    function mostrarTask(taskRequerida){
        setTaskAberta(taskRequerida)
    }
    async function deleteTask(id){
        console.log("Tentando deletar:", taskAberta);
        const resposta  = await habitApi.deleteHabit(id)
        console.log("Status DELETE:", resposta.status);
        await pegarTasks()
    }
    

    const [taskAberta, setTaskAberta] = useState(null)
    function fecharTask(taskRequerida){if (taskAberta == taskRequerida){alert("aaaa"); setTaskAberta(null)}}

    const [tasks, setTasks] = useState([])

    async function pegarTasks(){
        const tasks = await habitApi.showHabit();
        await setTasks(tasks)
    }


    const [dadosUsuario, setDadosUsuario] = useState(null)

    useEffect(() => {
        async function carregarDados(){
               
            const dados = await api.PegarDadosUsuario()
            setDadosUsuario(dados)
            await pegarTasks()
        }
         
        carregarDados()
        
    }, [])

    return(

        <div>
            <UserSlideBar dadosUser={dadosUsuario} />
            <CreateTaskBtn alternar={alternarTaskBar}/>
            {createTaskForm ? <CreateTask alternar={alternarTaskBar}/> : null}
            <div>{tasks.map((task)=>{
                return (

                    
                    <div key={task.id}><BtnTaskCard id={task.id} task={task} nome={task.nome} taskRequerida={mostrarTask} /> 
                    {taskAberta?.id == task.id? <TaskCard id={taskAberta.id} nome={taskAberta.nome} descricao={taskAberta.descricao} frequencia={taskAberta.frequencia} meta={taskAberta.meta} delete={deleteTask}/> : null}</div>
            
                    
                )
            })}
            
            </div>
        </div>


    )

}
export default Dashboard