

function getHeader(){
    const token = localStorage.getItem("token")

    return {'Content-Type':'application/json', Authorization: `Bearer ${token}`}

}

async function apiClient(endpoint, metodo, body){

    

    const baseUrl = "http://localhost:8081/" 
    const resposta = await fetch(baseUrl+endpoint, {method: metodo, headers: getHeader(), body: JSON.stringify(body)})


    return(await resposta)
    
}

async function apiClientGet(endpoint, metodo){

    

    const baseUrl = "http://localhost:8081/" 
    const resposta = await fetch(baseUrl+endpoint, {method: metodo, headers: getHeader()})


    return(await resposta)
    
}

async function apiClientPublic(endpoint, metodo, body){

    

    const baseUrl = "http://localhost:8081/" 
    const resposta = await fetch(baseUrl+endpoint, {method: metodo, headers: {'Content-Type':'application/json'}, body: JSON.stringify(body)})


    return(await resposta)
    
}

const apiClients = {apiClient, apiClientPublic, apiClientGet}

export default apiClients
