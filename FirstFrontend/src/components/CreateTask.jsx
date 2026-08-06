
import habitApi from "../api/HabitApi"
import { use, useState } from "react"

function CreateTask(props){

    const [descricao, setDescricao] = useState("")
    const [frequencia, setFrequencia] = useState("DIARIO")
    const [nome, setNome] = useState("")
    const [meta, setMeta] = useState("")

    const [retorno, setRetorno] = useState(403) 

    const [erro, setErro] = useState(null)

    async function cadastrarHabito(){
        console.log(frequencia)
        const cadastro =await habitApi.createHabit(nome, descricao, frequencia, meta)
        if (await cadastro.status === 200){
            await props.alternar()
        }
    }


    

    return(
        
        <div>
            <h3>Criar Habito</h3>

            <label htmlFor="text">Nome: </label>
            <input onChange={(evento)=>setNome(evento.target.value)} value={nome} type="text" name="name" id="name" />
            <br />

            <label htmlFor="text" >Descrição: </label>
            <input onChange={(evento)=>setDescricao(evento.target.value)} value={descricao} type="text" name="descricao" id="descricao" />
            <br />

            <label htmlFor="frequencia">Frequência: </label>
            <select onChange={(evento)=>setFrequencia(evento.target.value)} value={frequencia} type="text" name="frequencia" id="frequencia">

                <option value="DIARIO">Diario</option>
                <option value="SEMANAL">Semanal</option>
                <option value="MENSAL">Mensal</option>
                

            </select>
            <br />

            

            <label htmlFor="date">Meta:</label>
            <input onChange={(evento)=>setMeta(evento.target.value)} value={meta} type="date" name="meta" id="meta" />
            <br />
           
            <button onClick={()=>{cadastrarHabito()}}  > Criar </button>

            
        </div>
    )
}
export default CreateTask