import React, { useState } from "react";
import { Content, Footer, Header } from "../CommonComponent/LayoutComponent/index.jsx";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        minHeight: "100vh",
        position: "relative",
        // backgroundImage:
        //    url(${amBackgroundImage})` : "",
        backgroundPosition: "100%",
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
    },
    commonCoinatinerRoot: {
        display: "flex",
        alignItems: "center",
        flexWrap: "nowrap",
        marginLeft: "auto",
        marginRight: "auto",
        height: "100%",
        padding: "0 15px",
        [theme.breakpoints.up("xs")]: {
            width: "97vw",
        },
        [theme.breakpoints.up("sm")]: {
            width: "95vw",
        },
        [theme.breakpoints.up("md")]: {
            width: "100vw",
        },
        [theme.breakpoints.up("lg")]: {
            width: "1110px",
        },
        [theme.breakpoints.up("xl")]: {
            width: "1110px",
        },
    },
}));
export default function CommonLayout(props) {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            {window.location.pathname.search('login') > -1 || window.location.pathname.search('register') > -1
                || window.location.pathname.search('homebot') > -1 ?
                null
                :
                <Header
                    className={classes.commonCoinatinerRoot}
                    {...props}
                />
            }

            <Content
                className={classes.commonCoinatinerRoot}
                {...props}
            />
            {window.location.pathname.search('login') > -1 || window.location.pathname.search('register') > -1
                || window.location.pathname.search('homebot') > -1 ?
                null
                :
                <Footer
                    className={classes.commonCoinatinerRoot}
                />
            }

        </div>
    );
}
