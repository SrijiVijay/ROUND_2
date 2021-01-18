import React, { useState, useContext } from "react";
import { makeStyles } from "@material-ui/core/styles";
import logo from '../../logo.svg';

const useStyles = makeStyles((theme) => ({
  root: {
    height: "10.5vh",
    [theme.breakpoints.up("md")]: {
      height: "75px",
    },
    background:
      "linear-gradient(90deg,#fff,#f2faff 50%,#e8fbff)",
    [theme.breakpoints.down("xs")]: {
      background: "#f3f9fb !important",
    },
  },
  cointainer: {
    [theme.breakpoints.down("sm")]: {
      display: "none !important",
    },
    [theme.breakpoints.down("xs")]: {
      display: "none !important",
    },
    justifyContent: "space-between",
    color: theme.palette.primary.main,
  },
  image: {
    cursor: "pointer",
    "& g": {
      fill: theme.palette.primary.main,
    },
    "& path": {
      fill: theme.palette.primary.main,
    },
  },
  iconCointainer: {
    [theme.breakpoints.down("xl")]: {
      display: "none !important",
    },
    [theme.breakpoints.down("lg")]: {
      display: "none !important",
    },
    [theme.breakpoints.down("md")]: {
      display: "none !important",
    },
    [theme.breakpoints.down("sm")]: {
      display: "flex !important",
    },
    [theme.breakpoints.down("xs")]: {
      display: "flex !important",
    },
    cursor: "pointer",
    justifyContent: "flex-start",
    color: theme.palette.primary.main,
  },
  pageHeading: {
    flex: 1,
    textAlign: "center",
  },
  progressBar: {
    height: "3px",
    margin: "0 15px",
  },
  companyName: {
    fontSize: "24px",
    fontWeight: 600,
  },
}));
export default function Header(props) {

  const classes = useStyles();

  const { className } = props;

  return (
    <div className={`${classes.root}`}>
      <div className={`${className} ${classes.iconCointainer}`}>
        {/* <BsFilterLeft size="3em" className="pointer-icon bs-filter-ham-icon" />


        <BsX size="2em" /> */}
      </div>
      {/* {window.location.pathname.search("thankyou") <= 0 &&
        window.location.pathname.search("start") <= 0
        && (
          <div className={classes.iconCointainer}>
            <div style={{ width: "100%" }}>
              <LinearProgress
                variant="determinate"
                color="primary"
                value={window.location.pathname.search("plan") > -1 ? 20
                  : window.location.pathname.search("custom") > -1 ? 40
                    : window.location.pathname.search("profile") > -1 ? 60
                      : window.location.pathname.search("summary") > -1 ? 80 : 0}
                classes={{ root: classes.progressBar }}
              />
            </div>
          </div>
        )} */}

      <div className={`${className} ${classes.cointainer}`}>
        <a href="/start" rel="noopener noreferrer">
          <img src={logo} alt="kurnia-logo" width={100} />
        </a>

        <div style={{ textAlign: "center", marginTop: "2%" }}></div>
        {/* <HeaderStepper /> */}
        <div className={`display-flex align-items-flex-end`}>

          <NeedAssistance />
        </div>
      </div>



    </div>
  );
}
const NeedAssistance = (props) => {
  const classes = useStyles();
  const { handleNeedAssitanceOpen } = props;
  return (
    <div
      className={`${classes.image} ${classes.companyName} display-flex align-items-flex-end`}
    >
      Health Insurance
    </div>
  );
};

