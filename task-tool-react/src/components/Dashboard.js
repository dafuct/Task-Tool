import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";
import CreateTaskButton from "./Project/CreateTaskButton";

class Dashboard extends Component {
  render() {
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Tasks</h1>
              <br />
              <CreateTaskButton />
              <br />
              <hr />
              <ProjectItem />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Dashboard;
