import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";

class TaskBoard extends Component {
  render() {
    const { id } = this.props.match.params;
    return (
      <div className="container">
        <Link to={`/addNoteTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Note </i>
        </Link>
        <br />
        <hr />
        <Backlog />
      </div>
    );
  }
}

export default TaskBoard;
