import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";
class Landing extends Component {
  componentDidMount() {
    if (this.props.security.validToken) {
      this.props.history.push("/dashboard");
    }
  }

  render() {
    return (
      <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4">
                  Личный Инструмент для Управления Целями
                </h1>
                <p className="lead">
                  Создайте свой аккаунт, чтобы присоединиться к активным целям
                  или начать новую цель
                </p>
                <hr />
                <Link className="btn btn-lg btn-dark mr-2" to="/register">
                  Регистрация
                </Link>
                <Link className="btn btn-lg btn-secondary mr-2" to="/login">
                  Логин
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Landing.propTypes = {
  security: PropTypes.object.isRequired,
};

const mapStateProps = (state) => ({
  security: state.security,
});

export default connect(mapStateProps)(Landing);
