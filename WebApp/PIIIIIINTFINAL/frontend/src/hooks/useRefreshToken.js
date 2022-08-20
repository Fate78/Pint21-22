import axios from "axios";
import useAuth from "./useAuth";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

const useRefreshToken = () => {
    const {setAuth} = useAuth();

    const refresh = async () => {
        const response = await axios.get(baseUrl + "/authenticate", {
            withCredentials: true
        });

        setAuth(prev => {
            console.log(JSON.stringify(prev));
            console.log(response.data.token);
            return {
                ...prev, 
                accessToken: response.data.token
            }
        })
        return response.data.email;
    }

    return refresh;
}

export default useRefreshToken;