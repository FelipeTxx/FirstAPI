import apiClients from "./apiClient";


async function authApiPost(email, senha){

    const resposta = await apiClients.apiClientPublic("auth/login", 'POST', {email, senha})
    const dados = await resposta.json()
    await localStorage.setItem("token", dados.token)
    if (await dados.token){return true}
    else{return await false}


}

const authApi = {authApiPost}

export default authApi