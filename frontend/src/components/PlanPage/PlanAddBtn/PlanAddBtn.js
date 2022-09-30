import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import Tooltip from "@mui/material/Tooltip";
import { useState } from "react";
import { instance } from "../../../api/index";

import classes from "./PlanAddBtn.module.css";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "80vw",
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
  borderRadius: "5px",
  border: "none",
  outline: "none",
};

function PlanAddBtn() {
  const [inputName, setName] = useState("");
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleChange = (event) => setName(event.target.value);
  const handleAdd = () => {
    instance
      .post(
        "/foodset",
        {
          name: inputName,
        },
        {}
      )
      .then((response) => {
        console.log("생성!");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <div
        className={`${classes.add_btn} gradient_color__horizon box_shadow`}
        onClick={handleOpen}
      >
        추가하기
      </div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            <i class="fa-solid fa-burger" style={{ marginRight: ".3rem" }}></i>
            새로운 식단 계획 추가하기
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            <form className={classes.input}>
              <input
                type="text"
                value={inputName}
                onChange={handleChange}
                placeholder="계획 이름을 작성해주세요"
                autoFocus
              />
              <button
                onClick={handleAdd}
                className={`${classes.add_btn_2} box_shadow`}
              >
                생성하기
              </button>
            </form>
          </Typography>
        </Box>
      </Modal>
    </div>
  );
}
export default PlanAddBtn;