import apiClients from "./apiClient";

async function CadastrarUsuario(email, senha, nome, idade, altura, peso) {
    const reposta = await apiClients.apiClientPublic("users", 'POST', {email, senha, nome, idade, altura, peso})
    if (reposta.status == 200){
        return true
    }
    else{return false}
}

async function AtualizarUsuario(nome, idade, altura, peso) {

    const resposta = await apiClients.apiClient("users/me", 'PUT', {nome, idade, altura, peso} )
    return resposta
    
}

async function deleteUser() {
    const resposta = await apiClients.apiClientGet("users/delete/me", 'DELETE')
    return resposta
}

const userApis = {CadastrarUsuario, AtualizarUsuario, deleteUser}
export default userApis