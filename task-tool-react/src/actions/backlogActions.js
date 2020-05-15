import axios from "axios";

export const addNoteTask = (backlog_id, note_task, history) => async (
  dispatch
) => {
  await axios.post(`/api/backlog/${backlog_id}`, note_task);
  history.push(`/taskBoard/${backlog_id}`);
};
