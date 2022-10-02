import classes from "./RecommendListItem.module.css"

function RecommendListItem(props) {
  return (
    <div className={classes.container}>
      <div className={classes.left}>
        <div className={classes.item_title}>Feliz Navidata</div>
        <div className={classes.item_subtitle}>
          Jose Feliciano Christmas Hitsfdkljfsdakljfasdkjlafdskjl
        </div>
      </div>
      <div className={classes.right}>
        <div>
          <i className="fa-regular fa-circle-question"></i>
        </div>
      </div>
    </div>
  )
}

export default RecommendListItem