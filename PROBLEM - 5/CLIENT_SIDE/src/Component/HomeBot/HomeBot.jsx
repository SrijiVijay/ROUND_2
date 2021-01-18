import { Button, Grid, Paper, Typography, withStyles } from '@material-ui/core';
import react, { Fragment } from 'react';
import CustomButton from '../../CommonComponent/Button/CustomButton';


const homeBotStyle = theme => ({
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
        margin: "40px auto",
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
        padding: "50px",
    },
    itemName: {
        fontWeight: 500,
    },
    itemValue: {
        fontWeight: 700,
    },
})

class HomeBot extends react.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const { classes } = this.props
        return (
            <div className={classes.root}>
                <Paper classes={{ root: classes.rootPaper }} elevation={10}>
                    <Typography variant="h5" gutterBottom classes={{ root: classes.heading }}>
                        Home - Bot
                    </Typography>

                    <Fragment>
                        <div className={classes.summary}>
                            {/* <h4 style={{ margin: 0, marginBottom: "10px", color: "#3f51b5", paddingTop: "12px" }}>Login</h4> */}

                        </div>
                        <div className={classes.container}>

                            <Grid container>
                                <Grid item xs={2}></Grid>
                                <Grid item xs={4}>
                                    <Button variant="outlined" color="primary" onClick={() => {
                                        this.props.history.push('/login')
                                    }}>
                                        Login
                                    </Button>

                                </Grid>
                                <Grid item xs={4}>
                                    <Button variant="outlined" color="secondary" onClick={() => {
                                        this.props.history.push('/register')
                                    }}>
                                        Register
                                    </Button>
                                </Grid>
                                <Grid item xs={2}></Grid>
                            </Grid>

                        </div>
                    </Fragment>

                </Paper>
            </div>
        )
    }
}

export default withStyles(homeBotStyle)(HomeBot)