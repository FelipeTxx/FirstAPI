import apiClients from "./apiClient";
import habitApi from "./HabitApi";


async function pegarTaskConclusion(taskId, data){

    const resposta = await apiClients.apiClientGet(`me/habits/hoje/${taskId}/${data}`, 'GET')
    

    const dados = await resposta.json();

    return dados;

}

async function concluirTask(taskId){

    const resposta = await apiClients.apiClient(`me/habits/${taskId}/complete`, 'POST', {taskId})

    return await resposta

}

async function pegarTodasAsTasksConclusion(taskId){
    const resposta = await apiClients.apiClient(`me/habits/${taskId}/all`, 'GET', {taskId})
    return await resposta
}


async function deletarTaskConclusion(taskId, id){
    const resposta = await apiClients.apiClientGet(`me/habits/${taskId}/${id}`, 'DELETE')
    return await resposta
}


const TaskConclusionApi = {pegarTaskConclusion, concluirTask, pegarTodasAsTasksConclusion, deletarTaskConclusion}

export default TaskConclusionApi

