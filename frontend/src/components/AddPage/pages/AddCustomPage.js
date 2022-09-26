import AddButtonList from "../AddButtonList/AddButtonList";
// import TopNav from "../../TopNav/TopNav";
import classes from "./AddCustomPage.module.css";
import { useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";

function AddCustomPage() {
  const accessToken = useSelector((state) => state.user.accessToken);

  const [foodName, setFoodName] = useState("");
  const [foodAmount, setFoodAmount] = useState("");
  const [foodCalorie, setFoodCalorie] = useState("");
  const [foodCarb, setFoodCarb] = useState("");
  const [foodProtein, setFoodProtein] = useState("");
  const [foodFat, setFoodFat] = useState("");
  const [foodSugar, setFoodSugar] = useState("");
  const [foodCholesterol, setFoodCholesterol] = useState("");
  const [foodSaturatedFat, setFoodSaturatedFat] = useState("");
  const [foodTransFat, setFoodTransFat] = useState("");
  const [foodSalt, setFoodSalt] = useState("");

  const onClickHandler = () => {
    axios({
      method: "post",
      url: "https://j7a704.p.ssafy.io/api/v1/food",
      data: {
        foodType: "string",
        name: foodName,
        totalAmount: Number(foodAmount),
        calorie: Number(foodCalorie),
        carb: Number(foodCarb),
        protein: Number(foodProtein),
        fat: Number(foodFat),
        sugar: Number(foodSugar),
        salt: Number(foodSalt),
        cholesterol: Number(foodCholesterol),
        saturatedFat: Number(foodSaturatedFat),
        transFat: Number(foodTransFat),
      },
      headers: { Authorization: `Bearer ${accessToken}` },
    })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div style={{ marginTop: "2vh" }}>
      {/* <div id="top_nav_area">
        <TopNav arrow={["", "/signup/gender"]}></TopNav>
      </div> */}
      <div className={classes.container}>
        <div className={classes.inputbox}>
          <input
            value={foodName}
            onChange={(e) => {
              setFoodName(e.target.value);
            }}
            className={classes.foodName}
          ></input>
        </div>
        <div className={classes.detailcontainer}>
          <div className={classes.detailBig}>
            <div>기준 분량(g)</div>
            <input
              value={foodAmount}
              onChange={(e) => {
                setFoodAmount(e.target.value);
              }}
              className={classes.input}
            ></input>
          </div>
          <div className={classes.detailBig}>
            <div>열량(kcal)</div>
            <input
              value={foodCalorie}
              onChange={(e) => {
                setFoodCalorie(e.target.value);
              }}
              className={classes.input}
            ></input>
          </div>
          <div className={classes.detailBig}>
            <div>탄수화물(g)</div>
            <input
              value={foodCarb}
              onChange={(e) => {
                setFoodCarb(e.target.value);
              }}
              className={classes.input}
            ></input>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>당(g)</div>
              <input
                value={foodSugar}
                onChange={(e) => {
                  setFoodSugar(e.target.value);
                }}
                className={classes.input}
              ></input>
            </div>
          </div>
          <div className={classes.detailBig}>
            <div>단백질(g)</div>
            <input
              value={foodProtein}
              onChange={(e) => {
                setFoodProtein(e.target.value);
              }}
              className={classes.input}
            ></input>
          </div>
          <div className={classes.detailBig}>
            <div>지방(g)</div>
            <input
              value={foodFat}
              onChange={(e) => {
                setFoodFat(e.target.value);
              }}
              className={classes.input}
            ></input>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>포화지방(g)</div>
              <input
                value={foodSaturatedFat}
                onChange={(e) => setFoodSaturatedFat(e.target.value)}
                className={classes.input}
              ></input>
            </div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>트랜스지방(g)</div>
              <input
                value={foodTransFat}
                onChange={(e) => setFoodTransFat(e.target.value)}
                className={classes.input}
              ></input>
            </div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>콜레스트롤(g)</div>
              <input
                value={foodCholesterol}
                onChange={(e) => {
                  setFoodCholesterol(e.target.value);
                }}
                className={classes.input}
              ></input>
            </div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>나트륨(g)</div>
              <input
                value={foodSalt}
                onChange={(e) => {
                  setFoodSalt(e.target.value);
                }}
                className={classes.input}
              ></input>
            </div>
          </div>
        </div>
      </div>
      <div onClick={onClickHandler} className={classes.addButtonContainer}>
        <div className={classes.addButton}>저장하기</div>
      </div>
      <AddButtonList></AddButtonList>
    </div>
  );
}

export default AddCustomPage;
