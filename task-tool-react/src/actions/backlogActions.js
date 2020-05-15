import axios from "axios";
import { GET_ERRORS } from "./types";

export const addNoteTask = (backlog_id, note_task, history) => async (
  dispatch
) => {
  try {
    await axios.post(`/api/backlog/${backlog_id}`, note_task);
    history.push(`/taskBoard/${backlog_id}`);
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
