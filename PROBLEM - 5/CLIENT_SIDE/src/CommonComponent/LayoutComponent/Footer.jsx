import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useState, useEffect } from "react";

const useStyles = makeStyles((theme) => ({
  root: {
    height: "24vh",
    [theme.breakpoints.up("md")]: {
      // height: "8vh",
      height: "80px",
    },
    background: "#f3f9fb",
    border: "1px solid hsla(0,0%,56%,.2)",
  },
  cointainer: {
    [theme.breakpoints.up("xs")]: {
      flexDirection: "column",
      textAlign: "center",
    },
    [theme.breakpoints.up("sm")]: {
      flexDirection: "column",
      textAlign: "center",
    },
    [theme.breakpoints.up("md")]: {
      flexDirection: "row",
      padding: "0 15px 0 15px!important",
      justifyContent: "space-between",
    },
    [theme.breakpoints.up("lg")]: {
      flexDirection: "row",
      padding: "0 15px 0 15px!important",
      justifyContent: "space-between",
    },
    justifyContent: "space-around",
    padding: "0 15px !important",
    color: "#909090",
    fontSize: "14px",
    fontWeight: 500,
  },
  link: {
    marginLeft: "8px",
    marginRight: "8px",
    color: theme.palette.primary.main,
    textDecoration: "none",
  },
  rootText: {
    [theme.breakpoints.down("xs")]: {
      width: "80%",
    },
  }
}));
export default function Footer(props) {
  const classes = useStyles();
  const { className } = props;
  const [currentYear, setCurrentyear] = useState('')

  useEffect(() => {
    const tz = new Date().getFullYear();
    setCurrentyear(tz)
  })

  return (
    <div className={`${classes.root}`}>
      <div className={`${className} ${classes.cointainer}`}>
        <React.Fragment>
          <div className={classes.rootText}>{`Copyright © ${currentYear} Health Insurance All Rights Reserved.`}</div>
          <div className="display-flex align-items-center">
            <a
              rel="noopener noreferrer"
              target="_blank"
              className={classes.link}

            >
              Terms & Conditions
            </a>
            <a
              rel="noopener noreferrer"
              target="_blank"
              className={classes.link}

            >
              Privacy Policy
            </a>
          </div>
        </React.Fragment>
      </div>
    </div>
  );
}
