import { useNavigate } from "react-router-dom";
import "./AlternarEntreCadastro_E_Login.css"

function AlternarEntreCadastro_E_Login(props){

    const navigate = useNavigate();

    function alternar(path){
        navigate(path)
    }

    return(
        <div>
            <p className="alternar" onClick={()=>alternar(props.path)}> {props.texto} </p>
        </div>
    )
}
export default AlternarEntreCadastro_E_Login