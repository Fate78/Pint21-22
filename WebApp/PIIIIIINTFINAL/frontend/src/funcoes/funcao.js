import React, {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';

export default function Localizacao() {
    const [localizacao, setLocalizacao] = useState();
    const location = useLocation();

    useEffect(() => {
        setLocalizacao(location.pathname.split("/")[2]);
    }, [location]);
    
    return localizacao
}