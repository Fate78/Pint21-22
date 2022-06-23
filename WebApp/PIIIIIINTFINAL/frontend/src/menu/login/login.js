import React from 'react';
import axios from 'axios';
import { Link } from "react-router-dom"
import '../../assets/bootstrap/bootstrap/css/bootstrap.css';
import '../../assets/CSS/stylesdashboard1.css';
import '../../assets/CSS/styleslogin.css';

class Pagina extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    };

  }

  componentDidMount() {
    // get all entities - GET
    fetch("https://roombookerapi.azurewebsites.net/api/utilizadores", {
        "method": "GET",
        "headers": {
            "Accept": "application/json",
            "Access-Control-Allow-Origin" : "true"
        }
    })
    .then(response => response.json())
    .then(response => {
        this.setState({
        utilizadores: response
        })
    })
    .catch(err => { console.log(err); 
    });

}

  render() {
    return (
  
            <div class="Section_top">
             <div class=" center">
                 <h1>Login</h1>
                 <form method="post">
                     <div class="txt_field">
                         <input type="text" required />
                         <label>Username</label> 
                     </div>
                     <div class="txt_field">
                         <input type="password" required />
                         <label>Password</label>
                     </div>
                     <div class= "pass">Esqueceu a Password? <a href="#">Recupere aqui</a></div>
                      <a href="dashboard.html"><input type="submit" value="Login" /></a>
                     <div class="signup_link">
                         Faça Login ou registe-se <a href="registar.html">aqui</a>
                     </div>
                </form>
             </div>
            </div>

    );
  }

  loadFillData() {
    return this.state.utilizadores.map((data, index) =>{
        return(
            <tr key={index}>
                
                
                    
            </tr>
        )
    })
}
}
export default Pagina;
