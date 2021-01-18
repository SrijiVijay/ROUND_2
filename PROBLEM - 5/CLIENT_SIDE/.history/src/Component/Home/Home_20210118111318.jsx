import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import clsx from 'clsx';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Check from '@material-ui/icons/Check';
import PermIdentityIcon from '@material-ui/icons/PermIdentity';
import FavoriteIcon from '@material-ui/icons/Favorite';
import VideoLabelIcon from '@material-ui/icons/VideoLabel';
import StepConnector from '@material-ui/core/StepConnector';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import CustomInput from '../../CommonComponent/Input/CustomInput.jsx';
import { Grid, MenuItem, Paper } from '@material-ui/core';
import CustomButton from '../../CommonComponent/Button/CustomButton.jsx';
import CustomSelect from '../../CommonComponent/Select/CustomSelect.jsx';
import { useState } from "react";
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import Loader from '../../CommonComponent/Loader/Loader.jsx';
const CryptoJS = require("crypto-js");
const paymentSecretKey = 'run4Fun';
const emailRegx = new RegExp(
    /^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i
);



const ColorlibConnector = withStyles({
    alternativeLabel: {
        top: 22,
    },
    active: {
        '& $line': {
            backgroundImage:
                'linear-gradient( 95deg,rgb(242,113,33) 0%,rgb(233,64,87) 50%,rgb(138,35,135) 100%)',
        },
    },
    completed: {
        '& $line': {
            backgroundImage:
                'linear-gradient( 95deg,rgb(242,113,33) 0%,rgb(233,64,87) 50%,rgb(138,35,135) 100%)',
        },
    },
    line: {
        height: 3,
        border: 0,
        backgroundColor: '#eaeaf0',
        borderRadius: 1,
    },
})(StepConnector);

const useColorlibStepIconStyles = makeStyles({
    root: {
        backgroundColor: '#ccc',
        zIndex: 1,
        color: '#fff',
        width: 50,
        height: 50,
        display: 'flex',
        borderRadius: '50%',
        justifyContent: 'center',
        alignItems: 'center',
    },
    active: {
        backgroundImage:
            'linear-gradient( 136deg, rgb(242,113,33) 0%, rgb(233,64,87) 50%, rgb(138,35,135) 100%)',
        boxShadow: '0 4px 10px 0 rgba(0,0,0,.25)',
    },
    completed: {
        backgroundImage:
            'linear-gradient( 136deg, rgb(242,113,33) 0%, rgb(233,64,87) 50%, rgb(138,35,135) 100%)',
    },
});

function ColorlibStepIcon(props) {
    const classes = useColorlibStepIconStyles();
    const { active, completed } = props;

    const icons = {
        1: <PermIdentityIcon />,
        2: <FavoriteIcon />,
        3: <VideoLabelIcon />,
    };

    return (
        <div
            className={clsx(classes.root, {
                [classes.active]: active,
                [classes.completed]: completed,
            })}
        >
            {icons[String(props.icon)]}
        </div>
    );
}

ColorlibStepIcon.propTypes = {
    /**
     * Whether this step is active.
     */
    active: PropTypes.bool,
    /**
     * Mark the step as completed. Is passed to child components.
     */
    completed: PropTypes.bool,
    /**
     * The label displayed in the step icon.
     */
    icon: PropTypes.node,
};

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        boxShadow: "0px 6px 6px -3px rgba(0,0,0,0.2), 0px 10px 14px 1px rgba(0,0,0,0.14), 0px 4px 18px 3px rgba(0,0,0,0.12)",
        borderRadius: "15px",
    },
    button: {
        marginRight: theme.spacing(1),
    },
    instructions: {
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(1),
    },
    paperRoot: {
        // borderRadius: "15px",
        padding: "10px 30px 20px",
        marginBottom: "20px",
    },
    stepRoot: {
        borderRadius: "15px",
    },
    heading: {
        marginBottom: "20px",
        marginTop: 0,
        fontSize: "24px",
        padding: " 0 120px ",
        color: "#3f51b5",
        // color: theme.palette.primary.main,
    },
    selectPaper: {
        border: "1px solid #909090",
        marginTop: "8px",
        boxShadow: "none",
        maxHeight: "205px !important",
    },
    selectList: {
        "& li": {
            padding: "10px",
            fontSize: "17px",
        },
        // "&$selected ": {
        //   background: theme.palette.primary.light,
        // },
        // "& hover": {
        //   background: theme.palette.primary.light,
        // },
    },
    footerCont: {
        marginTop: "30px"
    },
    menuItemRoot: {
        "&$selected": {
            background: `#3f51b5b3 !important`,
        },
        "&$selected:hover": {
            background: `#3f51b554 !important`,
        },
        "&:hover": {
            background: `#3f51b554 !important`,
        },
    },
    menuSelected: { background: "#3f51b5b3 !important" },
}));

function getSteps() {
    return ['Personal Information', 'Your Health Condition', 'Your Habits'];
}

