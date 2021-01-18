import { Grid, MenuItem, Paper, Typography, withStyles } from '@material-ui/core';
import axios from 'axios';
import react, { Fragment } from 'react';
import CustomButton from '../../CommonComponent/Button/CustomButton';
import Loader from '../../CommonComponent/Loader/Loader';
const CryptoJS = require("crypto-js");
const paymentSecretKey = 'run4Fun';


const summaryStyle = theme => ({
    root: {
        width: "85%",
        margin: "auto",
        borderRadius: "15px",
    },
    rootPaper: {
        padding: "10px 40px",
        borderRadius: "15px",
    },
    container: {
        width: "90%",
        margin: "5px auto",
    },
    summary: {
        textAlign: "left",
        width: "90%",
        margin: "auto",
        borderBottom: "2px solid #3f51b5",
        paddingBottom: "23px",
    },
    heading: {
        padding: "5px",
        fontSize: "24px",
        fontWeight: "500",
        color: "#3f51b5",
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
        //     background: "#3f51b5",
        // },
        // "& hover": {
        //   background: theme.palette.primary.light,
        // },
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
    footerBtnContainer: {
        margin: "30px",
    },
    itemName: {
        fontWeight: 500,
    },
    itemValue: {
        fontWeight: 700,

    },
    item: {
        padding: "4px",
        fontSize: "18px",
    }
})

class SummaryPage extends react.Component {

    constructor(props) {
        super(props)
        if (sessionStorage.getItem("data") === null || sessionStorage.getItem("data") === "") {
            props.history.push(`start`);
        }
        if (sessionStorage.getItem("dataPrm") === null || sessionStorage.getItem("dataPrm") === "") {
            props.history.push(`start`);
        }
        this.state = {
            loader: true,
            formData: {},
            premiumResponseData: {},
        }
    }

    componentDidMount() {
        if (sessionStorage.getItem("data") !== null && sessionStorage.getItem("dataPrm") !== "") {
            let data = sessionStorage.getItem("data");
            let bytes = CryptoJS.AES.decrypt(data, paymentSecretKey);
            let decryptedData = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));

            let premiumResponseData = sessionStorage.getItem("dataPrm");
            let resBytes = CryptoJS.AES.decrypt(premiumResponseData, paymentSecretKey);
            let resDecryptedData = JSON.parse(resBytes.toString(CryptoJS.enc.Utf8));


            this.setState(prevState => ({
                ...prevState,
                loader: false,
                formData: decryptedData,
                premiumResponseData: resDecryptedData.data,
            }))
        }
    }

    handleProceed = () => {

        this.setState(prevState => ({
            ...prevState,
            loader: true,
        }))

        let path = `payment`;
        this.props.history.push(path);

    }

    render() {
        console.log(this.state, this.state.formData)
        const { classes } = this.props
        return (
            <div className={classes.root}>

                <Loader loader={this.state.loader} />
                <Paper classes={{ root: classes.rootPaper }} elevation={10}>
                    <Typography variant="h5" gutterBottom classes={{ root: classes.heading }}>
                        Review Your Details
                    </Typography>

                    {Object.keys(this.state.formData).length > 0 ?
                        <Fragment>
                            <div className={classes.summary}>
                                <h4 style={{ margin: 0, marginBottom: "10px", color: "#3f51b5", paddingTop: "12px", fontSize: "19px" }}>Personal & Premium Details</h4>
                                <Grid container>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Insured Person : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.name}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>E-mail : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.email}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Age : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.age}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Gender : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.gender}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Hypertension : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.hyperTension}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Blood Sugar : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.bloodSugar}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Overweight : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.overWeight}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Blood Pressure : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.bloodPressure}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Smoking : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.smoking}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Alcohol : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.alcohol}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Drugs : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.drugs}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Daily Exercise : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemValue}>{this.state.formData.dailyExercise}</span>
                                    </Grid>

                                    <Grid item xs={6} className={classes.item}>
                                        <span className={classes.itemName}>Premium Amount : </span>
                                    </Grid>
                                    <Grid item xs={6} className={classes.item}>
                                    {console.log(this.state.premiumResponseData)}
                                        <span className={classes.itemValue}>Rs. {this.state.premiumResponseData.amount} </span>
                                    </Grid>

                                </Grid>
                            </div>

                            <div className={classes.container}>
                                <div className={classes.footerBtnContainer}>
                                    <CustomButton disabled={this.state.paymentButton} onClick={this.handleProceed}>
                                        Proceed to payment
                                </CustomButton>
                                </div>
                            </div>
                        </Fragment>
                        : null
                    }

                </Paper>
            </div>
        )
    }
}

export default withStyles(summaryStyle)(SummaryPage)