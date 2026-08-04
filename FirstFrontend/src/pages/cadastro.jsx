import { useState } from "react"



function Cadastro(){

    const [email, setEmail] = useState("")
    const [senha, setsenha] = useState("")
    const [nome, setNome] = useState("")
    const [idade, setIdade] = useState("")
    const [peso, setPeso] = useState("")
    const [altura, setAltura] = useState("")

    async function cadastrar(){

        const resposta = await fetch("http://localhost:8081/users", {method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify({nome, idade, altura, peso, senha, email})}) 
        alert(resposta.status)   
        
    }

    return(
        
        <div>
            <h1>Cadastro</h1>
            <label htmlFor="email" >Email: </label>
            <input onChange={(evento)=>setEmail(evento.target.value)} value={email} type="email" name="email" id="email" />
            <br />
            <label htmlFor="password">Senha: </label>
            <input onChange={(evento)=>setsenha(evento.target.value)} value={senha} type="passwork" name="password" id="password" />
            <label htmlFor="text">Nome: </label>
            <input onChange={(evento)=>setNome(evento.target.value)} value={nome} type="text" name="name" id="name" />
            <label htmlFor="texto">Idade:</label>
            <input onChange={(evento)=>setIdade(evento.target.value)} type="text" name="idade" id="idade" />
            <label htmlFor="text">Altura: </label>
            <input onChange={(evento)=>setAltura(evento.target.value)} type="text" name="altura" id="altura" />
            <label htmlFor="text">Peso: </label>
            <input onChange={(evento)=>setPeso(evento.target.value)} type="text" name="peso" id="peso" />
            <button onClick={()=>cadastrar()}>Cadastrar</button>
        
        </div>

    )

}

export default Cadastro