

function TaskCard(props){
    

    return(

        <div className="TaskEnvolve">

            <h3>Nome: {props?.nome}</h3>
            <p><b>Descrição: {props?.descricao}</b></p>
            <p><b>Frequencia: </b>{props?.frequencia}</p>
            <p><b>Meta: </b>{props?.meta}</p>
            <button onClick={()=>props.delete(props.id)}>Delete</button>

        </div>

    )



}
export default TaskCard