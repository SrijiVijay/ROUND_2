import React from "react";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { FormHelperText } from "@material-ui/core";
const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    marginLeft: 0,
    marginRight: 0,
    minWidth: 100,
  },
  label: {
    color: "#909090",
    fontSize: "18px",
    marginBottom: ".1em",
  },
  select: {
    "&:focus": {
      background: "none",
    },
    fontWeight: 600,
  },
  selectCoverage: {
    fontSize: "17.5px",
    fontWeight: 700,
    "&:focus": {
      background: "none",
    },
  },
  inputRoot: {
    marginTop: "9px",
    textAlign: "left",
  },
}));
export default function CustomSelect(props) {
  const {
    name,
    label,
    value,
    onChange,
    options,
    placeholder,
    disabled,
    disableUnderline,
    isError,
    helperText,
    staicYear,
    defaultValue,
    classes: propsClasses,
    greyPlaceholder,
    MenuProps,
    isCoverage,
    margin,
  } = props;
  const classes = useStyles();
  return (
    <FormControl
      // className={classes.formControl}
      margin={margin}
      style={{
        width: "100%",
        margin: margin === "dense" ? 0 : "8px",
        marginLeft: 0,
        marginRight: 0,
        minWidth: 100,
      }}
    >
      {label && (
        <InputLabel shrink classes={{ root: classes.label }}>
          {label}
        </InputLabel>
      )}
      <Select
        inputProps={{
          classes: {
            select: classes.select,
            root: classes.inputRoot
          },
        }}
        classes={propsClasses}
        name={name}
        disableUnderline={disableUnderline}
        disabled={disabled}
        className={classes.selectEmpty}
        value={value}
        onChange={onChange}
        error={isError}
        displayEmpty
        IconComponent={(props) => <ExpandMoreIcon fontSize="small" className={props.className} />}
        MenuProps={{
          ...{
            PaperProps: {
              style: {
                maxHeight: 40 * 4.5,
                width: "20ch",
              },
            },
            disableScrollLock: true,
            anchorOrigin: {
              vertical: "bottom",
              horizontal: "center",
            },
            transformOrigin: {
              vertical: "top",
              horizontal: "center",
            },
            getContentAnchorEl: null,
          },
          ...MenuProps,
        }}
        renderValue={
          value !== ""
            ? undefined
            : () => <Placeholder greyPlaceholder={greyPlaceholder}>{placeholder}</Placeholder>
        }
        labelId="Vehicle Reg No"
        id="Vehicle Reg No"
      >
        {staicYear ? <MenuItem value={12}>1 YEAR</MenuItem> : null}
        {options}
      </Select>
      <FormHelperText error={isError}>{helperText}</FormHelperText>
    </FormControl>
  );
}

const usePlaceholderStyles = makeStyles(() => ({
  placeholder: {
    color: "#313131",
    fontWeight: "700",
  },
}));
const Placeholder = ({ children, greyPlaceholder }) => {
  const classes = usePlaceholderStyles();
  return (
    <div className={classes.placeholder} style={{ color: greyPlaceholder ? "#A1A5A6" : "#313131" }}>
      {children}
    </div>
  );
};
