import apiClients from "./apiClient";

async function CadastrarUsuario(email, senha, nome, idade, altura, peso) {
    const reposta = await apiClients.apiClientPublic("users", 'POST', {email, senha, nome, idade, altura, peso})
    if (reposta.status == 200){
        return true
    }
    else{return false}
}

export default CadastrarUsuario