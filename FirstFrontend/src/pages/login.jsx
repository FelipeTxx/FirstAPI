
import { useState } from "react"

function Login(){

    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")
    
    async function logar(){

        const resposta = await fetch("http://localhost:8081/auth/login", {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({email, senha})})
        const dados = await resposta.json()
        alert(dados.token)
        
    }

    return(

        

        <div>
            <h2>Login: </h2>
            <label htmlFor="email">E-mail:</label>
            <input onChange={(evento) => setEmail(evento.target.value)} value={email} type="email" id="email" name="email"/>
            <br />
            <label htmlFor="password">Password:</label>
            <input onChange={(evento) => setSenha(evento.target.value)} value={senha} type="password" id="password" name="password" />
            <br />
            <button onClick={() => logar()} className="login">Login</button>
        </div>
        

    
    )

}

export default Login