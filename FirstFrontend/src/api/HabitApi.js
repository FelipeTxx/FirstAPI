import apiClients from "./apiClient";

async function createHabit(nome, descricao, frequencia, meta){

    const resposta = await apiClients.apiClient("users/me/habits", 'POST', {nome, descricao, frequencia, meta})
    return await resposta;

}

async function showHabit() {

    const resposta = await apiClients.apiClient("user/me/habits", 'GET', null)
    return await resposta
    
}

const habitApi = {createHabit, showHabit}
export default habitApi