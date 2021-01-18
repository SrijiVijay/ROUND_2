import React from "react";
import { Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    borderRadius: "30px",
    display: "inline-block",
    fontSize: "14px",
    padding: "10px 20px",
    minWidth: "200px",
    // border: "none",
    textTransform: "capitalize",
    boxShadow: "none",
    "&:hover": {
      // background: "#006099",
      // boxShadow: "none",
    },
  },
  outlined: {
    "&:hover": {
      background: "inherit !important",
      // boxShadow: "none",
    },
  }
}));
export default function CustomButton(props) {
  const { disabled, onClick, variant = "contained", fullWidth, className, style } = props;
  const classes = useStyles();
  return (
    <Button
      className={className}
      color="primary"
      variant={variant}
      disabled={disabled}
      classes={{ root: `${classes.root} ${variant === "outlined" ? classes.outlined : ""}` }}
      onClick={onClick}
      style={{ width: fullWidth ? "100%" : "fit-content", ...style }}
    >
      {props.children}
    </Button>
  );
}
