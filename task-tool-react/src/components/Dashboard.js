import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";
import CreateTaskButton from "./Project/CreateTaskButton";
import { connect } from "react-redux";
import { getTask } from "../actions/taskActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getTask();
  }

  render() {
    const { tasks } = this.props.task;

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
              {tasks.map((task) => (
                <ProjectItem key={task.id} task={task} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  getTask: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  task: state.task,
});

export default connect(mapStateToProps, { getTask })(Dashboard);
