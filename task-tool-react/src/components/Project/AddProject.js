import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";
import classnames from "classnames";

class AddProject extends Component {
  constructor() {
    super();

    this.state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      start_date: "",
      end_date: "",
      errors: {},
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  //life cycle hooks
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newProject = {
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      start_date: this.state.start_date,
      end_date: this.state.end_date,
    };
    this.props.createProject(newProject, this.props.history);
  }

  render() {
    const { errors } = this.state;

    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row justify-content-center">
              <div className="col-md-8 mt-4">
                <h5 className="display-4 text-center">Создание цели</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.projectName,
                      })}
                      placeholder="Название цели"
                      name="projectName"
                      value={this.state.projectName}
                      onChange={this.onChange}
                    />
                    {errors.projectName && (
                      <div className="p-1 mb-1 bg-danger rounded-sm text-white">
                        {errors.projectName}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.projectIdentifier,
                      })}
                      placeholder="Идентификатор цели"
                      name="projectIdentifier"
                      value={this.state.projectIdentifier}
                      onChange={this.onChange}
                    />
                    {errors.projectIdentifier && (
                      <div className="p-1 mb-1 bg-danger rounded-sm text-white">
                        {errors.projectIdentifier}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.description,
                      })}
                      placeholder="Описание цели"
                      name="description"
                      value={this.state.description}
                      onChange={this.onChange}
                    />
                    {errors.description && (
                      <div className="p-1 mb-1 bg-danger rounded-sm text-white">
                        {errors.description}
                      </div>
                    )}
                  </div>
                  <h6>Начальная дата</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="start_date"
                      value={this.state.start_date}
                      onChange={this.onChange}
                    />
                  </div>
                  <h6>Предполагаемая дата окончания</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="end_date"
                      value={this.state.end_date}
                      onChange={this.onChange}
                    />
                  </div>

                  <input
                    type="submit"
                    className="btn btn-dark btn-block mt-4"
                    value="Создать"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

AddProject.propTypes = {
  createProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});

export default connect(mapStateToProps, { createProject })(AddProject);
