import React from "react";
import { Link } from "react-router-dom";

const CreateTaskButton = () => {
  return (
    <React.Fragment>
      <Link to="/addTask" className="btn btn-lg btn-info">
        Create a Task
      </Link>
    </React.Fragment>
  );
};

export default CreateTaskButton;
