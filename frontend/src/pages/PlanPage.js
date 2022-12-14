import { useEffect } from "react"
import { Route } from "react-router-dom"
import PlanMainPage from "../components/PlanPage/Pages/PlanMainPage"
import PlanAddBtn from "../components/PlanPage/PlanAddBtn/PlanAddBtn"
import { instance } from "../api/index"
import { useDispatch } from "react-redux"
import { registerDailyMealList, registerOneMealList } from "../store/planSlice"
import PlanDetailPage from "../components/PlanPage/Pages/PlanDetailPage"
import PlanSummaryPage from "../components/PlanPage/Pages/PlanSummaryPage"
import PlanMealSummaryPage from "../components/PlanPage/Pages/PlanMealSummaryPage"
import PlanReportPage from "../components/PlanPage/Pages/PlanReportPage"

import classes from "../components/PlanPage/Pages/PlanPage.module.css"

function PlanPage() {
  const colorSet = {
    carbColor: "#afb4ff",
    proteinColor: "#7c83fd",
    fatColor: "#b1e1ff",
  }
  const dispatch = useDispatch()
  useEffect(() => {
    instance
      .get("/foodset", {})
      .then((response) => {
        dispatch(registerDailyMealList(response.data))
      })
      .catch((error) => console.log(error))
  }, [])

  return (
    <div>
      <Route path="/plan" exact>
        <PlanMainPage></PlanMainPage>
      </Route>
      <Route path="/plan/:planId" exact>
        <PlanDetailPage colorSet={colorSet}></PlanDetailPage>
      </Route>
      <Route path="/plan/:planId/summary" exact>
        <PlanSummaryPage colorSet={colorSet}></PlanSummaryPage>
      </Route>
      <Route path="/plan/:planId/report" exact>
        <PlanReportPage colorSet={colorSet}></PlanReportPage>
      </Route>
      <Route path="/plan/:planId/meal/:mealtime">
        <PlanMealSummaryPage colorSet={colorSet}></PlanMealSummaryPage>
      </Route>
    </div>
  )
}

export default PlanPage
