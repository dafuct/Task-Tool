import { GET_BACKLOG, GET_NOTE_TASK, DELETE_NOTE_TASK } from "../actions/types";

const initialState = {
  note_tasks: [],
  note_task: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_BACKLOG:
      return {
        ...state,
        note_tasks: action.payload,
      };

    case GET_NOTE_TASK:
      return {
        ...state,
        note_task: action.payload,
      };

    case DELETE_NOTE_TASK:
      return {
        ...state,
      };
    default:
      return state;
  }
}
