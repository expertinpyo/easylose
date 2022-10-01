import TopNav from "../../TopNav/TopNav";
import MealNutrientInfo from "../MealNutrientInfo/MealNutrientInfo";
import UserFoodList from "../UserFoodList/UserFoodList";
import NutrientChart from "../NutrientChart/NutrientChart";
import NutrientProgressBox from "../NutrientProgressBox/NutrientProgressBox";

import { useParams } from "react-router-dom";

import { registerLastEntered } from "../../../store/statusSlice";
import { useDispatch, useSelector } from "react-redux";
import { useState, useEffect } from "react";

function MealSummaryPage(props) {
  const userInfo = useSelector((state) => state.user.userInfo);
  const userDailyDiet = useSelector((state) => state.daily.dailyDiet);

  const [value, setValue] = useState({
    dietSum: undefined,
    foodList: undefined,
  });
  // console.log(props.userDailyDiet)

  const params = useParams();
  const meal = {
    BREAKFAST: "아침",
    LUNCH: "점심",
    DINNER: "저녁",
    SNACK: "간식",
  };
  const dispatch = useDispatch();

  useEffect(() => {
    localStorage.setItem("mealtime", params.mealtime);
    dispatch(registerLastEntered(params.mealtime));
  }, [params, dispatch]);

  useEffect(() => {
    if (userDailyDiet) {
      const temp = { dietSum: undefined, foodList: undefined };
      temp.dietSum = userDailyDiet[0].sums[params.mealtime];
      temp.foodList = userDailyDiet[0].details[params.mealtime];
      setValue(temp);
    }
  }, [userDailyDiet]);

  return (
    <div>
      <div id="top_nav_area">
        <TopNav text={meal[params.mealtime]} arrow={["/main", 0]} />
      </div>
      <div
        style={{
          margin: "10vh 5vw 15vh",
        }}
      >
        <MealNutrientInfo dietSum={value.dietSum} />
        <UserFoodList foodList={value.foodList} />
        <NutrientChart dietSum={value.dietSum} />
        <NutrientProgressBox userInfo={userInfo} dietSum={value.dietSum} />
      </div>
    </div>
  );
}
export default MealSummaryPage;
