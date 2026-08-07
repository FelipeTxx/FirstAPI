import Cadastro from "../pages/cadastro";
import Login from "../pages/login";
import Dashboard from "../pages/dashboard";
import EditarUser from "../pages/editarUser";

import { Route } from "react-router-dom";
import { Routes } from "react-router-dom";



function AppRoutes(){

    return(

         <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/cadastro" element={<Cadastro/>}/>
            <Route path="/dashboard" element={<Dashboard/>}/>
            <Route path="/editarUser" element={<EditarUser/>}/> 
        </Routes>


    )


}

export default AppRoutes
