

async function RealizarLogin(email, senha){
    
    const reposta = await fetch("http://localhost:8081/auth/login", {method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify({email, senha})})
    const dados = await reposta.json()
    await localStorage.setItem("token", dados.token)
    if ( await dados.token){
        return true
    }
    else {return false}

}
async function RealizarCadastro(email, senha, nome, idade, altura, peso) {
    const reposta = await fetch("http://localhost:8081/users", {method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify({email, senha, nome, idade, altura, peso})})
    console.log(reposta.status)
    if (reposta.status == 200){
        return true
    }
    else{return false}
}

async function PegarDadosUsuario() {
    const reposta = await fetch("http://localhost:8081/users/me", {method: 'GET', headers: getHeader() })
    const dados = await reposta.json()
    
    return dados;
}


function getHeader(){
    const token = localStorage.getItem("token")

    return {'Content-Type':'application/json', Authorization: `Bearer ${token}`}
}
const api = {RealizarLogin, PegarDadosUsuario, RealizarCadastro}
export default api



