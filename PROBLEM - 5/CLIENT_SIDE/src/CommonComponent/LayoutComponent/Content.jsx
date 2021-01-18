import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Redirect, Route, Router, Switch } from "react-router-dom";
import Home from '../../Component/Home/Home.jsx';
import Error from "../../Component/Error/Error.jsx";
import PaymentPage from "../../Component/Payment/PaymentPage.jsx";
import SummaryPage from "../../Component/Summary/SummaryPage.jsx";
import Login from "../../Component/Login/Login.jsx";
import Register from "../../Component/Register/Register.jsx";
import HomeBot from "../../Component/HomeBot/HomeBot.jsx";

const useStyles = makeStyles((theme) => ({
  root: {
    minHeight: "calc(100vh - 34.5vh)",
    [theme.breakpoints.up("md")]: {
      // minHeight: "calc(100vh - 18.5vh)",
      minHeight: "calc(100vh - 195px)",
    },
    // background: "#f3f9fb",
    padding: "40px 0",
  },
  contentRoot: {
    display: "flex !important",
    alignItems: "flex-start !important",
    flexWrap: "nowrap !important",
  },
}));
export default function Content(props) {
  const classes = useStyles();
  const { className } = props;
  return (

    <div className={`${classes.root}`}>
      <div className={`${className} ${classes.contentRoot}`}>

        <Switch>
          <Route exact path={"/start"} component={Home} />
          <Route exact path={"/payment"} component={PaymentPage} />
          <Route exact path={"/summary"} component={SummaryPage} />

          <Route exact path={"/homebot"} component={HomeBot} />
          <Route exact path={"/login"} component={Login} />
          <Route exact path={"/register"} component={Register} />
          <Route exact path="/error" component={Error} />
          <Redirect to="/start" />
        </Switch>
      </div>
    </div>
  );
}
