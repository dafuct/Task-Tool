import axios from "axios";
import { GET_ERRORS, GET_TASKS, GET_TASK, DELETE_TASK } from "./types";

export const createTask = (task, history) => async (dispatch) => {
  try {
    const res = await axios.post("/api/task", task);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getTasks = () => async (dispatch) => {
  const res = await axios.get("/api/task/all");
  dispatch({
    type: GET_TASKS,
    payload: res.data,
  });
};

export const getTask = (id, history) => async (dispatch) => {
  try {
    const res = await axios.get(`/api/task/${id}`);
    dispatch({
      type: GET_TASK,
      payload: res.data,
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

export const deleteTask = (id) => async (dispatch) => {
  if (
    window.confirm(
      "Are you sure? This will delete the task and all data related to it"
    )
  ) {
    await axios.delete(`/api/task/${id}`);
    dispatch({
      type: DELETE_TASK,
      payload: id,
    });
  }
};
