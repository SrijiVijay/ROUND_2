import React from "react";
import loaderImg from "../../Assests/Images/loader.svg";

export default function Loader(props) {
  const { loader } = props;
  // const classes = useStyles();
  return (
    loader ? <div className='loader'>
      <img src={loaderImg} />
    </div> : ''
  );
  // return loader ? <div className="loading" /> : "";
}
