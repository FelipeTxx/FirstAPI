import "./CSS/CreateTask.css"
import habitApi from "../api/HabitApi"
import TaskCard from "./TaskCard"
import TaskConclusionApi from "../api/TaskConclusionApi"
import { useState } from "react"
import { useEffect } from "react"




function BtnTaskCard(props){


    const [taskFeita, setTaskFeita] = useState(false);

    async function concluirTask(){
        const conclude = await TaskConclusionApi.concluirTask(props.id)
    }
    async function pegarTaskFeita(){
        const hoje = new Date();
        const data = `${hoje.getFullYear()}-${String(hoje.getMonth() + 1).padStart(2, "0")}-${String(hoje.getDate()).padStart(2, "0")}`;
        

        const feita = await TaskConclusionApi.pegarTaskConclusion(props.id, data)
        if (data == feita.data){return true}
        else{return false}
    }

    async function pegarTaskFeitaId(){
        const hoje = new Date();
        const data = `${hoje.getFullYear()}-${String(hoje.getMonth() + 1).padStart(2, "0")}-${String(hoje.getDate()).padStart(2, "0")}`;
        

        const feita = await TaskConclusionApi.pegarTaskConclusion(props.id, data)

        return feita;
    }



    function pegarTodasTaskConclusion(){
        const conclusions = TaskConclusionApi.pegarTodasAsTasksConclusion(props.id)

    }

    async function getAllTasks(){const reposta = await habitApi.showHabit()}

    async function deletarTaskConclusion() {
        const conclusao = await pegarTaskFeitaId();

        const idConclude = conclusao.id;


        await TaskConclusionApi.deletarTaskConclusion(
            props.id,
            idConclude
        );
    }

    async function marcarOuDesmarcarCheckBox(){
        if (taskFeita){
           
            await deletarTaskConclusion()
            setTaskFeita(false)
        }
        else{setTaskFeita(true); await concluirTask()}
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
        <div className="BtnName">
            <input type="checkbox" checked={taskFeita} onChange={()=>{marcarOuDesmarcarCheckBox()}}/>
            <h3 onClick={()=>{props.task, props.taskRequerida(props.task)}}>{props.nome} </h3>
        </div>

        
    )
    

}

export default BtnTaskCard
