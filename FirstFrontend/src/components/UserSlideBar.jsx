import { useState } from "react"
import "./CSS/UserCard.css"
import { useNavigate } from "react-router-dom"

function UserSlideBar(props){

    const navigate = useNavigate()
    
    const dados = props?.dadosUser
    
    
    return(
        
        <div className="mainDiv">
            <h3 onClick={()=>navigate("/editarUser")} className="UserName"><b>{dados?.nome}</b></h3>

        </div>

    )
}
export default UserSlideBar