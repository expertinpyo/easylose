import { configureStore } from "@reduxjs/toolkit";
import userInfoSlice from "./userInfoSlice";

export default configureStore({
  reducer: {
    UserInfo: userInfoSlice,
  },
});
