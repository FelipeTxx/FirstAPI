import "./CSS/CreateTask.css"

function CreateTaskBtn(props){

    

    return(

        <div>
            <button onClick={()=>props.alternar()} className="btnCriarTask">Criar Task</button>
        </div>

    )

}

export default CreateTaskBtn
