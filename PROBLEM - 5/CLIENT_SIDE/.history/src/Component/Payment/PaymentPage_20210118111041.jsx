import { Grid, Icon, MenuItem, Paper, TextareaAutosize, Typography, withStyles } from '@material-ui/core';
import axios from 'axios';
import react, { Fragment } from 'react';
import CustomButton from '../../CommonComponent/Button/CustomButton';
import CustomInput from '../../CommonComponent/Input/CustomInput';
import Loader from '../../CommonComponent/Loader/Loader';
import CustomSelect from '../../CommonComponent/Select/CustomSelect';
import success from '../../Assests/Images/success-icon.png';
import failure from '../../Assests/Images/failure-icon.png';
import { Image } from '@material-ui/icons';
import CryptoJS from "crypto-js";

const CryptoJS1 = require("crypto-js");
const paymentSecretKey = 'run4Fun'; 
const numRegx = new RegExp("^[0-9]+$");
const cardData = [
    {
        type: "Credit Card",
        id: "CREDIT_CARD"
    },
    {
        type: "Debit Card",
        id: "DEDIT_CARD"
    }
]

const paymentStyle = theme => ({
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
})

class PaymentPage extends react.Component {

    constructor(props) {
        super(props);
        if (sessionStorage.getItem("data") === null || sessionStorage.getItem("data") === "") {
            props.history.push(`start`);
        }
        if (sessionStorage.getItem("dataPrm") === null || sessionStorage.getItem("dataPrm") === "") {
            props.history.push(`start`);
        }
        this.state = {
            formPaymentData: {
                paymentMethod: "",
                cardNumber: "",
                holderName: "",
                cvv: "",
                expiryMonth: "",
                expiryYear: "",
            },
            loader: false,
            isPaymentStatusPage: false,
            paymentButton: true,
            paymentResponse: "",
            premiumResponseData: "",
        }
    }

