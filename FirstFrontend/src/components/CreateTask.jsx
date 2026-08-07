import habitApi from "../api/HabitApi"
import { useState } from "react"
import "./CSS/CreateTask.css"

function CreateTask(props){

    const [descricao, setDescricao] = useState("")
    const [frequencia, setFrequencia] = useState("DIARIO")
    const [nome, setNome] = useState("")
    const [meta, setMeta] = useState("")

    async function cadastrarHabito(){
        const cadastro = await habitApi.createHabit(nome, descricao, frequencia, meta)
        if (cadastro.status === 200){
            await props.alternar()
        }
    }

    return(
        <div className="createTaskCard">
            <h3>Criar Habito</h3>
            <div className="createTaskGrid">
                <div>
                    <label htmlFor="name">Nome:</label>
                    <input onChange={(evento)=>setNome(evento.target.value)} value={nome} type="text" name="name" id="name" />
                </div>

                <div>
                    <label htmlFor="descricao">Descrição:</label>
                    <input onChange={(evento)=>setDescricao(evento.target.value)} value={descricao} type="text" name="descricao" id="descricao" />
                </div>

                <div>
                    <label htmlFor="frequencia">Frequência:</label>
                    <select onChange={(evento)=>setFrequencia(evento.target.value)} value={frequencia} name="frequencia" id="frequencia">
                        <option value="DIARIO">Diário</option>
                        <option value="SEMANAL">Semanal</option>
                        <option value="MENSAL">Mensal</option>
                    </select>
                </div>

                <div>
                    <label htmlFor="meta">Meta:</label>
                    <input onChange={(evento)=>setMeta(evento.target.value)} value={meta} type="date" name="meta" id="meta" />
                </div>
            </div>
            <div className="createTaskActions">
                <button className="btnPrimary" onClick={()=>cadastrarHabito()}>Criar</button>
            </div>
        </div>
    )
}
export default CreateTask
