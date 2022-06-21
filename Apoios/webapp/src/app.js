'use strict';
class Utilizadores extends React.Component {
    render() {
        return (
            <table>
                <thead>
                    <tr>
                        <th>Nome Utilizador</th>
                        <th>Nome Completo</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.utilizadores && this.props.utilizadores.map(utilizador => {
                        return <tr>
                            <td>{utilizador.nomeUtilizador}</td>
                            <td>{utilizador.nomeCompleto}</td>
                        </tr>
                    })}
                </tbody>
            </table>
        );
    }
}
class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      utilizadores: [],
      nomeUtilizador: '',
      nomeCompleto: '',
    };

    this.create = this.create.bind(this);
    this.update = this.update.bind(this);
    this.delete = this.delete.bind(this);
    this.handleChange = this.handleChange.bind(this);
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

  create(e) {
    // add entity - POST
    e.preventDefault();

  }

  update(e) {
    // update entity - PUT
    e.preventDefault();

  }

  delete(e) {
    // delete entity - DELETE
    e.preventDefault();

  }

  handleChange(changeObject) {
    this.setState(changeObject)
  }

  render() {
    return (
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-md-8">
              <h1 className="display-4 text-center">Make An API Call in React</h1>
              <form className="d-flex flex-column">
                <legend className="text-center">Add-Update-Delete User</legend>
                <label htmlFor="name">
                  Nome Utilizador:
                  <input
                    name="name"
                    id="name"
                    type="text"
                    className="form-control"
                    value={this.state.nomeutilizador}
                    onChange={(e) => this.handleChange({ nomeutilizador: e.target.value })}
                    required
                    />
                </label>
                <button className="btn btn-primary" type='button' onClick={(e) => this.create(e)}>
                  Add
                </button>
                <button className="btn btn-info" type='button' onClick={(e) => this.update(e)}>
                    Update
                </button>
                <button className="btn btn-danger" type='button' onClick={(e) => this.delete(e)}>
                    Delete
                </button>
              </form>
              <Utilizadores utilizadores={this.state.utilizadores} />
            </div>
          </div>
        </div>
    );
  }
}

let domContainer = document.querySelector('#App');
ReactDOM.render(<App />, domContainer);