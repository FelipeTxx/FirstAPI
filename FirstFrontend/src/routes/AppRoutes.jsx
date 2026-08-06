import Cadastro from "../pages/cadastro";
import Login from "../pages/login";
import Dashboard from "../pages/dashboard";

import { Route } from "react-router-dom";
import { Routes } from "react-router-dom";



function AppRoutes(){

    return(

         <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/cadastro" element={<Cadastro/>}/>
             <Route path="/dashboard" element={<Dashboard/>}/>
        </Routes>


    )


}

export default AppRoutes
