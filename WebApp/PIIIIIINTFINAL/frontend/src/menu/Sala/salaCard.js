import { Link } from "react-router-dom"

export default function SalaCard(props) {
    console.log(props)
    return (
                        
                            
        <div className='col'>
            <Link to={`/sala/${props.idSala}`} style={{ textDecoration: "none", color: "black" }}>

                <div className='card h-100'>
                    <div className='card-body'>

                        <div className='card-title'>

                            <p>Sala {props.nSala}</p>

                        </div>
                        <hr />
                        <p>Lotação máxima: {props.lotacaoMax}</p>
                        <p>Tempo de Limpeza: {props.tempoMinLimp}</p>
                        <p>Encontra-se {props.limpo ? "limpo" : "por limpar"}</p>
                        <p>Está {props.ativo ? "ativo" : "inativo"}</p>
                    </div>
                </div>

            </Link>

        </div>
    
            
    )
}