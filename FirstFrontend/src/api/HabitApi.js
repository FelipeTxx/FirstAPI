import apiClients from "./apiClient";

async function createHabit(nome, descricao, frequencia, meta){

    const resposta = await apiClients.apiClient("users/me/habits", 'POST', {nome, descricao, frequencia, meta})
    return await resposta;

}

async function showHabit() {

    const resposta = await apiClients.apiClientGet("users/me/habits", 'GET')
    return await resposta.json()
    
}

async function deleteHabit(id) {
    
    const resposta = await apiClients.apiClient(`users/me/habits/${id}`, 'DELETE', id)
    return await resposta

}

async function atualizarHabit(id, nome, descricao, frequencia, meta) {
    const resposta = await apiClients.apiClient(`users/me/habits/${id}`, 'PUT', {nome, descricao, frequencia, meta})
    return await resposta
}

const habitApi = {createHabit, showHabit, deleteHabit, atualizarHabit}
export default habitApi