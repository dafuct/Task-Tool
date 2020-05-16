import React, { Component } from "react";

class NoteTask extends Component {
  render() {
    const { note_task } = this.props;
    return (
      <div className="card mb-1 bg-light">
        <div className="card-header text-primary">
          ID: {note_task.taskSequence} -- Priority: {note_task.priority}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{note_task.summary}</h5>
          <p className="card-text text-truncate ">
            {note_task.acceptanceCriteria}
          </p>
          <a href="" className="btn btn-primary">
            View / Update
          </a>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}

export default NoteTask;
