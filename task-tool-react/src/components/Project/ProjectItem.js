import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteProject } from "../../actions/projectActions";

class ProjectItem extends Component {
  onDeleteClick = (id) => {
    this.props.deleteProject(id);
  };

  render() {
    const { project } = this.props;
    return (
      <div className="container">
        <div className="card card-body mb-3" id="card">
          <div className="row justify-content-between">
            <div className="col-lg-8 col-md-4 col-8">
              <span className="mx-auto">{project.projectName}</span>
              <hr />
              <p>{project.description}</p>
            </div>
            <div className="col-md-3 d-none d-lg-block">
              <ul className="list-group">
                <Link to={`/projectBoard/${project.projectIdentifier}`}>
                  <li className="list-group-item board">
                    <i className="fas fa-tasks"></i>
                    <i> Доска задач</i>
                  </li>
                </Link>
                <Link to={`/updateProject/${project.projectIdentifier}`}>
                  <li className="list-group-item update">
                    <i className="far fa-edit pr-1"></i>
                    <i> Обновить цель</i>
                  </li>
                </Link>
                <li
                  className="list-group-item delete"
                  onClick={this.onDeleteClick.bind(
                    this,
                    project.projectIdentifier
                  )}
                >
                  <i className="fas fa-trash-alt"> </i>
                  <i> Удалить цель</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

ProjectItem.propTypes = {
  deleteProject: PropTypes.func.isRequired,
};

export default connect(null, { deleteProject })(ProjectItem);
