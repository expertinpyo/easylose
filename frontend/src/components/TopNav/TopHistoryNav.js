import classes from "./TopNav.module.css"
import { useHistory } from "react-router-dom"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faAngleRight, faAngleLeft } from "@fortawesome/free-solid-svg-icons"

function TopHistoryNav() {
  const history = useHistory()
  return (
    <div className={classes.top_nav_item_list}>
      <div className={classes.top_nav_item}>
        <div
          className={`${classes.top_nav_item__box} ${classes.top_nav_item__arrow}`}
          style={{ display: "flex" }}
          onClick={() => {
            history.goBack()
          }}
        >
          <FontAwesomeIcon icon={faAngleLeft} size="xl" />
        </div>
      </div>
      {/* <div className={`${classes.top_nav_item} ${classes.top_nav_item__text}`}>
        <div className={classes.top_nav_item__box}>
          <div>{props.text}</div>
        </div>
      </div> */}
    </div>
  )
}

export default TopHistoryNav
