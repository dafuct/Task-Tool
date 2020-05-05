import axios from "axios";
import { GET_ERRORS, GET_TASKS } from "./types";

export const createTask = (task, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/task", task);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getTask = () => async (dispatch) => {
  const res = await axios.get("http://localhost:8080/api/task/all");
  dispatch({
    type: GET_TASKS,
    payload: res.data,
  });
};
