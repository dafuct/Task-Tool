import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddTask from "./components/Project/AddTask";
import { Provider } from "react-redux";
import store from "./store";
import UpdateTask from "./components/Project/UpdateTask";
import TaskBoard from "./components/TaskBoard/TaskBoard";
import AddNoteTask from "./components/TaskBoard/AddNoteTask";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addTask" component={AddTask} />
          <Route exact path="/updateTask/:id" component={UpdateTask} />
          <Route exact path="/taskBoard/:id" component={TaskBoard} />
          <Route exact path="/addNoteTask/:id" component={AddNoteTask} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