function getHealthConditions() {
    return [
        {
            id: "hyperTension",
            name: "Hypertension"
        },
        {
            id: "bloodSugar",
            name: "Blood Sugar"
        },
        {
            id: "overWeight",
            name: "Overweight"
        },
        {
            id: "bloodPressure",
            name: "Blood Pressure"
        },
    ];
}

function getHabits() {
    return [
        {
            id: "smoking",
            name: "Smoking"
        },
        {
            id: "alcohol",
            name: "Alcohol"
        },
        {
            id: "drugs",
            name: "Drugs"
        },
        {
            id: "dailyExercise",
            name: "Daily Exercise"
        },
    ];
}

const genderData = [
    {
        "id": 1,
        "name": "Male"
    },
    {
        "id": 2,
        "name": "Female"
    }
]

const selectionData = [
    {
        "id": 1,
        "selection": "Yes"
    },
    {
        "id": 2,
        "selection": "No"
    },
]

export default function Home() {
    const classes = useStyles();
    const [activeStep, setActiveStep] = useState(0);
    const steps = getSteps();
    const healthConditions = getHealthConditions();
    const habits = getHabits();
    const history = useHistory();
    const [loader, setLoader] = useState(false);
    const [nextDisbled, setNextDisabled] = useState(true);

    const formInitialValue = {
        name: "",
        email: "",
        gender: "",
        age: "",
        hyperTension: "",
        bloodSugar: "",
        overWeight: "",
        bloodPressure: "",
        smoking: "",
        alcohol: "",
        drugs: "",
        dailyExercise: "",
    };

    const [formData, setFormData] = useState(formInitialValue);
    const numRegx = new RegExp("^[0-9]+$");

    useEffect(() => {

        nextValidation();

    }, [formData, activeStep])

    const nextValidation = () => {
        if (activeStep == 0 && formData.name.trim() !== "" && formData.gender !== "" && formData.age.trim() !== "" && emailRegx.test(formData.email)) {
            setNextDisabled(false);
        } else if (activeStep == 1 && formData.hyperTension !== "" && formData.bloodSugar !== "" && formData.overWeight !== "" && formData.bloodPressure !== "") {
            setNextDisabled(false)
        } else if (activeStep == 2 && formData.smoking !== "" && formData.alcohol !== "" && formData.drugs !== "" && formData.dailyExercise !== "") {
            setNextDisabled(false);
        } else {
            setNextDisabled(true)
        }
    }

    // const checkValidity = (name) => {

    //     if (name == "name") {

    //     }
    // }

    const handleOnChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;

        if (name === "age") {
            if (!(!value.length || numRegx.test(value))) {
                return;
            }
        }

        setFormData((values) => ({
            ...values,
            [name]: value,
        }));

    }

    const handleNext = () => {
        switch (activeStep) {
            case 0:
                if (formData.name.trim() !== "" && formData.gender !== "" && formData.age.trim() !== "" && emailRegx.test(formData.email)) {
                    setActiveStep((prevActiveStep) => prevActiveStep + 1);
                }
                break;
            case 1:
                if (formData.hyperTension !== "" && formData.bloodSugar !== "" && formData.overWeight !== "" && formData.bloodPressure !== "") {
                    setActiveStep((prevActiveStep) => prevActiveStep + 1);
                }
                break;
            case 2:
                if (formData.smoking !== "" && formData.alcohol !== "" && formData.drugs !== "" && formData.dailyExercise !== "") {
                    setActiveStep((prevActiveStep) => prevActiveStep + 1);
                }
                break;

            default:
                break;
        }

    };

    const handleFinish = () => {

        setLoader(true)

        let EncryptedObj = CryptoJS.AES.encrypt(JSON.stringify(formData), paymentSecretKey).toString();
        sessionStorage.setItem("data", EncryptedObj);

        axios.post('http://localhost:8085/premiumservice/calculatePremium', formData).then(res => {
            console.log(res)
            let EncryptedRes = CryptoJS.AES.encrypt(JSON.stringify(res), paymentSecretKey).toString();
            sessionStorage.setItem("dataPrm", EncryptedRes);
            setLoader(false)
            let path = `summary`;
            history.push(path);
        })
            .catch(() => {
                let path = `error`;
                history.push(path);
            });



    }

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    return (
        <div className={classes.root}>

            <Loader loader={loader} />

            <Stepper classes={{ root: classes.stepRoot }} alternativeLabel activeStep={activeStep} connector={<ColorlibConnector />}>
                {steps.map((label) => (
                    <Step key={label}>
                        <StepLabel StepIconComponent={ColorlibStepIcon}>{label}</StepLabel>
                    </Step>
                ))}
            </Stepper>
            <div>

                <div>
                    <Grid container>
                        <Grid item xs={2}></Grid>
                        <Grid item xs={8} >
                            <Paper classes={{ root: classes.paperRoot }} elevation={0}>

                                <h3 className={`${classes.heading} form-title`}>
                                    {`Check your health insurance premium within a minute!`}
                                </h3>

                                {activeStep == 0 && (
                                    <Grid container>
                                        <Grid item xs={6}>
                                            <div style={{ padding: "10px" }}>

                                                <CustomInput
                                                    name="name"
                                                    label="Your Name"
                                                    placeholder="Name"

                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    // inputProps={{ maxLength: 15 }}
                                                    value={formData.name}
                                                />
                                            </div>
                                        </Grid>
                                        <Grid item xs={6}>
                                            <div style={{ padding: "10px" }}>

                                                <CustomInput
                                                    name="email"
                                                    label="Your Email"
                                                    placeholder="Email"
                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    // inputProps={{ maxLength: 15 }}
                                                    value={formData.email}
                                                />
                                            </div>
                                        </Grid>
                                        <Grid item xs={6}>
                                            <div style={{ padding: "10px" }}>
                                                <CustomSelect
                                                    options={genderData.map((item) => (
                                                        <MenuItem
                                                            classes={{ root: classes.menuItemRoot, selected: classes.menuSelected }}
                                                            value={item.name}
                                                        >
                                                            {item.name}
                                                        </MenuItem>
                                                    ))}
                                                    name="gender"
                                                    label="Your Gender"
                                                    MenuProps={{
                                                        classes: {
                                                            paper: classes.selectPaper,
                                                            list: classes.selectList,
                                                        },
                                                    }}
                                                    value={formData.gender}
                                                    onChange={handleOnChange}
                                                    placeholder="Select Gender"
                                                // helperText={formError.gender}
                                                // isError={formError.gender.length > 0}
                                                />
                                            </div>
                                        </Grid>
                                        <Grid item xs={6}>
                                            <div style={{ padding: "10px" }}>
                                                <CustomInput
                                                    name="age"
                                                    label="Your Age"
                                                    placeholder="Age"
                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    inputProps={{ maxLength: 2 }}
                                                    value={formData.age}
                                                />
                                            </div>
                                        </Grid>
                                    </Grid>
                                )}

                                {activeStep == 1 && (
                                    <Grid container>

                                        {healthConditions.map((item) => {
                                            return (
                                                <Grid item xs={6}>
                                                    <div style={{ padding: "10px" }}>
                                                        <CustomSelect
                                                            options={selectionData.map((item) => (
                                                                <MenuItem
                                                                    classes={{ root: classes.menuItemRoot, selected: classes.menuSelected }}
                                                                    value={item.selection}
                                                                >
                                                                    {item.selection}
                                                                </MenuItem>
                                                            ))}
                                                            name={item.id}
                                                            label={item.name}
                                                            MenuProps={{
                                                                classes: {
                                                                    paper: classes.selectPaper,
                                                                    list: classes.selectList,
                                                                },
                                                            }}
                                                            value={formData[item.id]}
                                                            onChange={handleOnChange}
                                                            placeholder={`Select ${item.name}`}
                                                        // helperText={formError.gender}
                                                        // isError={formError.gender.length > 0}
                                                        />
                                                    </div>
                                                </Grid>
                                            )
                                        })}

                                    </Grid>
                                )}

                                {activeStep == 2 && (
                                    <Grid container>

                                        {habits.map((item) => {
                                            return (
                                                <Grid item xs={6}>
                                                    <div style={{ padding: "10px" }}>
                                                        <CustomSelect
                                                            options={selectionData.map((item) => (
                                                                <MenuItem
                                                                    classes={{ root: classes.menuItemRoot, selected: classes.menuSelected }}
                                                                    value={item.selection}
                                                                >
                                                                    {item.selection}
                                                                </MenuItem>
                                                            ))}
                                                            name={item.id}
                                                            label={item.name}
                                                            MenuProps={{
                                                                classes: {
                                                                    paper: classes.selectPaper,
                                                                    list: classes.selectList,
                                                                },
                                                            }}
                                                            value={formData[item.id]}
                                                            onChange={handleOnChange}
                                                            placeholder={`Select ${item.name}`}
                                                        // helperText={formError.gender}
                                                        // isError={formError.gender.length > 0}
                                                        />
                                                    </div>
                                                </Grid>
                                            )
                                        })}

                                    </Grid>
                                )}

                                <div className={classes.footerCont}>
                                    {/* <Typography className={classes.instructions}>{getStepContent(activeStep)}</Typography> */}
                                    <div>

                                        <CustomButton disabled={activeStep === 0} onClick={handleBack}>
                                            Back
                                        </CustomButton>
                                        <span style={{ margin: "20px" }} />
                                        <CustomButton
                                            disabled={nextDisbled}
                                            onClick={activeStep === steps.length - 1 ? handleFinish : handleNext}
                                        >
                                            {activeStep === steps.length - 1 ? 'Calculate Premium' : 'Next'}
                                        </CustomButton>

                                    </div>
                                </div>

                            </Paper>

                            {/* <p className="small">
                                For Health insurance,&nbsp;
                             <a
                                    target="_blank"
                                    rel="noopener noreferrer"
                                    style={{ textDecoration: "none" }}

                                >
                                    Click Here.
                             </a>
                            </p> */}

                        </Grid>
                        <Grid item xs={2}></Grid>
                    </Grid>

                </div>
            </div>
        </div>
    );
}