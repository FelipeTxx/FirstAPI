import { useState } from "react"
import "./CSS/UserCard.css"


function UserCard(props){

    
    const dados = props?.dadosUser
    
    
    return(
        
        <div >
            
            <h3><b>{dados?.nome}</b></h3>
            <p><b>Email:</b> {dados?.email}</p>
            <p><b>Idade:</b> {dados?.idade}</p>
            <p><b>Altura:</b> {dados?.altura}</p>
            <p><b>Peso:</b> {dados?.peso}</p>

        </div>

    )
}

export default UserCard