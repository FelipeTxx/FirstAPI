import { useEffect, useState } from "react"
import habitApi from "../api/HabitApi"

function TaskCard(props){
    const [editMode, setEditMode] = useState(false)
    const [nome, setNome] = useState(props?.nome || "")
    const [descricao, setDescricao] = useState(props?.descricao || "")
    const [frequencia, setFrequencia] = useState(props?.frequencia || "")
    const [meta, setMeta] = useState(props?.meta || "")


    async function atualizarHabit(){
        const atualizado = await habitApi.atualizarHabit(props.id, nome, descricao, frequencia, meta)

    }



    useEffect(() => {
        setEditMode(false)
        setNome(props?.nome || "")
        setDescricao(props?.descricao || "")
        setFrequencia(props?.frequencia || "")
        setMeta(props?.meta || "")
    }, [props?.id, props?.nome, props?.descricao, props?.frequencia, props?.meta])

    return(
        <div className="taskDetailCard">
            <div className="taskDetailTop">
                <div>
                    <h3 className="taskDetailTitle">{editMode ? nome : props?.nome}</h3>
                    <p style={{ color: "var(--muted)", marginTop: "6px" }}>Detalhes do hábito selecionado</p>
                </div>
                <button className="btnDanger" onClick={()=>props.delete(props.id)}>Delete</button>
            </div>

            <div className="taskDetailMeta">
                <div className="taskField">
                    <label>Nome</label>
                    {editMode ? (
                        <input value={nome} onChange={(event)=>setNome(event.target.value)} />
                    ) : (
                        <div className="taskFieldValue">{props?.nome}</div>
                    )}
                </div>

                <div className="taskField">
                    <label>Descrição</label>
                    {editMode ? (
                        <textarea rows="4" value={descricao} onChange={(event)=>setDescricao(event.target.value)} />
                    ) : (
                        <div className="taskFieldValue">{props?.descricao}</div>
                    )}
                </div>

                <div className="taskField">
                    <label>Frequência</label>
                    {editMode ? (
                        <input value={frequencia} onChange={(event)=>setFrequencia(event.target.value)} />
                    ) : (
                        <div className="taskFieldValue">{props?.frequencia}</div>
                    )}
                </div>

                <div className="taskField">
                    <label>Meta</label>
                    {editMode ? (
                        <input type="date" value={meta} onChange={(event)=>setMeta(event.target.value)} />
                    ) : (
                        <div className="taskFieldValue">{props?.meta}</div>
                    )}
                </div>
            </div>

            <div className="taskDetailFooter">
                {editMode ? (
                    <button className="btnPrimary" onClick={()=>{setEditMode(false), atualizarHabit(), props.carregar(), props.mostrarTask()}}>Salvar</button>
                ) : (
                    <button className="btnPrimary" onClick={()=>setEditMode(true)}>Alterar</button>
                )}
            </div>
        </div>
    )
}
export default TaskCard

