import "./CSS/CreateTask.css"
import TaskConclusionApi from "../api/TaskConclusionApi"
import { useState, useEffect } from "react"

function BtnTaskCard(props){

    const [taskFeita, setTaskFeita] = useState(false);

    async function concluirTask(){
        await TaskConclusionApi.concluirTask(props.id)
    }

    async function pegarTaskFeita(){
        const hoje = new Date();
        const data = `${hoje.getFullYear()}-${String(hoje.getMonth() + 1).padStart(2, "0")}-${String(hoje.getDate()).padStart(2, "0")}`;
        const feita = await TaskConclusionApi.pegarTaskConclusion(props.id, data)
        return data == feita.data
    }

    async function pegarTaskFeitaId(){
        const hoje = new Date();
        const data = `${hoje.getFullYear()}-${String(hoje.getMonth() + 1).padStart(2, "0")}-${String(hoje.getDate()).padStart(2, "0")}`;
        return await TaskConclusionApi.pegarTaskConclusion(props.id, data)
    }

    async function deletarTaskConclusion() {
        const conclusao = await pegarTaskFeitaId();
        await TaskConclusionApi.deletarTaskConclusion(props.id, conclusao.id);
    }

    async function marcarOuDesmarcarCheckBox(){
        if (taskFeita){
            await deletarTaskConclusion()
            setTaskFeita(false)
        } else {
            setTaskFeita(true)
            await concluirTask()
        }
    }
    
    useEffect(() => {
        async function verificarConclusao() {
            try {
                const feita = await pegarTaskFeita();
                setTaskFeita(feita);
            } catch {
                setTaskFeita(false);
            }
        }

        verificarConclusao();
    }, [])

    return(
        <button
            type="button"
            className={`taskItem ${props.active ? "taskItem--active" : ""}`}
            onClick={() => props.taskRequerida(props.task)}
        >
            <span style={{ display: "flex", alignItems: "center", gap: "12px" }}>
                <input type="checkbox" checked={taskFeita} onChange={(event)=>{event.stopPropagation(); marcarOuDesmarcarCheckBox()}} onClick={(event)=>event.stopPropagation()} />
                <h3 className="taskItemName">{props.nome}</h3>
            </span>
           
        </button>
    )
}

export default BtnTaskCard