    let data = sessionStorage.getItem("data");
    let bytes = CryptoJS.AES.decrypt(data, paymentSecretKey);
    let decryptedData = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));

    let premiumResponseData = sessionStorage.getItem("dataPrm");
    let resBytes = CryptoJS.AES.decrypt(premiumResponseData, paymentSecretKey);
    let resDecryptedData = JSON.parse(resBytes.toString(CryptoJS.enc.Utf8));

    componentDidMount() {
        if (sessionStorage.getItem("data") !== null && sessionStorage.getItem("dataPrm") !== null) {
            let data = sessionStorage.getItem("data");
            let bytes = CryptoJS.AES.decrypt(data, paymentSecretKey);
            let decryptedData = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));

            let premiumResponseData = sessionStorage.getItem("dataPrm");
            let resBytes = CryptoJS.AES.decrypt(premiumResponseData, paymentSecretKey);
            let resDecryptedData = JSON.parse(resBytes.toString(CryptoJS.enc.Utf8));

            this.setState(prevState => ({
                ...prevState,
                formData: decryptedData,
                premiumResponseData: resDecryptedData,
            }))
        }
    }

    handleOnChange = (event) => {

        const name = event.target.name;
        const value = event.target.value;

        if (name === "cardNumber" || name == "cvv" || name == "expiryMonth" || name == "expiryYear") {
            if (!(!value.length || numRegx.test(value))) {
                return;
            }
        }

        let values = this.state;
        values.formPaymentData[name] = value;
        this.setState({ ...values }, () => {
            this.validatePay()
        });
    }

    validatePay = () => {
        let values = this.state.formPaymentData;
        if (values.paymentMethod !== "" && values.cardNumber.length == 16
            && values.holderName !== "" && values.cvv.length == 3 && values.cvv != 0
            && values.expiryMonth.length == 2 && values.cardNumber != 0
            && values.expiryYear.length == 4 && values.expiryMonth <= 12 && values.expiryMonth != 0
            && values.expiryYear >= new Date().getFullYear()) {
            this.setState(prevState => ({
                ...prevState,
                paymentButton: false
            }))
        } else {
            this.setState(prevState => ({
                ...prevState,
                paymentButton: true
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
            "client": {
                "id": Math.ceil(Math.random() * 1000000000).toString()
            },
            "buyer": {
                "name": this.state.formData.name,
                "email": this.state.formData.email,
                "cpf": Math.ceil(Math.random() * 100000000000).toString()
            },
            "amount": this.state.premiumResponseData.data.amount,
            "type": this.state.formPaymentData.paymentMethod,
            "creditCard": {
                "holderName": this.state.formPaymentData.holderName,
                "number": this.state.formPaymentData.cardNumber,
                "expirationDate": this.state.formPaymentData.expiryYear + '-' + this.state.formPaymentData.expiryMonth,
                "cvv": this.state.formPaymentData.cvv
            }
        }

        // var data = JSON.stringify(obj);
        // var key = CryptoJS.enc.Latin1.parse('1234567812345678');
        // var iv = CryptoJS.enc.Latin1.parse('1234567812345678');
        // var encrypted = CryptoJS.AES.encrypt(
        //     data,
        //     key,
        //     {
        //         iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.ZeroPadding
        //     });
        // console.log('encrypted: ' + encrypted);

        // var decrypted = CryptoJS.AES.decrypt(encrypted, key, { iv: iv, padding: CryptoJS.pad.ZeroPadding });
        // console.log('decrypted: ' + decrypted.toString(CryptoJS.enc.Utf8));

        // let EncryptedObj = CryptoJS.AES.encrypt(JSON.stringify(obj), paymentSecretKey).toString();
        let EncryptedObj = JSON.stringify(obj);
        console.log(EncryptedObj, "EncryptedObj");

        // var bytes = CryptoJS.AES.decrypt(EncryptedObj, paymentSecretKey);
        // var decryptedData = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
        // console.log(decryptedData)

        axios.post('http://localhost:8085/paymentservice/payment', EncryptedObj,{headers: {"Content-Type":"application/json"}}).then(res => {
            console.log(res)
            this.setState(prevState => ({
                ...prevState,
                loader: false,
                isPaymentStatusPage: true,
                paymentResponse: res.data,
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
                {this.state.premiumResponseData != "" ?
                    <Paper classes={{ root: classes.rootPaper }} elevation={10}>
                        <Typography variant="h5" gutterBottom classes={{ root: classes.heading }}>
                            {this.state.isPaymentStatusPage ? "Payment Status" : "Payment Page"}
                        </Typography>
                        {this.state.isPaymentStatusPage ?
                            <Fragment>
                                {this.state.paymentResponse.status == "APPROVED" ?
                                    <div>
                                        <div >
                                            <img src={success} style={{ width: "25px", height: "auto" }} />
                                        </div>
                                        Your transaction is Successful
                                        <div className={classes.summary}>
                                            {/* <h4 style={{ margin: 0, marginBottom: "10px", color: "#3f51b5", paddingTop: "12px" }}>Personal Info</h4> */}
                                            <div className={classes.item}>
                                                <span className={classes.itemName}>Reference Number : </span>
                                                <span className={classes.itemValue}>{this.state.paymentResponse.referenceNumber}</span>
                                            </div>
                                            <div className={classes.item}>
                                                <span className={classes.itemName}>Person Name :</span>
                                                <span className={classes.itemValue}> {this.state.paymentResponse.buyer.name}</span>
                                            </div>
                                            <div className={classes.item}>
                                                <span className={classes.itemName}>Amount Paid : </span>
                                                <span className={classes.itemValue}>Rs. {this.state.paymentResponse.amount}</span>
                                            </div>

                                        </div>

                                    </div>
                                    :
                                    <div>
                                        <div >
                                            <img src={failure} style={{ width: "25px", height: "auto" }} />
                                        </div>
                                        Your transaction is failed
                                    </div>

                                }

                            </Fragment>
                            :
                            <Fragment>
                                <div className={classes.summary}>
                                    <h4 style={{ margin: 0, marginBottom: "10px", color: "#3f51b5", paddingTop: "12px" }}>Personal Info</h4>
                                    <div className={classes.item}>
                                        <span className={classes.itemName}>Insured Person : </span>
                                        <span className={classes.itemValue}>{this.state.formData.name}</span>
                                    </div>
                                    <div className={classes.item}>
                                        <span className={classes.itemName}>E-mail : </span>
                                        <span className={classes.itemValue}>{this.state.formData.email}</span>
                                    </div>
                                    <div className={classes.item}>
                                        <span className={classes.itemName}>Amount Payable : </span>
                                        <span className={classes.itemValue}>Rs. {this.state.premiumResponseData.data.amount}</span>
                                    </div>

                                </div>
                                <div className={classes.container}>
                                    <div style={{ padding: "15px 0", maxWidth: "400px" }}>
                                        <CustomSelect
                                            options={cardData.map((item) => (
                                                <MenuItem
                                                    classes={{ root: classes.menuItemRoot, selected: classes.menuSelected }}
                                                    value={item.id}
                                                >
                                                    {item.type}
                                                </MenuItem>
                                            ))}
                                            name="paymentMethod"
                                            label="Payment Method"
                                            MenuProps={{
                                                classes: {
                                                    paper: classes.selectPaper,
                                                    list: classes.selectList,
                                                },
                                            }}
                                            value={this.state.formPaymentData.paymentMethod}
                                            onChange={this.handleOnChange}
                                            placeholder="Select Payment Method"
                                        // helperText={formError.gender}
                                        // isError={formError.gender.length > 0}
                                        />
                                    </div>
                                    <Grid container>
                                        <Grid item xs={6}>
                                            <div style={{ padding: "10px 10px 10px 0" }}>
                                                <CustomInput
                                                    name="cardNumber"
                                                    label="Card Number"
                                                    placeholder="Card Number"

                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={this.handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    inputProps={{ maxLength: 16 }}
                                                    value={this.state.formPaymentData.cardNumber}
                                                />
                                            </div>
                                        </Grid>
                                        <Grid item xs={6}>
                                            <div style={{ padding: "10px 0px 10px 15px" }}>
                                                <CustomInput
                                                    name="holderName"
                                                    label="Holder Name"
                                                    placeholder="Holder Name"

                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={this.handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    // inputProps={{ maxLength: 15 }}
                                                    value={this.state.formPaymentData.holderName}
                                                />
                                            </div>
                                        </Grid>
                                        <Grid item xs={6}>
                                            <div style={{ padding: "10px 10px 10px 0" }}>
                                                <CustomInput
                                                    name="cvv"
                                                    label="CVV Code"
                                                    placeholder="CVV code"

                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={this.handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    inputProps={{ maxLength: 3 }}
                                                    value={this.state.formPaymentData.cvv}
                                                />
                                            </div>
                                        </Grid>
                                        <Grid item xs={3}>

                                            <div style={{ padding: "10px 0px 10px 15px" }}>
                                                <CustomInput
                                                    name="expiryMonth"
                                                    label="Expiry Month"
                                                    placeholder="Expiry Month"

                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={this.handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    inputProps={{ maxLength: 2 }}
                                                    value={this.state.formPaymentData.expiryMonth}
                                                />
                                            </div>
                                        </Grid>
                                        <Grid item xs={3}>
                                            <div style={{ padding: "10px 0px 10px 15px" }}>
                                                <CustomInput
                                                    name="expiryYear"
                                                    label="Expiry Year"
                                                    placeholder="Expiry Year"

                                                    // helperText={formError.vehicle_no}
                                                    // isError={formError.vehicle_no.length > 0}
                                                    onChange={this.handleOnChange}
                                                    // onBlur={handleOnBlur}
                                                    required={true}
                                                    inputProps={{ maxLength: 4 }}
                                                    value={this.state.formPaymentData.expiryYear}
                                                />
                                            </div>
                                        </Grid>
                                    </Grid>
                                    <div className={classes.footerBtnContainer}>
                                        <CustomButton disabled={this.state.paymentButton} onClick={this.handleProceed}>
                                            Proceed to Pay
                                    </CustomButton>
                                    </div>
                                </div>
                            </Fragment>
                        }
                    </Paper>
                    : null
                }
            </div>
        )
    }
}

export default withStyles(paymentStyle)(PaymentPage)