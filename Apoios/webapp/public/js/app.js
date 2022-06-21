'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Utilizadores = function (_React$Component) {
  _inherits(Utilizadores, _React$Component);

  function Utilizadores() {
    _classCallCheck(this, Utilizadores);

    return _possibleConstructorReturn(this, (Utilizadores.__proto__ || Object.getPrototypeOf(Utilizadores)).apply(this, arguments));
  }

  _createClass(Utilizadores, [{
    key: 'render',
    value: function render() {
      return React.createElement(
        'table',
        null,
        React.createElement(
          'thead',
          null,
          React.createElement(
            'tr',
            null,
            React.createElement(
              'th',
              null,
              'Nome Utilizador'
            ),
            React.createElement(
              'th',
              null,
              'Nome Completo'
            )
          )
        ),
        React.createElement(
          'tbody',
          null,
          this.props.utilizadores && this.props.utilizadores.map(function (utilizador) {
            return React.createElement(
              'tr',
              null,
              React.createElement(
                'td',
                null,
                utilizador.nomeUtilizador
              ),
              React.createElement(
                'td',
                null,
                utilizador.nomeCompleto
              )
            );
          })
        )
      );
    }
  }]);

  return Utilizadores;
}(React.Component);

var App = function (_React$Component2) {
  _inherits(App, _React$Component2);

  function App(props) {
    _classCallCheck(this, App);

    var _this2 = _possibleConstructorReturn(this, (App.__proto__ || Object.getPrototypeOf(App)).call(this, props));

    _this2.state = {
      utilizadores: [],
      nomeUtilizador: '',
      nomeCompleto: ''
    };

    _this2.create = _this2.create.bind(_this2);
    _this2.update = _this2.update.bind(_this2);
    _this2.delete = _this2.delete.bind(_this2);
    _this2.handleChange = _this2.handleChange.bind(_this2);
    return _this2;
  }

  _createClass(App, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var _this3 = this;

      // get all entities - GET
      fetch("https://roombookerapi.azurewebsites.net/api/utilizadores", {
        "method": "GET",
        "headers": {
          "Accept": "application/json",
          "Access-Control-Allow-Origin": "true"
        }
      }).then(function (response) {
        return response.json();
      }).then(function (response) {
        _this3.setState({
          utilizadores: response
        });
      }).catch(function (err) {
        console.log(err);
      });
    }
  }, {
    key: 'create',
    value: function create(e) {
      // add entity - POST
      e.preventDefault();
    }
  }, {
    key: 'update',
    value: function update(e) {
      // update entity - PUT
      e.preventDefault();
    }
  }, {
    key: 'delete',
    value: function _delete(e) {
      // delete entity - DELETE
      e.preventDefault();
    }
  }, {
    key: 'handleChange',
    value: function handleChange(changeObject) {
      this.setState(changeObject);
    }
  }, {
    key: 'render',
    value: function render() {
      var _this4 = this;

      return React.createElement(
        'div',
        { className: 'container' },
        React.createElement(
          'div',
          { className: 'row justify-content-center' },
          React.createElement(
            'div',
            { className: 'col-md-8' },
            React.createElement(
              'h1',
              { className: 'display-4 text-center' },
              'Make An API Call in React'
            ),
            React.createElement(
              'form',
              { className: 'd-flex flex-column' },
              React.createElement(
                'legend',
                { className: 'text-center' },
                'Add-Update-Delete User'
              ),
              React.createElement(
                'label',
                { htmlFor: 'name' },
                'Nome Utilizador:',
                React.createElement('input', {
                  name: 'name',
                  id: 'name',
                  type: 'text',
                  className: 'form-control',
                  value: this.state.nomeutilizador,
                  onChange: function onChange(e) {
                    return _this4.handleChange({ nomeutilizador: e.target.value });
                  },
                  required: true
                })
              ),
              React.createElement(
                'button',
                { className: 'btn btn-primary', type: 'button', onClick: function onClick(e) {
                    return _this4.create(e);
                  } },
                'Add'
              ),
              React.createElement(
                'button',
                { className: 'btn btn-info', type: 'button', onClick: function onClick(e) {
                    return _this4.update(e);
                  } },
                'Update'
              ),
              React.createElement(
                'button',
                { className: 'btn btn-danger', type: 'button', onClick: function onClick(e) {
                    return _this4.delete(e);
                  } },
                'Delete'
              )
            ),
            React.createElement(Utilizadores, { utilizadores: this.state.utilizadores })
          )
        )
      );
    }
  }]);

  return App;
}(React.Component);

var domContainer = document.querySelector('#App');
ReactDOM.render(React.createElement(App, null), domContainer);