import { useLocation, Navigate, Outlet } from "react-router-dom";
import useAuth from "../../hooks/useAuth";
import NavBarTop from "../Navs/NavBarTop";
import Navbar from "../Navs/NavBar";
import { useEffect, useState } from "react";
import axios from "axios"

const baseUrl = "https://roombookerapi.azurewebsites.net/api";

const RequireAuth = () => {
    const { auth } = useAuth();
    const location = useLocation();
    const [utilizador, setUtilizador] = useState();

    


    return (
        
             auth?.user 
                ? <div><NavBarTop /> <Navbar /> <Outlet /></div>
                : <Navigate to="/login" state={{ from: location }} replace />  
    );
}

export default RequireAuth;