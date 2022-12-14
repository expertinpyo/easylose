import classes from "./AddDetailPage.module.css"
import TopNav from "../../TopNav/TopNav"
import { useRef } from "react"
import AddButtonList from "../AddButtonList/AddButtonList"
import { useHistory } from "react-router-dom"
import TopHistoryNav from "../../TopNav/TopHistoryNav"

function AddDetailPage() {
  const history = useHistory()
  const foodInfo = history.location.state.foodInfo
  console.log(foodInfo)
  return (
    <div>
      <div id="top_nav_area">
        <TopHistoryNav></TopHistoryNav>
      </div>
      <div style={{ marginTop: "9vh" }} className={classes.container}>
        <div className={classes.header}>
          <div className={classes.name}>
            {/* <div
              style={{ fontSize: 16, marginBottom: "1vh", color: "#A8A8A8" }}
            >
              {foodInfo.food_type}
            </div> */}
            <div style={{ fontSize: 20, fontWeight: 1000 }}>
              {foodInfo.name}
            </div>
          </div>
        </div>
        <div style={{ width: "90%" }}>
          <div className={classes.header2}>
            {/* <div style={{ fontSize: 14, marginBottom: "1vh" }}>
              1회 분량 기준입니다
            </div> */}
            <div style={{ fontSize: 18, fontWeight: 700 }}>영양정보</div>
          </div>
          <div className={classes.itemList}>
            <div className={classes.item}>
              <div>칼로리</div>
              <div>{foodInfo.calorie}kcal</div>
            </div>
            <div className={classes.item}>
              <div>탄수화물</div>
              <div>{foodInfo.carb}g</div>
            </div>
            <div className={classes.item}>
              <div>단백질</div>
              <div>{foodInfo.protein}g</div>
            </div>
            <div className={classes.item}>
              <div>지방</div>
              <div>{foodInfo.fat}g</div>
            </div>
          </div>
        </div>
        <div className={classes.detailcontainer}>
          <div className={classes.detailTitle}>
            <div style={{ fontSize: 18, fontWeight: 700 }}>상세 영양정보</div>
            <div>{foodInfo.total_amount}</div>
          </div>
          <div className={classes.detailBig}>
            <div>열량</div>
            <div>{foodInfo.calorie}kcal</div>
          </div>
          <div className={classes.detailBig}>
            <div>탄수화물</div>
            <div>{foodInfo.carb}g</div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>당</div>
              <div>{foodInfo.sugar}g</div>
            </div>
          </div>
          <div className={classes.detailBig}>
            <div>단백질</div>
            <div>{foodInfo.protein}g</div>
          </div>
          <div className={classes.detailBig}>
            <div>지방</div>
            <div>{foodInfo.fat}g</div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>포화지방</div>
              <div>{foodInfo.saturated_fat}g</div>
            </div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>트랜스지방</div>
              <div>{foodInfo.trans_fat}g</div>
            </div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>콜레스트롤</div>
              <div>{foodInfo.cholesterol}g</div>
            </div>
          </div>
          <div className={classes.detailSmall}>
            <div className={classes.detailSmallSmall}>
              <div>나트륨</div>
              <div>{foodInfo.salt}g</div>
            </div>
          </div>
        </div>
      </div>
      <AddButtonList></AddButtonList>
    </div>
  )
}

export default AddDetailPage
