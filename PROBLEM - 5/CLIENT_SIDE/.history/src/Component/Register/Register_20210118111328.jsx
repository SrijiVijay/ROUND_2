import { Grid, MenuItem, Paper, TextareaAutosize, Typography, withStyles } from '@material-ui/core';
import axios from 'axios';
import react, { Fragment } from 'react';
import CustomButton from '../../CommonComponent/Button/CustomButton';
import CustomInput from '../../CommonComponent/Input/CustomInput';
import Loader from '../../CommonComponent/Loader/Loader';
import HomeIcon from '@material-ui/icons/Home';
import CustomSelect from '../../CommonComponent/Select/CustomSelect';

const CryptoJS = require("crypto-js");
const paymentSecretKey = 'asrun4Funpiringminds';
const numRegx = new RegExp("^[0-9]+$");

const registerStyle = theme => ({
    root: {
        width: "60%",
        margin: "auto",
        borderRadius: "15px",
    },
    rootPaper: {
        padding: "10px 40px",
        borderRadius: "15px",
    },
    container: {
        width: "90%",
        margin: "20px auto",
    },
    summary: {
        textAlign: "left",
        width: "90%",
        margin: "auto",
        borderBottom: "2px solid #3f51b5",
        paddingBottom: "23px",
    },
    heading: {
        padding: "10px",
        fontSize: "24px",
        fontWeight: "600",
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
})

class RegisterPage extends react.Component {

    constructor(props) {
        super(props);

        this.state = {
            formData: {
                username: "",
                password: "",
            },
            loader: false,
            loginButton: true
        }
    }



    handleOnChange = (event) => {

        const name = event.target.name;
        const value = event.target.value;

        // if (name === "cardNumber" || name == "cvv" || name == "expiryMonth" || name == "expiryYear") {
        //     if (!(!value.length || numRegx.test(value))) {
        //         return;
        //     }
        // }

        let values = this.state;
        values.formData[name] = value;
        this.setState({ ...values }, () => {
            this.validatePay()
        });
    }

    validatePay = () => {

        let values = this.state.formData;
        if (values.username !== "" && values.password !== "") {
            this.setState(prevState => ({
                ...prevState,
                loginButton: false
            }))
        } else {
            this.setState(prevState => ({
                ...prevState,
                loginButton: true
            }))
        }

    }

    handleProceed = () => {

        this.setState(prevState => ({
            ...prevState,
            loader: true
        }))

        console.log(this.state)

        let obj = {
            "userDetails": {
                "username": this.state.formData.username,
                "password": this.state.formData.password,
            },
        }

        let EncryptedObj = CryptoJS.AES.encrypt(JSON.stringify(obj), paymentSecretKey).toString();
        console.log(EncryptedObj);

        // var bytes = CryptoJS.AES.decrypt(EncryptedObj, paymentSecretKey);
        // var decryptedData = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
        // console.log(decryptedData)

        axios.post('http://localhost:8085/premiumservice/calculatePremium', EncryptedObj).then(res => {
            console.log(res)
            this.setState(prevState => ({
                ...prevState,
                loader: false,
                paymentResponse: res,
            }))
        })
            .catch(() => {
                let path = `error`;
                this.props.history.push(path)
            });

    }

    render() {
        const { classes } = this.props
        return (
            <div className={classes.root}>
                <Loader loader={this.state.loader} />
                <Paper classes={{ root: classes.rootPaper }} elevation={10}>
                    <Typography variant="h5" gutterBottom classes={{ root: classes.heading }}>

                        <HomeIcon onClick={() => {
                            this.props.history.push('/homebot')
                        }} style={{ cursor: "pointer", float: "left", width: "1.3em", height: '1.3em' }} />
                        <span>Register</span>
                    </Typography>

                    <Fragment>
                        <div className={classes.summary}>
                            {/* <h4 style={{ margin: 0, marginBottom: "10px", color: "#3f51b5", paddingTop: "12px" }}>Login</h4> */}

                        </div>
                        <div className={classes.container}>

                            <Grid container>
                                <Grid item xs={12}>
                                    <div style={{ padding: "10px 0 10px 0" }}>
                                        <CustomInput
                                            name="username"
                                            label="User Name"
                                            placeholder="User Name"

                                            // helperText={formError.vehicle_no}
                                            // isError={formError.vehicle_no.length > 0}
                                            onChange={this.handleOnChange}
                                            // onBlur={handleOnBlur}
                                            required={true}
                                            // inputProps={{ maxLength: 16 }}
                                            value={this.state.formData.username}
                                        />
                                    </div>
                                </Grid>
                                <Grid item xs={12}>
                                    <div style={{ padding: "10px 0 10px 0" }}>
                                        <CustomInput
                                            name="password"
                                            label="Password"
                                            placeholder="Password"
                                            isPassword
                                            // helperText={formError.vehicle_no}
                                            // isError={formError.vehicle_no.length > 0}
                                            onChange={this.handleOnChange}
                                            // onBlur={handleOnBlur}
                                            required={true}
                                            // inputProps={{ maxLength: 15 }}
                                            value={this.state.formData.password}
                                        />
                                    </div>
                                </Grid>

                            </Grid>
                            <div className={classes.footerBtnContainer}>
                                <CustomButton disabled={this.state.loginButton} onClick={this.handleProceed}>
                                    Register
                                </CustomButton>
                            </div>
                        </div>
                    </Fragment>

                </Paper>
            </div>
        )
    }
}

export default withStyles(registerStyle)(RegisterPage)