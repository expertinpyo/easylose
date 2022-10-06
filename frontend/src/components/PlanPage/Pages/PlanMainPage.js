import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import ListItem from "../ListItem/ListItem"
import PlanAddBtn from "../PlanAddBtn/PlanAddBtn"

import classes from "./PlanMainPage.module.css"

function PlanMainPage() {
  const [term, setTerm] = useState(0)
  const dispatch = useDispatch()
  const oneMealList = useSelector((state) => state.plan.oneMealList)
  const dailyMealList = useSelector((state) => state.plan.dailyMealList)

  useEffect(() => {}, [])

  return (
    <div className={classes.container}>
      <div className={classes.page_title}>나의 식단 목록</div>
      <div className={classes.itemContainer}>
        {term === 1
          ? oneMealList.map((item) => {
              return (
                <div style={{ width: "100%", height: "10vh" }}>
                  <ListItem></ListItem>
                </div>
              )
            })
          : dailyMealList.map((item, index) => {
              return (
                <div
                  key={index}
                  style={{ width: "100%", height: "10vh", marginBottom: "1vh" }}
                >
                  <ListItem data={item}></ListItem>
                </div>
              )
            })}
      </div>
      <PlanAddBtn />
    </div>
  )
}

export default PlanMainPage
