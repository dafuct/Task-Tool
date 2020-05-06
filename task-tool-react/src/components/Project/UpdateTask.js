import React, { Component } from "react";
import { getTask } from "../../actions/taskActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";

class UpdateTask extends Component {
  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getTask(id, this.props.history);
  }

  render() {
    return (
      <div className="project">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Update Task form</h5>
              <hr />
              <form>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg "
                    placeholder="Task Name"
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Unique Task ID"
                    disabled
                  />
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Task Description"
                  />
                </div>
                <h6>Start Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="start_date"
                  />
                </div>
                <h6>Estimated End Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="end_date"
                  />
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateTask.propTypes = {
  getTask: PropTypes.func.isRequired,
  task: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  task: state.task.task,
});

export default connect(mapStateToProps, { getTask })(UpdateTask);
