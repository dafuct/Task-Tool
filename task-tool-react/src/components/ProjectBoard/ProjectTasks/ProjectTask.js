import React, { Component } from "react";
import { Link } from "react-router-dom";
import { deleteProjectTask } from "../../../actions/backlogActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class ProjectTask extends Component {
  onDeleteClick(backlog_id, pt_id, pt_summary) {
    this.props.deleteProjectTask(backlog_id, pt_id, pt_summary);
  }
  render() {
    const { project_task } = this.props;
    let priorityString;
    let priorityClass;

    if (project_task.priority === 1) {
      priorityClass = "bg-danger text-light";
      priorityString = "Высокий";
    }

    if (project_task.priority === 2) {
      priorityClass = "bg-warning text-light";
      priorityString = "Средний";
    }

    if (project_task.priority === 3) {
      priorityClass = "bg-info text-light";
      priorityString = "Низкий";
    }

    return (
      <div className="card mb-1 bord">
        <div className={`card-header text-primary ${priorityClass}`}>
          Приоритет: {priorityString}
        </div>
        <div className="card-body bg-secondary">
          <h5 className="card-title">{project_task.summary}</h5>
          <p className="card-text text-truncate ">
            {project_task.acceptanceCriteria}
          </p>
          <Link
            to={`/updateProjectTask/${project_task.projectIdentifier}/${project_task.projectSequence}`}
            className="btn btn-primary"
          >
            Обновить
          </Link>

          <button
            className="btn btn-danger ml-4"
            onClick={this.onDeleteClick.bind(
              this,
              project_task.projectIdentifier,
              project_task.projectSequence,
              project_task.summary
            )}
          >
            Удалить
          </button>
        </div>
      </div>
    );
  }
}

ProjectTask.propTypes = {
  deleteProjectTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);
