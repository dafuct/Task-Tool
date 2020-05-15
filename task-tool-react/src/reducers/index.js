import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import taskReducer from "./taskReducer";
import backlogReducer from "./backlogReducer";

export default combineReducers({
  errors: errorReducer,
  task: taskReducer,
  backlog: backlogReducer,
});
