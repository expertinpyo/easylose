import classes from "./PickComplete.module.css"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faUserCheck } from "@fortawesome/free-solid-svg-icons"
import { useHistory } from "react-router-dom"
import { useSelector } from "react-redux"
import { useEffect } from "react"

function PickComplete({ putUserInfo }) {
  const history = useHistory()
  useEffect(() => {
    putUserInfo()
  })
  const userInfo = useSelector((state) => state.user.userInfo)
  return (
    <div className={classes.container}>
      <FontAwesomeIcon icon={faUserCheck} size="5x"></FontAwesomeIcon>
      <div>가입이 완료 되었습니다!</div>
      <div>다음과 같이 일일 영양소가 추천되었습니다.</div>
      { userInfo ?
      <div className={classes.itemList}>
        <div className={classes.item}>
          <div>칼로리</div>
          <div>{userInfo.dailyCalorie}cal</div>
        </div>
        <div className={classes.item}>
          <div>탄수화물</div>
          <div>{userInfo.dailyCarb}g</div>
        </div>
        <div className={classes.item}>
          <div>단백질</div>
          <div>{userInfo.dailyProtein}g</div>
        </div>
        <div className={classes.item}>
          <div>지방</div>
          <div>{userInfo.dailyFat}g</div>
        </div>
      </div>
      : null}
      <div>
        <div className={classes.button} onClick={() => {}}>
          일일 영양소 수정하기
        </div>
        <div
          className={classes.button}
          style={{
            background:
              "linear-gradient(90deg,var(--main-color) 44%,var(--light-color) 88%)",
            color: "white",
          }}
          onClick={() => {
            history.push("/main")
          }}
        >
          시작하기
        </div>
      </div>
    </div>
  )
}

export default PickComplete
