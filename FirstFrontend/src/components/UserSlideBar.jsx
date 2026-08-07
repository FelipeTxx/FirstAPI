import "./CSS/UserCard.css"
import { useNavigate } from "react-router-dom"

function UserSlideBar(props){
    const navigate = useNavigate()
    const dados = props?.dadosUser

    return(
        <aside className="sidebarUser">
            <h3 onClick={()=>navigate("/editarUser")} className="sidebarUser__name">
                <b>{dados?.nome || "Usuário"}</b>
            </h3>
            <p className="sidebarUser__meta"></p>
        </aside>
    )
}

export default UserSlideBar

