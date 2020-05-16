import React, { Component } from "react";
import NoteTask from "./NoteTask";

class Backlog extends Component {
  render() {
    const { notes_task_prop } = this.props;
    const notes = notes_task_prop.map((note_task) => (
      <NoteTask key={note_task.id} note_task={note_task} />
    ));
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {notes}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {}
          </div>
        </div>
      </div>
    );
  }
}

export default Backlog;
