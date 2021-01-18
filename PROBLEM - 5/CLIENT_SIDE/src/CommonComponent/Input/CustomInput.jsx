import React from "react";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import { makeStyles } from "@material-ui/core/styles";
import { TextField } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    marginLeft: 0,
    marginRight: 0,
    minWidth: 120,
  },
  inputRoot: {
    "&::placeholder": { textTransform: "none !important" },
    "&::-ms-input-placeholder": { textTransform: "none !important" },
    fontWeight: 700,
    color: "#313131",
    padding: "5px 0",
    fontSize: "16px",
    textTransform: "uppercase",
    lineHeight: 1.5,
  },
  inputRootEdit: {
    "&::placeholder": { textTransform: "none !important" },
    "&::-ms-input-placeholder": { textTransform: "none !important" },
    fontWeight: 700,
    color: "#313131",
    padding: "5px 0",
    fontSize: "17px",
    textTransform: "uppercase",
    lineHeight: 1.5,
  },
  label: {
    color: "#909090",
    fontSize: "18px",
    marginBottom: ".1em",
    width: "max-content",
  },
}));

const onPrevent = (e) => {
  e.preventDefault();
}

export default function CustomInput(props) {
  const classes = useStyles();
  const {
    name,
    label,
    placeholder,
    value,
    helperText,
    isError,
    onClick,
    onChange = () => { },
    onBlur = () => { },
    required,
    inputProps,
    InputProps,
    auto,
    defaultValue,
    onKeyDown,
    autoComplete,
    isVehicleEdit,
    id,
    style,
    isAddressField,
    isPassword
  } = props;
  return (
    <FormControl style={{ width: "100%" }} className={classes.formControl}>
      <InputLabel shrink id="customInput" classes={{ root: classes.label }}>
        {label}
      </InputLabel>
      <TextField
        onPaste={e => onPrevent(e)}
        onCopy={e => onPrevent(e)}
        onKeyDown={onKeyDown}
        name={name}
        defaultValue={defaultValue}
        value={value}
        onClick={onClick}
        onChange={onChange}
        style={{ width: "100%", marginTop: "15px", ...style }}
        labelId="customInput"
        id={id ? id : "customInput"}
        InputProps={{
          ...{
            classes: {
              root: isVehicleEdit ? classes.inputRootEdit : classes.inputRoot,
              input: isVehicleEdit ? classes.inputRootEdit : classes.inputRoot
            },
          },
          ...InputProps,
        }}
        placeholder={placeholder}
        helperText={helperText}
        onChange={(e) => onChange(e)}
        onBlur={(e) => onBlur(e)}
        required={required}
        inputProps={inputProps}
        error={isError}
        autoFocus={auto}
        // autoComplete="false" 
        autoComplete={isAddressField ? "chrome-off" : "off"}
        type={isPassword ? "password" : ""}
      />
    </FormControl>
  );
}
