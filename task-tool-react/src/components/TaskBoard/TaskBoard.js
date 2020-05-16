import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBacklog } from "../../actions/backlogActions";

class TaskBoard extends Component {
  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }

  render() {
    const { id } = this.props.match.params;
    const { notes_task } = this.props.backlog;
    return (
      <div className="container">
        <Link to={`/addNoteTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Note </i>
        </Link>
        <br />
        <hr />
        <Backlog notes_task_prop={notes_task} />
      </div>
    );
  }
}

TaskBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
});

export default connect(mapStateToProps, { getBacklog })(TaskBoard);
