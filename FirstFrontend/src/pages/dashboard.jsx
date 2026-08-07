import api from "../api/api"
import { useEffect, useState } from "react"
import CreateTask from "../components/CreateTask"
import habitApi from "../api/HabitApi";
import CreateTaskBtn from "../components/CreateTaskBtn"
import TaskCard from "../components/TaskCard";
import BtnTaskCard from "../components/BtnTaskCard";
import UserSlideBar from "../components/UserSlideBar";
import { useNavigate } from "react-router-dom";
import "../App.css"

function Dashboard(){
    const navigate = useNavigate()
    const [createTaskForm, setCreateTaskForm] = useState(false)
    const [taskAberta, setTaskAberta] = useState(null)
    const [tasks, setTasks] = useState([])
    const [dadosUsuario, setDadosUsuario] = useState(null)

    function alternarTaskBar(){
        setCreateTaskForm(!createTaskForm)
        pegarTasks()
        return createTaskForm
    }

    function mostrarTask(taskRequerida){
        setTaskAberta(taskRequerida)
    }

    async function deleteTask(id){
        const resposta  = await habitApi.deleteHabit(id)
        if (resposta.status === 200 || resposta.status === 204) {
            await pegarTasks()
            setTaskAberta(null)
        }
    }

    async function pegarTasks(){
        const listaTasks = await habitApi.showHabit();
        setTasks(listaTasks)
    }
    async function carregarDados(){
            const dados = await api.PegarDadosUsuario()
            setDadosUsuario(dados)
            await pegarTasks()
    }

    useEffect(() => {
        carregarDados()
    }, [])

    return(
        <div className="appShell">
            <div className="appLayout">
                <div className="dashboardLayout">
                    <section className="dashboardPanel dashboardPanel--list">
                        <div className="dashboardHeader">
                            <div>
                                <h1 className="dashboardTitle">Caixa de Entrada</h1>
                                <p style={{ color: "var(--muted)", marginTop: "6px" }}></p>
                            </div>
                            <button className="backLoginButton" onClick={() => navigate("/")}>
                                <span className="backLoginButton__icon">←</span>
                                <span>Voltar para o login</span>
                            </button>
                        </div>
                        <UserSlideBar dadosUser={dadosUsuario} />
                        <CreateTaskBtn alternar={alternarTaskBar}/>
                        {createTaskForm ? <CreateTask alternar={alternarTaskBar}/> : null}
                        <div className="taskList">
                            {tasks.map((task)=>(
                                <div key={task.id} className="taskRow">
                                    <BtnTaskCard
                                        id={task.id}
                                        task={task}
                                        nome={task.nome}
                                        taskRequerida={mostrarTask}
                                        active={taskAberta?.id === task.id}
                                    />
                                </div>
                            ))}
                        </div>
                    </section>

                    <aside className="dashboardPanel dashboardPanel--detail">
                        {taskAberta ? (
                            <TaskCard
                                id={taskAberta.id}
                                nome={taskAberta.nome}
                                descricao={taskAberta.descricao}
                                frequencia={taskAberta.frequencia}
                                meta={taskAberta.meta}
                                carregar={carregarDados}
                                mostrarTask={mostrarTask}
                                delete={deleteTask}
                            />
                        ) : (
                            <div className="taskDetailsEmpty">
                                <div>
                                    <h2>Selecione uma task</h2>
                                    <p style={{ marginTop: "8px" }}>
                                        
                                    </p>
                                </div>
                            </div>
                        )}
                    </aside>
                </div>
            </div>
        </div>
    )
}
export default Dashboard
